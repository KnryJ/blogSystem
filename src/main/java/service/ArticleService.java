package service;

import dao.ArticleDao;
import model.ArticleDetailResult;
import model.ArticleListResult;
import model.User;

public class ArticleService {
    public ArticleListResult articleList(User currentUser){
        ArticleDao articleDao = new ArticleDao();
        ArticleListResult result = new ArticleListResult();

        result.currentUser = currentUser;
        if(currentUser != null){
            result.articleCount = articleDao.selectArticleCountByUid(currentUser.uid);
            result.articleList = articleDao.selectListByUid(currentUser.uid);
            result.typeCount = articleDao.selectTypeCountByUid(currentUser.uid);
        }

        return result;
    }

    public ArticleDetailResult detail(User currentUser, int aid) {
        ArticleDetailResult result = new ArticleDetailResult();
        ArticleDao articleDao = new ArticleDao();

        result.currentUser = currentUser;
        if(currentUser != null){
            result.articleCount = articleDao.selectArticleCountByUid(currentUser.uid);
            result.typeCount = articleDao.selectTypeCountByUid(currentUser.uid);
            result.article = articleDao.selectOneByAid(aid);
        }

        return result;
    }
}
