package org.api.dto.bookstoreDTO;

import lombok.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder



public class BooksInfoDTO {
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
