package io.geekya215.zhihuiya.response;

import java.util.Objects;

public class CommonResponse<T> {
    private T data;
    private boolean status;
    private String errorMsg;
    private int errorCode;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(data, true, null, 0);
    }

    public static <T> CommonResponse<T> fail(int errorCode, String errorMsg) {
        return new CommonResponse<>(null, false, errorMsg, errorCode);
    }

    public CommonResponse() {
    }

    public CommonResponse(T data, boolean status, String errorMsg, int errorCode) {
        this.data = data;
        this.status = status;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonResponse<?> that = (CommonResponse<?>) o;
        return status == that.status && errorCode == that.errorCode && Objects.equals(data, that.data) && Objects.equals(errorMsg, that.errorMsg);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(data);
        result = 31 * result + Boolean.hashCode(status);
        result = 31 * result + Objects.hashCode(errorMsg);
        result = 31 * result + errorCode;
        return result;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "data=" + data +
                ", status=" + status +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}