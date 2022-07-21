package util;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static final DataSource dataSource;

    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql:///blog?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");

        dataSource = mysqlDataSource;
    }

    @SneakyThrows
    public static Connection connection() throws SQLException {
        return dataSource.getConnection();
    }
}
