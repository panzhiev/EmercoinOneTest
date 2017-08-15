package com.example.tim.emercoinonetest.view;

import java.util.List;

/**
 * Created by TIM on 14.08.2017.
 */

public interface IMainActivity {

    void setResultData(String text);
    void setDataToSpFrom(List<String> FromRates);
    void setDataToSpTo(List<String> ToRates);
    void showToastException(String e);
    void setPairsOfCurrencyList(List<String> pairsOfCurrencyList);
    void showProgressBarLoadCurrencyPairs();
    void hideProgressBarLoadCurrencyPairs();
    void showProgressBarExchangeResult();
    void hideProgressBarExchangeResult();
    void showProgressBarListPairs();
    void hideProgressBarListPairs();
    void showSpinnerLayout();
}
