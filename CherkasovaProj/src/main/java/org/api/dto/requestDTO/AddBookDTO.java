package org.api.dto.requestDTO;


import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookDTO {
    private String userId;
    private List<IsbnDTO> collectionOfIsbns;
}
