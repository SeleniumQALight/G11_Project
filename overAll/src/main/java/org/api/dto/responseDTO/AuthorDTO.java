package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
    private String username;
    private String avatar;
}
