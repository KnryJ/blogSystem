package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ArticleListResult;
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

@WebServlet("/article-list.json")
public class ArticleListJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = null;
        HttpSession session = req.getSession();

        if(session != null){
            currentUser = (User)session.getAttribute("currentUser");
        }

//        Map<String, Object> result = new HashMap<>();
//        result.put("currentUser", currentUser);

        ArticleService articleService = new ArticleService();
        ArticleListResult result = articleService.articleList(currentUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(result);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonString);
    }
}
