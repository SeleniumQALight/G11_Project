package org.api.PrivatBankDTO;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyResponseDTO {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDTO[] exchangeRate;
}
