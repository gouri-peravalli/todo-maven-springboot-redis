# Todo
Curl


## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://gitlab.com/rameshappmax/todo.git
git branch -M main
git push -uf origin main
```

## CURL Commands

curl --location --request GET 'localhost:8080/todo/all'

curl --location --request POST 'localhost:8080/todo' \
--header 'Content-Type: application/json' \
--data-raw '{
     "taskId": "2",
    "description": "Test1",
    "dueDate": "2022-04-01",
    "state": "DONE"
}'


curl --location --request PUT 'localhost:8080/todo/2' \
--header 'Content-Type: application/json' \
--data-raw '{
        "taskId": 1,
        "description": "sdfdsfds",
        "dueDate": "2022-04-01",
        "state": "IN_PROGRESS"
}'

curl --location --request PUT 'localhost:8080/todo/2' \
--header 'Content-Type: application/json' \
--data-raw '{
        "taskId": 1,
        "description": "sdfdsfds",
        "dueDate": "2022-04-01",
        "state": "IN_PROGRESS"
}'

