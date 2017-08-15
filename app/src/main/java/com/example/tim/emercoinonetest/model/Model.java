package com.example.tim.emercoinonetest.model;


import com.example.tim.emercoinonetest.model.api.ApiModule;
import com.example.tim.emercoinonetest.model.pojo.TickerModel;
import java.util.List;
import rx.Observable;

/**
 * Created by TIM on 14.08.2017.
 */

public class Model implements IModel {
    @Override
    public Observable<List<TickerModel>> getTickersList() {
        return ApiModule.getApiInterface().getCurrencyTickersApi();
    }

    @Override
    public Observable<TickerModel> getExchangeRates(String s) {
        return ApiModule.getApiInterface().getExchangeRatesApi(s);
    }

}
