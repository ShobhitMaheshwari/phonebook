# phonebook

spring security borrowed from https://github.com/brahalla/Cerberus

Uses mysql database, with table name boot_security

# test
mvn test

# package
mvn clean package

# run
mvn spring-boot:run

Then in a new shell do the following

# register
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"shobhit","password":"shobhit"}' http://localhost:8080/register

# login
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"shobhit","password":"shobhit"}' http://localhost:8080/user

The following services need the user to send the token in X-Auth-Token header field

# List all contacts
curl -i -H "Content-Type: application/json" -H "X-Auth-Token: " -X GET http://localhost:8080/contacts

# Create contact
curl -i -H "Content-Type: application/json" -H "X-Auth-Token: " -X POST -d '{"first_name":"John","last_name":"Doe"}' http://localhost:8080/contacts

# Update contact
curl -i -H "Content-Type: application/json" -H "X-Auth-Token: " -X POST -d '{"first_name":"Johnny","last_name":"Mark"}' http://localhost:8080/contacts/1

# Add phone to contact
curl -i -H "Content-Type: application/json" -H "X-Auth-Token: " -X POST -d '{"phone":"+1 (555) 123-456"}' http://localhost:8080/contacts/1/entries

# Delete contact
curl -i -H "Content-Type: application/json" -H "X-Auth-Token: " -X DELETE http://localhost:8080/contacts/1