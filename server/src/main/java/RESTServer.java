/*
 * Copyright 2016 ikidou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.gson.Gson;
import db.DB;
import entity.Resp;
import handler.BlogHandler;
import handler.FormHandler;
import handler.HeaderHandler;
import spark.*;
import transformer.GsonTransformer;

import java.sql.SQLException;

public class RESTServer {
    static final String TYPE = "application/json; charset=UTF-8";

    public static void main(String[] args) throws SQLException {
        //数据库初始化
        DB.init();

        //http://localhost:4567/blog/?
        Spark.port(3434);
        Spark.init();

        //http://localhost:3434/blog
        Spark.get("/blog", BlogHandler.GET, GsonTransformer.getDefault());
        Spark.get("/blog/:id", BlogHandler.GET, GsonTransformer.getDefault());

        Spark.get("/zy", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Route(zy)";
            }
        }, GsonTransformer.getDefault());


        Spark.post("/blog", BlogHandler.POST, GsonTransformer.getDefault());
        Spark.post("/blog/:id", BlogHandler.POST, GsonTransformer.getDefault());

        Spark.put("/blog", BlogHandler.PUT, GsonTransformer.getDefault());
        Spark.put("/blog/:id", BlogHandler.PUT, GsonTransformer.getDefault());

        Spark.delete("/blog", BlogHandler.DELETE, GsonTransformer.getDefault());
        Spark.delete("/blog/:id", BlogHandler.DELETE, GsonTransformer.getDefault());

        Spark.head("/blog", BlogHandler.HEAD, GsonTransformer.getDefault());
        Spark.head("/blog/:id", BlogHandler.HEAD, GsonTransformer.getDefault());

        //TRACE
        Spark.trace("/blog", BlogHandler.TRACE, GsonTransformer.getDefault());
        Spark.trace("/blog/:id", BlogHandler.TRACE, GsonTransformer.getDefault());

        //OPTIONS
        Spark.options("/blog", BlogHandler.OPTIONS, GsonTransformer.getDefault());
        Spark.options("/blog/:id", BlogHandler.OPTIONS, GsonTransformer.getDefault());


        Spark.post("/form", new FormHandler(), GsonTransformer.getDefault());

        Spark.get("/headers", new HeaderHandler(), GsonTransformer.getDefault());

        Spark.after(new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                response.type(TYPE);
                response.header("author", "zy");
                response.header("Connection", "close");
                //response.header("author", "zy");
            }
        });

        Spark.exception(RuntimeException.class, new ExceptionHandler() {
            @Override
            public void handle(Exception exception, Request request, Response response) {
                Gson gson = new Gson();
                System.out.println("RuntimeException-" + exception);
                exception.printStackTrace();
                response.body(gson.toJson(Resp.create(400, exception.getMessage())));
            }
        });
        Spark.exception(Exception.class, new ExceptionHandler() {
            @Override
            public void handle(Exception exception, Request request, Response response) {
                Gson gson = new Gson();
                System.out.println("Exception-" + exception);
                exception.printStackTrace();
                response.body(gson.toJson(Resp.create(500, exception.getMessage())));
            }
        });
    }
}
