package com.tms.dataprovider.core.interfaces.units;

import com.tms.dataprovider.core.exceptions.DataServiceException;
import com.tms.dataprovider.core.models.dtos.ArticleDto;
import com.tms.dataprovider.core.models.parsers.cnn.ArticleCnn;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CnnNewsDetailUnit {
    ArticleCnn getArticle(String url) throws DataServiceException, IOException, URISyntaxException, InterruptedException;
}
