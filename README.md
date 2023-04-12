# Town Hallr

This wonderful application allows you to create individual topics for our Town Meetings and vote for other questions accordingly, thereby increasing their visibility. You can register automatically via your company account and you're ready to go. Search for topics that are important to you, comment on questions and increase their visibility by using the Up Vote button. You can use the form to ask your own questions and assign them to departments and topics.

To start the application, you need Java 17, Maven and Docker.

First you need to set up the Keycloak instance with the following command:
`docker-compose -f ./keycloak-setup/docker-compose.yml up --build -d`

Then you can log in into the application. You will get automatically forwarded to the keycloak login screen, where you can sign up via your google account. Below you will find alternative ways to login without your Vaadin account.

- Keycloak Management Console can be opened in your browser with localhost:9000 and the credential are "admin" for 
  username and "admin" as the password (this configuration shouldn't be used in production)
- the vaadin application has two different user types with the following credentials:
  - type: user ("myuser"/"myuser")
  - type: admin ("myadmin"/"myadmin")
  and can be started in the browser with localhost:8080

On the admin page you can assign topics to according members of the leadership team and disable existing topics after their got answered. 

Enjoy and thank you for your vote!