package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BooksLoginDTO {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private String isActive;
}
