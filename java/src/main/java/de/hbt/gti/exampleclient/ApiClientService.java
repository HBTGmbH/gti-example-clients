package de.hbt.gti.exampleclient;

import static java.util.Optional.ofNullable;

import de.hbt.gti.example.invoker.ApiClient;
import java.io.IOException;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.ByteString;

/**
 * This class provides an ApiClient which creates HMAC signatures for the request.
 * This is currently not supported by OpenAPI and had to be implemented additionally.
 */
public class ApiClientService {
  private final String gtiUser;
  private final ByteString gtiHmacSecret;
  private final ApiClient apiClient;

  public ApiClientService() {
    gtiUser = System.getenv("GTI_USER");
    gtiHmacSecret = ofNullable(System.getenv("GTI_HMAC_SECRET"))
        .map(s -> ByteString.of(s.getBytes()))
        .orElseThrow(() -> new IllegalArgumentException("Environment variable GTI_HMAC_SECRET must be set"));
    apiClient = createApiClient();
  }

  private Response hmacInterceptorChain(Chain chain) throws IOException {
    Buffer sink = new Buffer();
    chain.request().body().writeTo(sink);
    ByteString hmac = sink.hmacSha1(gtiHmacSecret);
    Request request = chain.request().newBuilder()
        .addHeader("geofox-auth-signature", hmac.base64())
        .addHeader("geofox-auth-user", gtiUser)
        .build();
    return chain.proceed(request);
  }
  private ApiClient createApiClient() {
    ApiClient apiClient = new ApiClient();
    OkHttpClient httpClient = apiClient.getHttpClient()
        .newBuilder()
        .addInterceptor(chain -> hmacInterceptorChain(chain))
        .build();
    apiClient.setHttpClient(httpClient);
    return apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }
}
