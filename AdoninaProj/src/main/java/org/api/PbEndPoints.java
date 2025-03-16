package org.api;

public interface PbEndPoints {
  String BASE_URL_PB = "https://api.privatbank.ua";
  String EXCHANGE_RATE = BASE_URL_PB + "/p24api/exchange_rates?date={0}";
}
