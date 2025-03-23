package org.api.dto.responseDTO;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoqaBooksByUserDTO {

    private String userId;
    private String username;
    private DemoqaBooksDTO[] books;
}
