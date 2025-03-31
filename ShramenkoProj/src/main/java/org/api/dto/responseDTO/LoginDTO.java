package org.api.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    @JsonProperty("isActive")
    private boolean active;
}