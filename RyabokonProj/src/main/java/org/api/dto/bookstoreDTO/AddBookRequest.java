package org.api.dto.bookstoreDTO;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AddBookRequest {
    private String userId;
    private List<BookISBN> collectionOfIsbn;
}
