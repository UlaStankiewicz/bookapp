To run the application, docker and maven are required.
Steps:
1. mvn clean install
2. docker-compose up --build

Example invokes are in BookApp.postman_collection.json, which you can import in Postman app or you can use the following instructions:

1. Get all books

curl -X GET \
http://localhost:8080/books/ \
-H 'cache-control: no-cache' \
-H 'postman-token: e20b02a0-5a67-110d-e8dc-418634ed7bfa'

2. Get book by id

curl -X GET \
http://localhost:8080/books/1001 \
-H 'cache-control: no-cache' \
-H 'postman-token: d2ac49b8-36ae-cc91-1fae-d209822cc7f4'

3. Update book by id

curl -X PUT \
http://localhost:8080/books/102 \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 348eb008-ce4b-c63e-c304-36ac62797c8f' \
-d '{
"title": "Jack Ryan 2",
"isbn": "123213213-3",
"author": "endriu",
"rating": 5
}'

4. Delete book by id
   
curl -X DELETE \
http://localhost:8080/books/2 \
-H 'cache-control: no-cache' \
-H 'postman-token: 096f56aa-38e6-12af-9505-80043f55019d'

5. Add book

curl -X POST \
http://localhost:8080/books/ \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 33953e2c-4e6a-05ab-c67b-9af3a0ac04b3' \
-d '{
"title": "Jack Ryan",
"author": "Meryl J.K.",
"isbn": "123456781-1",
"numberPages": 1000,
"rating": 4
}'

6. Add comment for book

curl -X POST \
http://localhost:8080/books/1000/comments \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 3c30f706-08d3-38ce-56a9-8543fab7267b' \
-d '{
"description": "it is description about some book"
}'