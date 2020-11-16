
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

package db;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.SqliteDatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;
import entity.Blog;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB {


    private static final String PATH = "Data/data.db";

    public static ConnectionSource getConnectionSource() throws SQLException {
        return new JdbcConnectionSource("jdbc:sqlite:" + PATH, new SqliteDatabaseType());
    }

    public static void init() {
        File dbFile = new File(PATH);
        ConnectionSource source = null;
        try {
            source = DB.getConnectionSource();
            //数据不存在新生成数据
            if (!dbFile.exists()) {
                File parentFile = dbFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                TableUtils.createTable(source, Blog.class);
                List<Blog> blogList = generateDefaultBlogList();
                Dao<Blog, Long> blogDao = DaoManager.createDao(source, Blog.class);
                DatabaseConnection databaseConnection = blogDao.startThreadConnection();
                boolean autoCommit = databaseConnection.isAutoCommit();
                databaseConnection.setAutoCommit(false);
                for (Blog blog : blogList) {
                    blogDao.create(blog);
                }
                blogDao.endThreadConnection(databaseConnection);
                blogDao.commit(databaseConnection);
                databaseConnection.setAutoCommit(autoCommit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (source != null) {
                    source.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //diy default data
    private static List<Blog> generateDefaultBlogList() {
        List<Blog> blogList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Blog blog = new Blog();
            blog.author = "fish" + i;
            blog.title = "retrofit" + (i);
            blog.address = "https://gitee.com/zhangyuwxf/retrofit.git";
            blog.content = "http test" + (i);
            blogList.add(blog);
        }
        System.out.print(blogList);
        return blogList;
    }
}
