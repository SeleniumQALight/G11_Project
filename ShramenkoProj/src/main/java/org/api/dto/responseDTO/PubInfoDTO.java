package org.api.dto.responseDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PubInfoDTO {

        private String ccy;
        private String base_ccy;
        private String buy;
        private String sale;

}
