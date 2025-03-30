package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookUserDTO {
    private String userId;
    private String username;
    private BookDTO[] books;

}
