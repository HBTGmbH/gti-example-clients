package de.hbt.gti.exampleclient;

import de.hbt.gti.example.client.CheckNameControllerApi;
import de.hbt.gti.example.invoker.ApiClient;
import de.hbt.gti.example.invoker.ApiException;
import de.hbt.gti.example.model.CNRequest;
import de.hbt.gti.example.model.CNResponse;
import de.hbt.gti.example.model.SDName;
import de.hbt.gti.example.model.SDName.TypeEnum;

public class CheckNameService {
  private final ApiClientService apiClientService;

  public CheckNameService() {
    apiClientService = new ApiClientService();
  }

  public CNResponse checkname(String name) throws ApiException {
    ApiClient apiClient = apiClientService.getApiClient();
    CheckNameControllerApi api = new CheckNameControllerApi(apiClient);
    CNRequest req = new CNRequest();
    req.setLanguage("de");
    req.setVersion(53);
    SDName sdName = new SDName();
    sdName.setName(name);
    sdName.setType(TypeEnum.UNKNOWN);
    req.setTheName(sdName);
    CNResponse response = api.checkName(req);
    System.out.println(response);
    return response;
  }
}
