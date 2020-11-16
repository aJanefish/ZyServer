package util;

import spark.Request;
import spark.Response;

import java.util.Set;

public class RequestShow {

    //GET https://www.baidu.com/s?ie=utf-8&mod=1&isbd=1&isid=836e75bd000c217f&ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=88093251_41_hao_pg&wd=fiddler%20%E6%8A%93%E5%8F%96loopback&fenlei=256&oq=fiddler%2520%25E6%258A%2593%25E5%258F%2596loopback&rsv_pq=836e75bd000c217f&rsv_t=ae67WCwjveXms4JxzE%2BkuzsefnqSbJroFClkIIS18FXAixmuBGeQNZIoJD1iVSB3f07Hsoi0Bexu&rqlang=cn&rsv_enter=0&rsv_dl=tb&rsv_btype=t&bs=fiddler%20%E6%8A%93%E5%8F%96loopback&rsv_sid=&_ss=1&clist=&hsug=&f4s=1&csor=18&_cr1=35567 HTTP/1.1
    //Host: www.baidu.com
    //Connection: keep-alive
    //Accept: */*
    //is_xhr: 1
    //X-Requested-With: XMLHttpRequest
    public static void show(Request request, String body) {
        p("----------------------------------------------");

        //打印请求行
        p(request.requestMethod() + " " + request.url() + "?" + request.queryString() + " " + request.protocol());

        //打印请求头
        Set<String> headers = request.headers();
        for (String header : headers) {
            p(header + ": " + request.headers(header));
        }
        //打印空格
        p();
        //请求正文

        p(body);
        p("----------------------------------------------\n");

    }

    //HTTP/1.1 200 OK
    //Bdpagetype: 3
    //Bdqid: 0xa3ea6598000616c1
    //Cache-Control: private
    //Connection: keep-alive
    //Content-Encoding: gzip
    //
    //正文
//    public static void show(Response response, String body) {
//        //打印响应行
//        p(response.protocol() + " " + response.code() + " " + response.message());
//        //打印请求头
//        Headers headers = response.headers();
//        p(headers);
//        //打印空格
//        p();
//        //请求正文
//        p(body);
//    }
    public static void p(Object o) {
        System.out.println(o);
    }

    public static void p() {
        System.out.println();
    }
}
