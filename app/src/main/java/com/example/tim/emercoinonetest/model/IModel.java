package com.example.tim.emercoinonetest.model;


import com.example.tim.emercoinonetest.model.pojo.TickerModel;
import java.util.List;
import rx.Observable;

/**
 * Created by TIM on 14.08.2017.
 */

public interface IModel {
    Observable<List<TickerModel>> getTickersList();
    Observable<TickerModel> getExchangeRates(String s);
}
