package org.api.dto.requestDTO;

import lombok.*;
import org.api.dto.responseDTO.BooksDTO;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookDto {
  public String userId;
  public List<IsbnDto> collectionOfIsbns;
}
