package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"math/rand"
	"net/http"
	"strings"
	"time"

	vegeta "github.com/tsenart/vegeta/lib"
)

func getTeamIdsFromFile() []string {
	teamsIdsInBytes, openFileError := ioutil.ReadFile("team_ids.txt")

	if openFileError != nil {
		log.Fatal("file does not exist")
	}

	teamIds := strings.Split(string(teamsIdsInBytes), ",")
	for i := range teamIds {
		teamIds[i] = strings.TrimSpace(teamIds[i])
	}

	return teamIds
}

func updatePlayer() vegeta.Targeter {
	return func(tgt *vegeta.Target) error {
		if tgt == nil {
			return vegeta.ErrNilTarget
		}

		applicationJson := []string{"application/json"}
		teamIds := getTeamIdsFromFile()
		rand.Seed(time.Now().UnixNano())

		tgt.Method = "POST"
		tgt.URL = "http://localhost:8080/api/teams/" +  teamIds[rand.Intn(199 - 0 + 1) + 0] + "/players"
		tgt.Body = []byte(`{"name": "Messi", "dorsal": 15, "price": 1}`)
		tgt.Header =  http.Header{
			"Content-Type": applicationJson,
		}

		return nil
	}
}

func getTeamsById() vegeta.Targeter {
	return func(tgt *vegeta.Target) error {
		if tgt == nil {
			return vegeta.ErrNilTarget
		}

		rand.Seed(time.Now().UnixNano())
		teamIds := getTeamIdsFromFile()

		tgt.Method = "GET"
		tgt.URL = "http://localhost:8080/api/teams/" +  teamIds[rand.Intn(199 - 0 + 1) + 0]

		return nil
	}
}

func main() {
	rate := vegeta.Rate{Freq: 2, Per: 2*time.Second}
	duration := 5 * time.Second

	targeter := getTeamsById()
	getTeamsAttacker := vegeta.NewAttacker()
	updatePlayerAttacker := vegeta.NewAttacker()

	var metrics vegeta.Metrics
	for res := range getTeamsAttacker.Attack(targeter, rate, duration, "User") {
		resUpdatePlayer := <- updatePlayerAttacker.Attack(updatePlayer(), vegeta.Rate{Freq: 1, Per: time.Second}, time.Second, "User 2")
		metrics.Add(res)
		metrics.Add(resUpdatePlayer)
	}
	metrics.Close()

	fmt.Printf("99th percentile: %s\n", metrics.Latencies.P99)
	fmt.Printf("Mean: %s\n", metrics.Latencies.Mean)
	fmt.Printf("Request: %d\n", metrics.Requests)
	fmt.Printf("Total 201 Requests: %d\n", metrics.StatusCodes["201"])
	fmt.Printf("Total 200 Requests: %d\n", metrics.StatusCodes["200"])
	fmt.Println("Errors: ")

	for _, error := range metrics.Errors {
		fmt.Println(error)
	}

}
