package com.example.tim.emercoinonetest.presenter;

import com.example.tim.emercoinonetest.model.IModel;
import com.example.tim.emercoinonetest.model.Model;
import com.example.tim.emercoinonetest.model.pojo.TickerModel;
import com.example.tim.emercoinonetest.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenterImpl implements IMainPresenter {

    private final IMainActivity mainActivity;
    private final IModel model;
    private Subscription subscription;
    private List<TickerModel> tickerModels;

    public MainPresenterImpl(IMainActivity mainActivity) {
        this.mainActivity = mainActivity;
        model = new Model();
    }

    @Override
    public void getTickersListToPresenter() {
        mainActivity.showProgressBarLoadCurrencyPairs();
        mainActivity.showProgressBarListPairs();
        subscription = model.getTickersList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                            tickerModels = s;
                            getListPairsOfCurrency();
                            filterFirstListCurrency(s);
                            mainActivity.hideProgressBarLoadCurrencyPairs();
                            mainActivity.showSpinnerLayout();
                        },
                        (Throwable e) -> mainActivity.showToastException(e.toString()));
    }

    @Override
    public void getExchangeRates(String currenciesPair) {
        mainActivity.showProgressBarExchangeResult();
        subscription = model.getExchangeRates(currenciesPair)
                .map(TickerModel::getLast)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                            mainActivity.setResultData(String.format("%.8f", s));
                            mainActivity.hideProgressBarExchangeResult();
                        },
                        (Throwable e) -> mainActivity.showToastException(e.toString()));
    }

    @Override
    public void getListPairsOfCurrency() {
        List<String> list = new ArrayList<>();
        for (TickerModel t : tickerModels) {
            list.add(t.getSymbol());
        }
        mainActivity.setPairsOfCurrencyList(list);
        mainActivity.hideProgressBarListPairs();
    }

    @Override
    public void getFilterSecondListCurrencyForSpinner(String firstCurrency) {
        subscription = Observable.just(tickerModels)
                .flatMap(Observable::from)
                .filter(s -> s.getCur().startsWith(firstCurrency))
                .map(s -> s.getSymbol().split("/"))
                .toList()
                .subscribe(strings -> mainActivity.setDataToSpTo(filterListCurrency(strings)),
                        (Throwable e) -> mainActivity.showToastException(e.toString()));
    }

    private List<String> filterListCurrency(List<String[]> listSecondCurrencies) {
        List<String> list = new ArrayList<>();
        for (String[] p : listSecondCurrencies) {
            list.add(p[1]);
        }
        return list;
    }

    private void filterFirstListCurrency(List<TickerModel> list) {
        List<String> firstListOfCurrency = new ArrayList();
        for (TickerModel p : list) {
            if (firstListOfCurrency.isEmpty()) {
                firstListOfCurrency.add(list.get(0).getCur());
            } else if (!(p.getCur()).equals((firstListOfCurrency.get(firstListOfCurrency.size() - 1)))) {
                firstListOfCurrency.add(p.getCur());
            }
        }
        mainActivity.setDataToSpFrom(firstListOfCurrency);
    }

    @Override
    public void onStop() {
        if (subscription != null) {
            if (!subscription.isUnsubscribed())
                subscription.unsubscribe();
        }
    }
}
