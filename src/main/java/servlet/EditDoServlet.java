package servlet;

import dao.ArticleDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/edit.do")
public class EditDoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        String type = req.getParameter("type");
        String content = req.getParameter("editor-markdown-doc");

        User currentUser = null;
        HttpSession session = req.getSession(false);
        if(session != null){
            currentUser = (User)session.getAttribute("currentUser");
        }

        if(session == null){
            resp.sendRedirect("/login.html");
            return;
        }

        ArticleDao articleDao = new ArticleDao();
        articleDao.insert(currentUser.uid, title, type, new Date(), content);

        resp.sendRedirect("/blog_list.html");
    }
}
