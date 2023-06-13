package de.hbt.gti.exampleclient;

import de.hbt.gti.client.CheckNameControllerApi;
import de.hbt.gti.invoker.ApiClient;
import de.hbt.gti.invoker.ApiException;
import de.hbt.gti.model.CNRequest;
import de.hbt.gti.model.CNResponse;
import de.hbt.gti.model.SDName;
import de.hbt.gti.model.SDName.TypeEnum;

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
