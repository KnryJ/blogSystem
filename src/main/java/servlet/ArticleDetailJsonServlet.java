package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ArticleDetailResult;
import model.User;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/article-detail.json")
public class ArticleDetailJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String aidStr = req.getParameter("aid");

        int aid = Integer.parseInt(aidStr);

        User currentUser = null;
        HttpSession session = req.getSession(false);
        if(session != null){
            currentUser = (User)session.getAttribute("currentUser");
        }


        ArticleService articleService = new ArticleService();
        ArticleDetailResult result = articleService.detail(currentUser, aid);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonStr);
    }
}
