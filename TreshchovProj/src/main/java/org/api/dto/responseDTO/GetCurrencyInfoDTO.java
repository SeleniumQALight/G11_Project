package org.api.dto.responseDTO;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCurrencyInfoDTO {
    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private GetCurrencyRateDTO[] exchangeRate;
}
