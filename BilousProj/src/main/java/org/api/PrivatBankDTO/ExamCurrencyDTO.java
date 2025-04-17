package org.api.PrivatBankDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamCurrencyDTO {
        private String ccy;        // Валюта
        private String base_ccy;   // Базова валюта
        private String buy;        // Курс купівлі
        private String sale;       // Курс продажу
}
