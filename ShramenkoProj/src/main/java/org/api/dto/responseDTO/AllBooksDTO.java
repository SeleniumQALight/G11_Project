package org.api.dto.responseDTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllBooksDTO {
    private List<BookDTO> books;
}
