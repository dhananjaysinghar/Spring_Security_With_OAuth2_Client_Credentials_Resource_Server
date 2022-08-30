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
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJraWQiOiJ3VTNpZklJYUxPVUFSZVJCL0ZHNmVNMVAxUU09IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJtYWVyc2tSZXN0QXBpIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1ZGl0VHJhY2tpbmdJZCI6ImQ4NTk3MTNjLWFmZmYtNGRmZC1hNDY1LTVjOWRjMzk2ZDk0Ni0zNzYxMDI5IiwiaXNzIjoiaHR0cHM6Ly9pYW0tY2R0Lm1hZXJzay5jb20vYWNtL29hdXRoMi9tYXUiLCJ0b2tlbk5hbWUiOiJhY2Nlc3NfdG9rZW4iLCJ0b2tlbl90eXBlIjoiQmVhcmVyIiwiYXV0aEdyYW50SWQiOiJ2aldFenFJY1lmYThaYWl0SGlfUEdKdWJZaXMiLCJhdWQiOiJtYWVyc2tSZXN0QXBpIiwibmJmIjoxNjYxODc1NTc2LCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIiwic2NvcGUiOlsiZ2Nzcy51c2VyLmFkbWluIiwib3BlbmlkIiwib3JnLm1hZXJzay5hcHAub3BlcmF0b3IiXSwiYXV0aF90aW1lIjoxNjYxODc1NTc2LCJyZWFsbSI6Ii9tYXUiLCJleHAiOjE2NjE4ODI3NzYsImlhdCI6MTY2MTg3NTU3NiwiZXhwaXJlc19pbiI6NzIwMCwianRpIjoiVkROQTBBejVNLWszcFZKcVdoU3M2UDZueGpVIn0.yp6xonlyEFRj6XLu6obyeVFWaQtsgm2Z8kW-XVQl6M8JatjBRPhHfMKqHBZyAqGFja0CoPtWCVZxa640YOS8TcWTd-rF7taNy7VDgw1c77rKq2z7fFBo7WBbD9jdhr7Cj8rj5aaWvwVegKXtEWUg1Ap-SLBmd6fNYdiCNA60W1-PyMVlx4BJsnAb6EjBRRgjfiGEF1fXGaEKfnI0CyAB0OJFaXSZ5JPoDeZdhYrL-l1i_cOtst2mym9UfklFuCV6QDrVM2O_cgcNT2EFspIBJYMYd2PAk_cR5W0ex-JlH1zyTJTR0pLRlD0ZxD5Ih4NjyEc77Ss7bWPmTZ2oUmm7DA'
~~~
