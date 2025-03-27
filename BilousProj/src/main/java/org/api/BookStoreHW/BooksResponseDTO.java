package org.api.BookStoreHW;


import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BooksResponseDTO {
    private List<BooksDTO> books;
}

