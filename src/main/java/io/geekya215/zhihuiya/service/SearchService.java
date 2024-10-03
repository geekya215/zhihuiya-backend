package io.geekya215.zhihuiya.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.geekya215.zhihuiya.request.QuerySearchRequest;
import io.geekya215.zhihuiya.response.CommonResponse;
import io.geekya215.zhihuiya.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    static final String QUERY_SEARCH_PATENT_PATH = "/search/patent/query-search-patent";
    static final String SIMPLE_BIBLIOGRAPHY_PATH = "/basic-patent-data/simple-bibliography";
    static final String HOST = "https://connect.zhihuiya.com";

    public Object querySearchPatent(QuerySearchRequest request, String apikey, String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + token);

        Map<String, String> querys = new HashMap<>();
        querys.put("apikey", apikey);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        try {
            String json = objectMapper.writeValueAsString(request);
            HttpResponse response = HttpUtils.doPost(HOST, QUERY_SEARCH_PATENT_PATH, null, headers, querys, json);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            return CommonResponse.fail(68300007, "存在错误的请求!");
        }
    }

    public Object getSimpleBibliography(QuerySearchRequest request, String apiKey, String token) {
        Object patents = querySearchPatent(request, apiKey, token);
        if (patents instanceof CommonResponse<?>) {
            return patents;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        try {
            JsonNode jsonNode = objectMapper.readTree(((String) patents));
            boolean status = jsonNode.get("status").asBoolean();

            if (!status) {
                return patents;
            } else {
                JsonNode data = jsonNode.get("data");
                JsonNode results = data.get("results");

                List<String> patentIds = new ArrayList<>();
                for (JsonNode result : results) {
                    patentIds.add(result.get("patent_id").asText());
                }
                String queryParams = String.join(",", patentIds);

                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("authorization", "Bearer " + token);

                Map<String, String> querys = new HashMap<>();
                querys.put("patent_id", queryParams);
                querys.put("apikey", apiKey);

                HttpResponse response = HttpUtils.doGet(HOST, SIMPLE_BIBLIOGRAPHY_PATH, null, headers, querys);
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            return CommonResponse.fail(68300007, "存在错误的请求!");
        }
    }
}
