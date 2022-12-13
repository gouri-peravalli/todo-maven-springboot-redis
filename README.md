# Pre-requisite
Install Docker & setup redis and start the redis container.

`docker run -d --name redis-sb -p 6379:6379 --hostname=localhost  --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=GOSU_VERSION=1.14 --env=REDIS_VERSION=7.0.6 --env=REDIS_DOWNLOAD_URL=http://download.redis.io/releases/redis-7.0.6.tar.gz --env=REDIS_DOWNLOAD_SHA=7b33a7e890d13e27af1f246acb16312669ad8a1d56ce8f807dfbcd3c09aa7bb3 --volume=/data --workdir=/data --runtime=runc -d redis:latest`

# How to login to redis cli and setup the bind to 0.0.0.0
`docker exec -it redis-sb bash`
`redis-cli`
`set bind 0.0.0.0`
`save`


## Note
   Redis path and version can be different.
   In localhost we need to set the bind to 0.0.0.0

# CURL Commands
`key is userid_taskId, eg: 100_2617212, here 100 is userid and 2617212 is the task id.`

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

## To get a specific todo based on the key
curl --location --request GET 'http://localhost:8080/todo/1_2' \
--header 'Content-Type: application/json'

## To update a specific Todo
curl --location --request PUT 'localhost:8080/todo/1_2' \
--header 'Content-Type: application/json' \
--data-raw '{
        "taskId": 1,
        "description": "sdfdsfds",
        "dueDate": "2023-04-01",
        "state": "IN_PROGRESS"
}'

## To delete a specific Todo
curl --location --request DELETE 'localhost:8080/todo/1_2' \
--header 'Content-Type: application/json'
