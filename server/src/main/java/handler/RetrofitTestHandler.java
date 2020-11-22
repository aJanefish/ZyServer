package handler;

import com.j256.ormlite.dao.Dao;
import db.DB;
import entity.Blog;
import entity.OkHttpResp;
import spark.Request;
import spark.Response;
import spark.Route;
import util.RequestShow;

import java.sql.SQLException;
import java.util.List;

import static com.j256.ormlite.dao.DaoManager.createDao;

//Retrofit 请求框架的服务端
public enum RetrofitTestHandler implements Route {
    GET_ALL {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("GET_ALL...");
            String body = request.body();
            RequestShow.show(request, body);
            //find all Blog
            List<Blog> list = getDao().queryForAll();
            return OkHttpResp.create(200, "OK", "all.do", list);
        }
    }, POST_ALL {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("POST_ALL...");
            String body = request.body();
            RequestShow.show(request, body);
            //find all Blog
            List<Blog> list = getDao().queryForAll();
            return OkHttpResp.create(200, "OK", "all.do", list);
        }
    },
    fish1 {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("GET fish1...");
            String body = request.body();
            RequestShow.show(request, body);
            List<Blog> list = getDao().queryForEq("author", "fish1");
            return OkHttpResp.create(200, "OK", "path fish1", list);
        }
    },

    fish2 {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("GET fish2...");
            String body = request.body();
            RequestShow.show(request, body);
            List<Blog> list = getDao().queryForEq("author", "fish2");
            return OkHttpResp.create(200, "OK", "path fish2", list);
        }
    },
    QUERY {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("GET QUERY...");
            String body = request.body();
            RequestShow.show(request, body);
            long id = getId(request);
            Blog blog = getDao().queryForId(id);
            return OkHttpResp.create(200, "OK", "path fish2", blog);
        }
    }, ADD {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("ADD ...");
            String body = request.body();
            RequestShow.show(request, body);
            return OkHttpResp.create(200, "OK", "ADD", "ADD");
        }
    }, LOGIN {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("LOGIN ...");
            String body = request.body();
            RequestShow.show(request, body);
            return OkHttpResp.create(200, "OK", "LOGIN", "LOGIN:" + body);
        }
    }, REGISTER {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            System.out.println("REGISTER ...");
            String body = request.body();
            RequestShow.show(request, body);
            return OkHttpResp.create(200, "OK", "REGISTER", "REGISTER:" + body);
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

    public long getId(Request request) {
        String id = request.params("id");
        String idInQueryString = request.queryParams("id");

        if (id == null && idInQueryString == null) {
            return -1;
        } else if (id == null) {
            id = idInQueryString;
        }
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("bad id:`" + id + "`");
        }
    }

    public Dao<Blog, Long> getDao() throws SQLException {
        return createDao(DB.getConnectionSource(), Blog.class);
    }
}
