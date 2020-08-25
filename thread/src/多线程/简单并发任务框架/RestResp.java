package 多线程.简单并发任务框架;

import java.io.Serializable;

public class RestResp<T> implements Serializable {
    private ResultType resultType;
    private String respMsg;
    private T data;

    public RestResp() {
    }


    public String getRespMsg() {
        return this.respMsg;
    }

    public T getData() {
        return this.data;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public RestResp(ResultType resultType, String respMsg, T data) {
        this.resultType = resultType;
        this.respMsg = respMsg;
        this.data = data;
    }

    public static <T> RestResp<T> restRespSuccess(T data){
        return new RestResp<>(ResultType.SUCCESS, "成功", data);
    }
    public static <T> RestResp<T> restRespSuccess(String respMsg, T data){
        return new RestResp<>(ResultType.SUCCESS, respMsg, data);
    }
    public static <T> RestResp<T> restRespFail(String respMsg, T data){
        return new RestResp<>(ResultType.FAIL, respMsg, data);
    }
    public static <T> RestResp<T> restRespFail(String respMsg){
        return new RestResp<>(ResultType.FAIL, respMsg, null);
    }
    public static <T> RestResp<T> restRespError(String respMsg){
        return new RestResp<>(ResultType.ERROR, respMsg, null);
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
