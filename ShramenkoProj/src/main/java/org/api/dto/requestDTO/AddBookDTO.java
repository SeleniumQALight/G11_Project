package org.api.dto.requestDTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookDTO {
    private String userId;
    private List<IsbnEntry> collectionOfIsbns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsbnEntry {
        private String isbn;
    }
}