package com.tms.dataprovider.core.dataclients.units;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.dataprovider.configurations.TaskConfiguration;
import com.tms.dataprovider.core.dataclients.BaseDataClient;
import com.tms.dataprovider.core.enums.Param;
import com.tms.dataprovider.core.exceptions.DataServiceException;
import com.tms.dataprovider.core.interfaces.units.CnnNewsDetailUnit;
import com.tms.dataprovider.core.models.application.RequestParam;
import com.tms.dataprovider.core.models.dtos.ArticleDto;
import com.tms.dataprovider.core.models.dtos.Item;
import com.tms.dataprovider.core.models.parsers.cnn.ArticleCnn;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.HttpMethod;
import lombok.SneakyThrows;
import org.apache.hc.core5.http.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;

@Component
public class CnnNewsDetailUnitImpl
        extends BaseDataClient<String, ArticleCnn>
        implements CnnNewsDetailUnit {

    private final TaskConfiguration taskConfiguration;

    public CnnNewsDetailUnitImpl(TaskConfiguration taskConfiguration,
                                 HttpClient client) {
        super(client, taskConfiguration.getCnnWorldUrl(), Method.GET);
        this.taskConfiguration = taskConfiguration;
    }

    @Override
    @SneakyThrows
    protected ArticleCnn Converter(String body) {
        Document doc = Jsoup.parse(body);
        Element element = doc
                .getElementsByAttributeValue("type", "application/ld+json")
                .first();

        if (element == null) {
            return null;
        }
        ObjectMapper om = new ObjectMapper();
        om.findAndRegisterModules();
        System.out.println(element.data());

        return Arrays.stream(om.readValue(element.data(), ArticleCnn[].class))
                .findFirst()
                .orElseThrow();
    }

    @Override
    protected void setParameters(List<RequestParam> params, String model) {
        params.add(new RequestParam("override_url", taskConfiguration.getCnnUrl() + model, Param.OVERRIDE_URL));
    }

    @Override
    public ArticleCnn getArticle(String url) throws DataServiceException, IOException, URISyntaxException, InterruptedException {
        return execute(url);
    }
}
