#!/bin/sh
for i in {1..4700}
do 
    curl -d '{
    "name": "Messi",
    "dorsal": 10,
    "price": 1
    }' -H "Content-Type: application/json" -X POST http://localhost:8080/api/teams/34f797fb-5015-48ba-beb6-a23c814c76eb/players
done

echo "Finished"