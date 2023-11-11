package com.project.shopapp.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
//    Dùng @Noteblank để validition không được bỏ trống
    private String fullname;
    @NotBlank(message = "Phone number is required")
    private String phone_number;
    private String address;
    @NotBlank(message = "Password can not be blank")
    private String password;
    private String confirm_password;
    private Date date_of_birth;
    private int facebook_account_id;
    private int google_account_id;
    private Long role_id;
}
