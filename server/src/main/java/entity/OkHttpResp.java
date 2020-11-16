package entity;

public class OkHttpResp {
    public int code;
    public String msg;
    public String des;
    public Object data;

    public static OkHttpResp create(int code, String msg) {
        return create(code, msg, null);
    }

    public static OkHttpResp create(int code, String msg, Object data) {
        OkHttpResp resp = new OkHttpResp();
        resp.code = code;
        resp.msg = msg;
        resp.data = data;
        return resp;
    }

    public static OkHttpResp create(int code, String msg, String des, Object data) {
        OkHttpResp resp = new OkHttpResp();
        resp.code = code;
        resp.msg = msg;
        resp.des = des;
        resp.data = data;
        return resp;
    }
}
