package org.api.dto.requestDTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBooksDTO {
    private String userId;
    private CollectionOfISBNsDTO[] collectionOfIsbns;
}
