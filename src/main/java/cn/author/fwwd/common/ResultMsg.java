package cn.author.fwwd.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class ResultMsg implements Serializable {

    private static final long serialVersionUID = -7522220974451590876L;

    public int code;

    public String msg;

    public Object  data;

    public Map<String, Object> extenal=new HashMap<String, Object>();

    public ResultMsg(){
        super();
    }

    public ResultMsg(int code){
        super();
        this.code=code;
    }

    public ResultMsg(int code,String msg){
        super();
        this.code=code;
        this.msg=msg;
    }

    public ResultMsg(int code,String msg,Object data){
        super();
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getExtenal() {
        return extenal;
    }

    public void setExtenal(Map<String, Object> extenal) {
        this.extenal = extenal;
    }

}
