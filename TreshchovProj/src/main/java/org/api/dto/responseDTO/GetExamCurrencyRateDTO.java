package org.api.dto.responseDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetExamCurrencyRateDTO {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
