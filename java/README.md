# Sample Implementation of a GTI client in JAVA

## Generating and building the client 

1. Generate the client code. 
This will download the OpenAPI-specification and generate a client
```
     ./gradlew generateGtiClient
```
OpenAPI does not support HMAC signatures for requests. This had to be implemented in the ApiClientService class.

2. Build and publish the client (to your local maven repository). This  will build the client library and publish it into your local maven repository
```
    cd ./build/api-client-generated
    mvn clean install
    cd ../..
```

## Executing the sample application
To execute the sample application, you will need to set environment variables containing the GTI-credentials.
```
    GTI_USER=<user> GTI_HMAC_SECRET=<hmac secret> ./gradlew run
```

You can get credentials from https://www.hvv.de/de/fahrplaene/abruf-fahrplaninfos/datenabruf

## Documentation
* https://gti.geofox.de/
