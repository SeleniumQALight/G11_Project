package org.api.dataTransferObject.responseDTO;

import lombok.*;

@Data // setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthorDTO {
    private String username;
    private String avatar;
}
