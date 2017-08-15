package com.example.tim.emercoinonetest.presenter;

/**
 * Created by TIM on 14.08.2017.
 */

public interface IMainPresenter  {
    void getTickersListToPresenter();
    void getListPairsOfCurrency();
    void getFilterSecondListCurrencyForSpinner(String firstCurrency);
    void getExchangeRates(String currenciesPair);
    void onStop();
}
