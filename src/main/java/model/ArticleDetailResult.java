package model;

import lombok.Data;

@Data
public class ArticleDetailResult {
    public User currentUser;
    public Integer articleCount;
    public Integer typeCount;
    public Article article;
}
