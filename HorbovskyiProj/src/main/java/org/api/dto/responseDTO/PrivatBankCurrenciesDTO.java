package org.api.dto.responseDTO;

public class PrivatBankCurrenciesDTO {

        private String baseCurrency;
        private String currency;
        private Float saleRateNB;
        private Float purchaseRateNB;
        private Float saleRate;
        private Float purchaseRate;

        public PrivatBankCurrenciesDTO() {
        }

        public PrivatBankCurrenciesDTO(String baseCurrency, String currency) {
                this.baseCurrency = baseCurrency;
                this.currency = currency;
        }

        @Override
        public String toString() {
                return "PrivatBankCurrenciesDTO{" +
                        "baseCurrency='" + baseCurrency + '\'' +
                        ", currency='" + currency + '\'' +
                        '}';
        }

        public Float getSaleRateNB() {
                return saleRateNB;
        }

        public void setSaleRateNB(Float saleRateNB) {
                this.saleRateNB = saleRateNB;
        }

        public Float getPurchaseRateNB() {
                return purchaseRateNB;
        }

        public void setPurchaseRateNB(Float purchaseRateNB) {
                this.purchaseRateNB = purchaseRateNB;
        }

        public Float getSaleRate() {
                return saleRate;
        }

        public void setSaleRate(Float saleRate) {
                this.saleRate = saleRate;
        }

        public Float getPurchaseRate() {
                return purchaseRate;
        }

        public void setPurchaseRate(Float purchaseRate) {
                this.purchaseRate = purchaseRate;
        }

        public String getBaseCurrency() {
                return baseCurrency;
        }

        public void setBaseCurrency(String baseCurrency) {
                this.baseCurrency = baseCurrency;
        }

        public String getCurrency() {
                return currency;
        }

        public void setCurrency(String currency) {
                this.currency = currency;
        }
}
