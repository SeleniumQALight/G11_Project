package org.api.dto.responseDTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBooksDto {
  private String userId;
  private String username;
  private List<BooksDTO> books;
}
