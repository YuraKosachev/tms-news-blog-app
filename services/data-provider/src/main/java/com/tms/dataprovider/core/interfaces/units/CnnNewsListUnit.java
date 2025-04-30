package com.tms.dataprovider.core.interfaces.units;

import com.tms.dataprovider.core.exceptions.DataServiceException;
import com.tms.dataprovider.core.models.dtos.Item;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

public interface CnnNewsListUnit {
    Set<String> getCnnNewsList(String url) throws DataServiceException, IOException, URISyntaxException, InterruptedException;
}
