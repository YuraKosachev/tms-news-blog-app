package com.tms.dataprovider.core.dataclients;

import com.tms.dataprovider.core.enums.Param;
import com.tms.dataprovider.core.exceptions.DataServiceException;
import com.tms.dataprovider.core.interfaces.apiclient.DataClient;
import com.tms.dataprovider.core.models.application.RequestParam;
import com.tms.dataprovider.core.models.application.RequestSetting;
import org.apache.hc.core5.http.Method;
import org.springframework.http.HttpStatusCode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseDataClient<TRequest, TResponse> implements DataClient<TRequest, TResponse> {

    private HttpClient client;
    private String url;
    private Method method;

    public BaseDataClient(HttpClient client, String url, Method method) {
        this.client = client;
        this.url = url;
        this.method = method;
    }


    @Override
    public TResponse execute(TRequest model) throws IOException, URISyntaxException, InterruptedException, DataServiceException {
        //HttpClient client = HttpClient.newHttpClient();

        RequestSetting setting = getRequestSetting(url, model);


        URL uri = new URL(setting.getEndpoint());
        HttpRequest.BodyPublisher bodyPublisher = setting.getBody() == null || method.equals(Method.GET)
                ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(setting.getBody());

        HttpRequest.Builder builderRequest = HttpRequest.newBuilder()
                .uri(uri.toURI())
                .method(method.toString(), bodyPublisher);

        if (!setting.getHeaders().isEmpty()) {
            builderRequest.headers(setting.getHeaders().toArray(String[]::new));
        }

        HttpRequest request = builderRequest.build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return responseHandler(response);
    }

    private RequestSetting getRequestSetting(String endpoint, TRequest model) {
        List<RequestParam> requestParams = new ArrayList<>();
        setParameters(requestParams, model);

        List<String> bodyBuilder = new ArrayList<>();
        List<String> headersBuilder = new ArrayList<>();
        List<String> endpointBuilder = new ArrayList<>();

        String body = null;

        for (RequestParam param : requestParams) {

            if (param.getParam() == Param.BODY) {
                Object val = param.getValue();
                if (val instanceof String)
                    bodyBuilder.add("\"" + param.getName() + "\":\"" + param.getValue() + "\"");
                else
                    bodyBuilder.add("'" + param.getName() + "':" + param.getValue());
            }
            if (param.getParam() == Param.HEAD) {
                headersBuilder.add(param.getName());
                headersBuilder.add(param.getValue().toString());
            }
            if (param.getParam() == Param.QUERY) {
                endpointBuilder.add(param.getName() + "=" + param.getValue());
            }
            if(param.getParam() == Param.OVERRIDE_URL) {
                endpoint = param.getValue().toString();
            }
        }

        if (!endpointBuilder.isEmpty()) {
            endpoint = endpoint + "?" + String.join("&", endpointBuilder);
        }

        if (!bodyBuilder.isEmpty()) {
            body = "{" + String.join(",", bodyBuilder) + "}";
        }

        return new RequestSetting(body, endpoint, headersBuilder);
    }

    protected void setParameters(List<RequestParam> params, TRequest model) {
    }


    protected TResponse responseHandler(HttpResponse<String> response) throws DataServiceException {
        if (!(response.statusCode() >= 200 && response.statusCode() < 300) && !canHandling(response)) {
            throw new DataServiceException(response.statusCode(), "Something went wrong! status code: " + response.statusCode());
        }
        return Converter(response.body());
    }

    protected abstract <TResponse> TResponse Converter(String body);

    protected boolean canHandling(HttpResponse<String> response) {
        return false;
    }


}
