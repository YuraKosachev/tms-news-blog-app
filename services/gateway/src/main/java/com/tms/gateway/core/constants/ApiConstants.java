package com.tms.gateway.core.constants;

public final class ApiConstants {
    public static final String API_PREFIX_NEWS_GATEWAY = "/api/news-service";
    public static final String API_PREFIX_DATA_PROVIDER_GATEWAY = "/api/data-provider-service";
    public static final String API_PREFIX_NOTIFICATION_GATEWAY = "/api/notification-service";
    public static final String API_PREFIX_NEWS_V1 = "/api/v1/news";
    public static final String API_PREFIX_DATA_PROVIDER_V1 = "/api/v1/dataprovider";
    public static final String API_PREFIX_NOTIFICATION_V1 = "/api/v1/notification";

    public static class Article {
        public static final String API_ARTICLE_CREATE_UPDATE = "/article";
        public static final String API_ARTICLE_DETAIL_DELETE = "/article/{id}";
        public static final String API_SHORT_ARTICLE_LIST_PAGE = "/short/articles";
        public static final String GATEWAY_API_ARTICLE_DETAIL_DELETE = "/article/delete/{id}";
    }

    public static class Notification {
        public static final String API_GET_LIST_BY_DATE = "/bydate/{date}";
    }

    public static class Category {
        public static final String API_CATEGORY_CREATE_UPDATE = "/category";
        public static final String API_CATEGORY_DETAIL_DELETE = "/category/{id}";
        public static final String GATEWAY_CATEGORY_DELETE= "/delete/category/{id}";
        public static final String API_CATEGORY_LIST = "/categories";
    }

    public static class Portal {
        public static final String API_PORTAL_LIST = "/portals";
        public static final String API_PORTAL_CREATE_UPDATE = "/portal";
        public static final String API_PORTAL_GET_AND_DELETE = "/portal/{id}";
    }

    public static class Comment {
        public static final String API_COMMENT_CREATE_UPDATE = "/comment";
        public static final String API_COMMENT_GET_DELETE = "/comment/{id}";
        public static final String GATEWAY_COMMENT_GET_DETAIL = "/detail/comment/{id}";
        public static final String API_ARTICLE_COMMET_LIST = "/article/{id}/comments";
    }

    public static class Task {
        public static final String PREFIX = "/tasks";
        public static final String API_LIST_TASK_BY_DATE = PREFIX + "/date/{date}";
        public static final String API_LIST_TASK_BY_STATUS = PREFIX + "/status/{status}";
        public static final String API_LIST_TASK = "/tasks";
    }

    public static class Source {
        public static final String API_LIST_SOURCES = "/sources";
        public static final String API_LIST_SOURCES_BY_TASK_ID = "/sources/task/{taskId}";
        public static final String API_SOURCE_BY_ID = "/source/{id}";
    }


    public static String[] onlyAdmin = {
            API_PREFIX_NOTIFICATION_GATEWAY + "/**",
            API_PREFIX_DATA_PROVIDER_GATEWAY + "/**",
            API_PREFIX_NEWS_GATEWAY + Portal.API_PORTAL_CREATE_UPDATE,
            API_PREFIX_NEWS_GATEWAY + Portal.API_PORTAL_GET_AND_DELETE.replace("{id}","**"),
            API_PREFIX_NEWS_GATEWAY + Portal.API_PORTAL_LIST,
            API_PREFIX_NEWS_GATEWAY + Category.GATEWAY_CATEGORY_DELETE.replace("{id}","**"),
    };

    public static String[] onlyAdminAndPubliher = {
            API_PREFIX_NEWS_GATEWAY + Article.API_ARTICLE_CREATE_UPDATE,
            API_PREFIX_NEWS_GATEWAY + Article.GATEWAY_API_ARTICLE_DETAIL_DELETE.replace("{id}","**"),
            API_PREFIX_NEWS_GATEWAY + Category.API_CATEGORY_CREATE_UPDATE + "/**",
    };

    public static String[] onlyAdminPubliherReader = {
            API_PREFIX_NEWS_GATEWAY + Comment.API_COMMENT_CREATE_UPDATE + "/**"
    };

    public static String[] freeUrls = {
            API_PREFIX_NEWS_GATEWAY + Category.API_CATEGORY_LIST,
            API_PREFIX_NEWS_GATEWAY + Category.API_CATEGORY_DETAIL_DELETE.replace("{id}","**"),
            API_PREFIX_NEWS_GATEWAY + Article.API_ARTICLE_DETAIL_DELETE.replace("{id}","**"),
            API_PREFIX_NEWS_GATEWAY + Article.API_SHORT_ARTICLE_LIST_PAGE,
            API_PREFIX_NEWS_GATEWAY + "**/comments",
            API_PREFIX_NEWS_GATEWAY + Comment.GATEWAY_COMMENT_GET_DETAIL.replace("{id}","**"),

    };
}
