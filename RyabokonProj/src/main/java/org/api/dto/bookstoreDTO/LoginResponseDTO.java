package org.api.dto.bookstoreDTO;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginResponseDTO {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private Boolean isActive;

}
