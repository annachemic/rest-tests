package dto.user;

import lombok.Data;

@Data
public class SingleUserInfoResponse {
    UsersInfoResponse.User data;
    UsersInfoResponse.Ad ad;
}
