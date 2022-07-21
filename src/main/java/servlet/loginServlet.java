package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login.do")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("DEBUG: username = " + username);
        System.out.println("DEBUG: password = " + password);

        UserDao userDao = new UserDao();
        User user = userDao.selectOneByUsernameAndPassword(username, password);
        if(user == null){
            resp.sendRedirect("/login.html");
            return;
        }

        System.out.println(user);

        HttpSession session = req.getSession();
        session.setAttribute("currentUser", user);

        resp.sendRedirect("/");
    }
}
