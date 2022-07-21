package servlet;

import dao.ArticleDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register.do")
public class RegisterDoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String avatar = req.getParameter("avatar");
        String git_repo = req.getParameter("git_repo");


        UserDao userDao = new UserDao();

        if(userDao.exist(username)){
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.print("<h1>用户名已存在<h1>");
            return;
        }

        userDao.register(username, password, avatar, git_repo);

        resp.sendRedirect("/login.html");
    }
}
