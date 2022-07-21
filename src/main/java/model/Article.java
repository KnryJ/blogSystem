package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Article {
    public Integer aid;
    @JsonIgnore
    public Integer uid;
    public String title;
    @JsonIgnore
    public String type;
    public String content;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    public String publishedAt;

    public String getSummary(){
        int len = Integer.min(content.length(), 20);
        return content.substring(0, len);
    }
}
