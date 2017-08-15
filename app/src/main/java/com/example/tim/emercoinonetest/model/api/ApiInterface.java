package com.example.tim.emercoinonetest.model.api;

import com.example.tim.emercoinonetest.model.pojo.TickerModel;
import com.example.tim.emercoinonetest.tools.Config;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiInterface {
    @GET(Config.TICERS_REQUEST)
    Observable<List<TickerModel>> getCurrencyTickersApi();

    @GET(Config.TICER_REQUEST)
    Observable<TickerModel> getExchangeRatesApi(@Query("currencyPair") String pairCurrency);
}
