# phonebook

spring security heavily borrowed from https://github.com/brahalla/Cerberus

#register
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"shobhit","password":"shobhit"}' http://localhost:8080/register

#login
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"shobhit","password":"shobhit"}' http://localhost:8080/user

#authenticate token
curl -i -H "Content-Type: application/json" -H "X-Auth-Token: " -X GET http://localhost:8080/api/protected