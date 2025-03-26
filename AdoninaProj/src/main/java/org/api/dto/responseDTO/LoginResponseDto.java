package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
  private String userId;
  private String username;
  private String password;
  private String token;
  private String expires;
  private String created_date;
  private Boolean isActive;
}
