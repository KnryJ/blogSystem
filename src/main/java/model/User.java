package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {

    public int uid;
    public String username;
    @JsonIgnore
    public String password;
    public String avatar;
    public String gitRepo;

}
