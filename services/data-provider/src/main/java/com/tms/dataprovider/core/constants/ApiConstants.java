package com.tms.dataprovider.core.constants;

public final class ApiConstants {
    public static final String API_PREFIX_V1 = "/api/v1/dataprovider";

    public static class Task {
        public static final String API_LIST_TASK_BY_DATE = "/tasks/date/{date}";
        public static final String API_LIST_TASK_BY_STATUS = "/tasks/status/{status}";
        public static final String API_LIST_TASK = "/tasks";
    }
    public static class Source {
        public static final String API_LIST_SOURCES = "/sources";
        public static final String API_LIST_SOURCES_BY_TASK_ID = "/sources/task/{taskId}";
        public static final String API_SOURCE_BY_ID = "/source/{id}";
    }
}
