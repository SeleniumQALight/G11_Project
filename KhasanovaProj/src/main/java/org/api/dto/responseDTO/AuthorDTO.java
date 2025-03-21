package org.api.dto.responseDTO;

import lombok.*;

@Data //Getters and Setters
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
    private String username;
    private String avatar;

}
