package com.tms.dataprovider.core.interfaces.apiclient;

import com.tms.dataprovider.core.exceptions.DataServiceException;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface DataClient<TRequest,TResponse> {
    TResponse execute(TRequest model) throws URISyntaxException, IOException, InterruptedException, DataServiceException;
}