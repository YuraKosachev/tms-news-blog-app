package com.tms.news.core.models.kafka;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsMessage {
    private List<NewsPublisher> news;
}