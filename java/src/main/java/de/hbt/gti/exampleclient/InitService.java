package de.hbt.gti.exampleclient;

import de.hbt.gti.example.client.InitRequestControllerApi;
import de.hbt.gti.example.invoker.ApiClient;
import de.hbt.gti.example.invoker.ApiException;
import de.hbt.gti.example.model.InitRequest;
import de.hbt.gti.example.model.InitResponse;

public class InitService {

  private final ApiClientService apiClientService;

  public InitService() {
    apiClientService = new ApiClientService();
  }

  public InitResponse init() throws ApiException {
    ApiClient apiClient = apiClientService.getApiClient();
    InitRequestControllerApi api = new InitRequestControllerApi(apiClient);
    InitRequest initRequest = new InitRequest();
    initRequest.setLanguage("de");
    initRequest.setVersion(53);
    return api.init(initRequest);
  }

}
