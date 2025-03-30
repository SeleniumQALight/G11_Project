package org.api.dto.responseDTO;

import lombok.*;

@Data //вже містить гетери і сетери
@ToString
@AllArgsConstructor //конструктор з усіма філдами
@NoArgsConstructor //дефолтний
@Builder //настроюваний

public class AuthorDTO {
    private String username;
    private String avatar;

}
