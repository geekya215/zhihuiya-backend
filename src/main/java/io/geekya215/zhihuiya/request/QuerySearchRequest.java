package io.geekya215.zhihuiya.request;

import java.util.Objects;

public class QuerySearchRequest {
    private String queryText;
    private int limit;
    private int offset;

    public QuerySearchRequest() {
    }

    public QuerySearchRequest(String queryText, int limit, int offset) {
        this.queryText = queryText;
        this.limit = limit;
        this.offset = offset;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuerySearchRequest that = (QuerySearchRequest) o;
        return limit == that.limit && offset == that.offset && Objects.equals(queryText, that.queryText);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(queryText);
        result = 31 * result + limit;
        result = 31 * result + offset;
        return result;
    }

    @Override
    public String toString() {
        return "QuerySearchRequest{" +
                "queryText='" + queryText + '\'' +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
