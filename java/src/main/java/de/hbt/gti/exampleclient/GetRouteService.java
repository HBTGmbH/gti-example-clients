package de.hbt.gti.exampleclient;

import de.hbt.gti.client.GetRouteControllerApi;
import de.hbt.gti.invoker.ApiClient;
import de.hbt.gti.invoker.ApiException;
import de.hbt.gti.model.GRRequest;
import de.hbt.gti.model.GRResponse;
import de.hbt.gti.model.SDName;

public class GetRouteService {
  private final ApiClientService apiClientService;

  public GetRouteService() {
    apiClientService = new ApiClientService();
  }

  public GRResponse getRoute(SDName start, SDName dest) throws ApiException {
    ApiClient apiClient = apiClientService.getApiClient();

    GetRouteControllerApi api = new GetRouteControllerApi(apiClient);
    GRRequest grRequest = new GRRequest();
    grRequest.setStart(start);
    grRequest.setDest(dest);
    grRequest.setLanguage("de");
    grRequest.setVersion(53);
    GRResponse response = api.getRoute(grRequest);
    System.out.println(response);
    return response;
  }
}
