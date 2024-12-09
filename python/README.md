# Sample Implementation of a GTI client in Python

## Installing dependencies

If you have not done so already, install poetry. Afterward, call `poetry install` from inside the python directory
and subsequently `poetry shell` to start a shell with activated virtual environment.

## Executing the sample application
To execute the sample application, you will need to set environment variables containing the GTI-credentials.
```
GTI_USER=<user> GTI_HMAC_SECRET=<hmac secret> python client.py
```
Open http://localhost:8080

You can request GTI credentials at https://www.hvv.de/de/fahrplaene/abruf-fahrplaninfos/datenabruf

## Documentation
* https://gti.geofox.de/
