package com.tms.news.kafka;

import com.tms.news.core.constants.KafkaConstants;
import com.tms.news.core.mappers.ArticleMapper;
import com.tms.news.core.mappers.CategoryMapper;
import com.tms.news.core.mappers.PortalMapper;
import com.tms.news.core.models.entities.Article;
import com.tms.news.core.models.entities.Category;
import com.tms.news.core.models.entities.Portal;
import com.tms.news.core.models.kafka.NewsMessage;
import com.tms.news.repositories.ArticleRepository;
import com.tms.news.repositories.CategoryRepository;
import com.tms.news.repositories.PortalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsPublisherConsumer {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final PortalMapper portalMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final PortalRepository portalRepository;

    @KafkaListener(topics = KafkaConstants.PUBLISHER_TOPIC )
    public void consumerNews(NewsMessage newsMessage) {
        log.info("News message received");

       var savedArticles = articleRepository.findAll();
       var savedPortals = portalRepository.findAll();
       var savedCategories = categoryRepository.findAll();

       var entitiesToInsert = newsMessage
               .getNews()
               .stream()
               .filter(article -> savedArticles.stream().noneMatch(saved->saved.getTitle().equals(article)))
               .toList();

        if(entitiesToInsert.isEmpty())
            return;

       Set<Portal> newPortal = entitiesToInsert
               .stream()
               .filter(p->savedPortals.stream().noneMatch(x->p.portal().equals(x.getName())))
               .map(p->portalMapper.kafkaMessageToEntity(p))
               .collect(Collectors.toSet());

       Set<Category> incomingCategoryNames = entitiesToInsert.stream()
               .flatMap(x-> Arrays.stream(x.category().split(","))
                       .map(c->c.trim()))// Arrays.stream(Arrays.stream(x.portal().split(",")).map(s->s.trim()))
               .filter(c->savedCategories.stream().noneMatch(y->y.getName().equals(c)))
               .map(c->categoryMapper.kafkaMessageToEntity(c))
               .collect(Collectors.toSet());

       if(!newPortal.isEmpty()) {
           log.info("Portal saved to database");
           portalRepository.saveAll(newPortal);
           savedPortals.addAll(newPortal);
       }

       if(!incomingCategoryNames.isEmpty()) {
           log.info("Categories saved to database");
           categoryRepository.saveAll(incomingCategoryNames);
           savedCategories.addAll(incomingCategoryNames);
       }

       List<Article> entities = entitiesToInsert
               .stream()
               .map(x->{
                   var entity = articleMapper.kafkaMessageToEntity(x);
                   List<String> categories = Arrays.stream(x.category().split(",")).map(m->m.trim()).toList();
                   Portal portal = savedPortals.stream().filter(p->p.getName().equals(x.portal())).findFirst().orElseThrow();
                   entity.setCategories(savedCategories.stream().filter(c->categories.contains(c.getName())).toList());
                   entity.setPortal(portal);
                   return entity;
               }).toList();

        log.info("Articles saved to database");
               if(!entities.isEmpty()){
                   articleRepository.saveAll(entities);
               }

    }
}
