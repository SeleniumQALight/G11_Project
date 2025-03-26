package org.api.dto.responseDTO;

import lombok.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBooksDto {
  private String userId;
  private String username;
  private BooksDTO[] books;
}
