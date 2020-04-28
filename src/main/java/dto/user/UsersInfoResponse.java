package dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UsersInfoResponse {
    Integer page;
    Integer per_page;
    Integer total;
    Integer total_pages;
    List<User> data;
    Ad ad;


    @Data
    public static class User {
        Integer id;
        String email;
        String first_name;
        String last_name;
        String avatar;
    }

    @Data
    public static class Ad {
        String company;
        String url;
        String text;
    }
}


