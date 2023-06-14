# Sample Implementation of a GTI client in JAVA

## Generating and building the client 

This will download the OpenAPI-specification, generate a client and compile the sources
```
     ./gradlew build
```

## Executing the sample application
To execute the sample application, you will need to set environment variables containing the GTI-credentials.
```
    GTI_USER=<user> GTI_HMAC_SECRET=<hmac secret> ./gradlew run
```

You can request for credentials at https://www.hvv.de/de/fahrplaene/abruf-fahrplaninfos/datenabruf

## Documentation
* https://gti.geofox.de/
