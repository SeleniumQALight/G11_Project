package org.api.dto.responseDTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String userId;
    private String username;
    private List<BookDTO> books;
}