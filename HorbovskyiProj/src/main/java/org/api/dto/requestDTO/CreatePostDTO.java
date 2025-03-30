package org.api.dto.requestDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostDTO {
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;

}
