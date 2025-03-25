package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookStoreLoginDTO {
        private String created_date;
        private String expires;
        private Boolean isActive;
        private String password;
        private String token;
        private String userId;
        private String username;
}
