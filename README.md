#### DOC_LINK: https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html
#### KEYCLOAK_SETUP: https://github.com/dhananjaysinghar/KeyCloak-for-OAuth2-client-credentials-flow

## KEY_CLOAK_CLIENT_CREDENTIALS_ENDPOINT
~~~
curl --location --request POST 'http://localhost:8180/realms/realspeed/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=realspeedApi' \
--data-urlencode 'client_secret=7gaXuSygNR8SLrQs4MpMrd7svxdHRoFn' \
--data-urlencode 'grant_type=client_credentials'
~~~

## SERVICE ENDPOINT
~~~
curl --location --request GET 'http://localhost:8080/api/hello' \
--header 'Authorization: Bearer {{access_token}}'
~~~
