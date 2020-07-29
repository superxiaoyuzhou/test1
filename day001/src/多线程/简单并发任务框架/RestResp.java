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
