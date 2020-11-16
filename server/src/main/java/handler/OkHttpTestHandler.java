package handler;

import entity.OkHttpResp;
import spark.Request;
import spark.Response;
import spark.Route;
import util.RequestShow;


public enum OkHttpTestHandler implements Route {
    GET {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("GET...");
            String body = request.body();
            RequestShow.show(request, body);
            return OkHttpResp.create(200, "OK", "GET from Server,Your Msg is :" + body);
        }
    },

    POST {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            try {
                System.out.println("post...");
                String body = request.body();
                System.out.println("body:" + body);
                RequestShow.show(request, body);
                return OkHttpResp.create(200, "OK", "POST from Server,Your Msg is :" + body);
            } catch (Exception e) {
                return OkHttpResp.create(404, e.toString());
            }
        }
    };
}
