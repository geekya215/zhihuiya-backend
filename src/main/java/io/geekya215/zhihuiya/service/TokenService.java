package io.geekya215.zhihuiya.service;

import io.geekya215.zhihuiya.response.CommonResponse;
import io.geekya215.zhihuiya.util.HttpUtils;
import io.geekya215.zhihuiya.request.TokenRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    static final String OAUTH_PATH = "/oauth/token";

    public Object getToken(TokenRequest tokenRequest) {
        String host = "https://" + tokenRequest.getClientId() + ":" + tokenRequest.getClientSecret() + "@connect.zhihuiya.com";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("grant_type", "client_credentials");

        try {
            HttpResponse response = HttpUtils.doPost(host, OAUTH_PATH, null, headers, null, requestBody);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            return CommonResponse.fail(68300008, "服务中断异常，请稍后再试!");
        }
    }
}
