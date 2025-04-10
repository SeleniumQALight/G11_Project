package org.api.dto.responseDTO;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PBCurrencyRatesDTO {

    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
