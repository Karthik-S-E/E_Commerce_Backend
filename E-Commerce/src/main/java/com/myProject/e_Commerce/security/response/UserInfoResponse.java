package com.myProject.e_Commerce.security.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoResponse {
    private long id;

    private String jwtToken;
    private String username;
    private List<String> roles;


    public UserInfoResponse(Long id, String username, List<String> roles) {
        this.id=id;
        this.username=username;
        this.roles=roles;
    }
}
