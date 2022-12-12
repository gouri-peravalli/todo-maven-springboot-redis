# Pre-requisite
Install Docker & setup redis and start the redis container.

# CURL Commands

## To create a todo task
curl --location --request POST 'http://localhost:8080/todo' \
--header 'Content-Type: application/json' \
--data-raw '{     
    "userId":1,
    "description": "Create a marketing campaign list 132",
    "dueDate": "2022-12-09",
    "state": "TODO"
}'

## To get the todo's for a given user
curl --location --request GET 'http://localhost:8080/todo/user/1' \
--header 'Content-Type: application/json'

## To get all the Todo's
curl --location --request GET 'localhost:8080/todo/all'

## To update a specific Todo
curl --location --request PUT 'localhost:8080/todo/1_2' \
--header 'Content-Type: application/json' \
--data-raw '{
        "taskId": 1,
        "description": "sdfdsfds",
        "dueDate": "2023-04-01",
        "state": "IN_PROGRESS"
}'
