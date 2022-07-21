package dao;

import lombok.SneakyThrows;
import model.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    @SneakyThrows
    public User selectOneByUsernameAndPassword(String username, String password){
        String sql = "select uid, avatar, git_repo from users where username = ? and password = ?";
        try (Connection c = DBUtil.connection()) {
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                System.out.println("DEBUG: " + ps);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return null;
                    }

                    User user = new User();
                    user.uid = rs.getInt("uid");
                    user.username = username;
                    user.password = password;
                    user.avatar = rs.getString("avatar");
                    user.gitRepo = rs.getString("git_repo");

                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void register(String username, String password, String avatar, String git_repo) {
        String sql = "INSERT INTO users (username, password, avatar, git_repo) VALUES (?, ?, ?, ?);";
        try(Connection c = DBUtil.connection()){
            try(PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, avatar);
                ps.setString(4, git_repo);
                ps.executeUpdate();
            }
        }
    }

    @SneakyThrows
    public boolean exist(String username) {
        String sql = "select * from users where username = ?";
        try(Connection c = DBUtil.connection()){
            try(PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, username);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
