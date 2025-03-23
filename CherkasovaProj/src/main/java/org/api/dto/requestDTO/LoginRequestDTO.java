package org.api.dto.requestDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {
    private String userName;
    private String password;
}
