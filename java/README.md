# Sample Implementation of a GTI client in JAVA

## Generating and building the client 

1. generate the client
```
     ./gradlew generateGtiClient
```
2. build and publish (locally) the client
```
    cd ./build/api-client-generated
    mvn clean install
    cd ../..
```

## Executing the sample application
Run the sample application
```
    GTI_USER=<user> GTI_HMAC_SECRET=<hmac secret> ./gradlew run
```

You can get credentials from ???@hbt.de or ???@hochbahn.de

## Documentation
* https://gti.geofox.de/