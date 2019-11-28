package main

import (
	"fmt"
	"net/http"
	"time"

	vegeta "github.com/tsenart/vegeta/lib"
)

func main() {
	rate := vegeta.Rate{Freq: 1, Per: time.Second}
	duration := 5 * time.Second
	applicationJson := []string{"application/json"}

	targeter := vegeta.NewStaticTargeter(vegeta.Target{
		Method: "POST",
		URL:    "http://localhost:8080/api/teams/34f797fb-5015-48ba-beb6-a23c814c76eb/players",
		Body: []byte(`{"name": "Messi", "dorsal": 10, "price": 1}`),
		Header: http.Header{
			"Content-Type": applicationJson,
		},
	}, vegeta.Target{
		Method: "GET",
		URL:    "http://localhost:8080/api/teams",
	})
	attacker := vegeta.NewAttacker()

	var metrics vegeta.Metrics
	for res := range attacker.Attack(targeter, rate, duration, "User") {
		metrics.Add(res)
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