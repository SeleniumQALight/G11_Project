package org.api.dto.bookstoreDTO;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BooksList {

    private String userId;
    private String username;
    private List<BooksDTO> books;

}
