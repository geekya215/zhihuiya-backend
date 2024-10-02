package io.geekya215.zhihuiya.request;

import java.util.Objects;

public class TokenRequest {
    private String clientId;
    private String clientSecret;

    public TokenRequest() {
    }

    public TokenRequest(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenRequest that = (TokenRequest) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(clientSecret, that.clientSecret);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(clientId);
        result = 31 * result + Objects.hashCode(clientSecret);
        return result;
    }

    @Override
    public String toString() {
        return "TokenRequest{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                '}';
    }
}
