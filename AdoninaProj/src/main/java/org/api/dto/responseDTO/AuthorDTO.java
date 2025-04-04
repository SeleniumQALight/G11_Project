package org.api.dto.responseDTO;

import lombok.*;

@Data //@Getter + @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {
  private String username;
  private String avatar;

}
