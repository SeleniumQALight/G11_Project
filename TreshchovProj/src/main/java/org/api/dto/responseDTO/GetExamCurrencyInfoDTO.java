package org.api.dto.responseDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetExamCurrencyInfoDTO {
    private GetExamCurrencyRateDTO[] currencyRate;
}
