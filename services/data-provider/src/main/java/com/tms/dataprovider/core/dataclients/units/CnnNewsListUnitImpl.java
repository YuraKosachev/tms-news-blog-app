package com.tms.dataprovider.core.dataclients.units;

import com.tms.dataprovider.configurations.TaskConfiguration;
import com.tms.dataprovider.core.dataclients.BaseDataClient;
import com.tms.dataprovider.core.enums.Param;
import com.tms.dataprovider.core.exceptions.DataServiceException;
import com.tms.dataprovider.core.interfaces.units.CnnNewsListUnit;
import com.tms.dataprovider.core.models.application.RequestParam;
import com.tms.dataprovider.core.models.dtos.ArticleDto;
import com.tms.dataprovider.core.models.dtos.Item;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CnnNewsListUnitImpl
        extends BaseDataClient<String, Set<String>>
        implements CnnNewsListUnit

{
    public CnnNewsListUnitImpl(TaskConfiguration taskConfiguration, HttpClient client) {
        super(client, taskConfiguration.getCnnUrl(), Method.GET);
    }

    @Override
    protected Set<String> Converter(String body) {
        Document doc = Jsoup.parse(body);
        Elements links = doc.select("a[data-link-type=\"article\"]");
        Set<String> linkSet = new HashSet<>();

        for (Element link : links)
        {
           linkSet.add(link.attr("href"));
        }
        return linkSet;
    }

    @Override
    protected void setParameters(List<RequestParam> params, String model) {
        params.add(new RequestParam("override_url", model, Param.OVERRIDE_URL));
    }

    @Override
    public Set<String> getCnnNewsList(String url)
            throws DataServiceException, IOException, URISyntaxException, InterruptedException {
        return execute(url);
    }
}
