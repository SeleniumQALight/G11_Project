package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BooksDTO {
  private String isbn;
  private String title;
  private String subTitle;
  private String author;
  private String publish_date;
  private String publisher;
  private Integer pages;
  private String description;
  private String website;
}
