package org.api.dto.request;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostDto {
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;
}
