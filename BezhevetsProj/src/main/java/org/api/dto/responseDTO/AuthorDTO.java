package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {
    private String username;
    private String avatar;
}
