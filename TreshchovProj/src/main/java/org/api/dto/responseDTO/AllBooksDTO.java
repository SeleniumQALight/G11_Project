package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllBooksDTO {
    private BookDTO[] books;
}
