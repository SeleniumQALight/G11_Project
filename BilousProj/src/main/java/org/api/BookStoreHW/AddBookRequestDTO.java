package org.api.BookStoreHW;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookRequestDTO {
    private String userId;
    private List<BookIsbnDTO> collectionOfIsbns;
}

