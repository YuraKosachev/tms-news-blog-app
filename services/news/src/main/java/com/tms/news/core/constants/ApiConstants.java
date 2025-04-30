package com.tms.news.core.constants;

public final class ApiConstants {
    public static final String API_PREFIX_V1 = "/api/v1/news";
    public static class Article{
        public static final String API_ARTICLE_CREATE_UPDATE = "/article";
        public static final String API_ARTICLE_DETAIL_DELETE = "/article/{id}";
        public static final String API_SHORT_ARTICLE_LIST_PAGE = "/short/articles";
//        public static final String API_SHORT_ARTICLE_LIST_PAGE = "/short/articles/page/{page}/size/{size}";
//        public static final String API_SHORT_ARTICLE_LIST_PAGE_SORT = "/short/articles/page/{page}/size/{size}/field/{field}/direction/{direction}";
    }
    public static class Category{
        public static final String API_CATEGORY_CREATE_UPDATE = "/category";
        public static final String API_CATEGORY_DETAIL_DELETE = "/category/{id}";
        public static final String API_CATEGORY_LIST = "/categories";
    }

    public static class Portal{
        public static final String API_PORTAL_LIST = "/portals";
        public static final String API_PORTAL_CREATE_UPDATE = "/portal";
        public static final String API_PORTAL_GET_AND_DELETE = "/portal/{id}";
    }

    public static class Comment{
        public static final String API_COMMENT_CREATE_UPDATE = "/comment";
        public static final String API_COMMENT_GET_DELETE = "/comment/{id}";

        public static final String API_ARTICLE_COMMET_LIST = "/article/{id}/comments";
    }
}
