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
    private String userId;
    private String username;
    private List<BooksDTO> books;
}

