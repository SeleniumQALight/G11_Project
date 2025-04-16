package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PubInfoPrivatBankDTO {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
