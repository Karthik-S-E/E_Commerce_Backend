package com.myProject.e_Commerce.security.request;


import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupRequest {

    private String email;

    private String username;

    private String password;

    private Set<String> role;

}
