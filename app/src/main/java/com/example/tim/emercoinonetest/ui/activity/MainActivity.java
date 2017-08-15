package com.example.tim.emercoinonetest.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tim.emercoinonetest.R;
import com.example.tim.emercoinonetest.presenter.IMainPresenter;
import com.example.tim.emercoinonetest.presenter.MainPresenterImpl;
import com.example.tim.emercoinonetest.ui.adapter.MainAdapter;
import com.example.tim.emercoinonetest.view.IMainActivity;

import java.util.List;

import static android.graphics.PorterDuff.Mode;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private RelativeLayout mRlItem1, mRlItem2, mRlItem3, mRlItem4;
    private LinearLayout mLlMyMoney, mLlBestRate, mLlExchange, mLlSpinners;
    private FrameLayout mFlCurrencyPair;
    private RecyclerView mRvCurrencyPair;
    private ImageView mIvWallet, mIvRate, mIvCurrencyPair, mIvExchange;
    private TextView mTvMyMoney, mTvBestRate, mTvCurrencyPair, mTvExchange, mTvExchangeResult;
    private Spinner mSpFrom, mSpTo;
    private IMainPresenter mPresenter;
    private ProgressBar mPbCurrencyPair, mPbExchangeResult, mPbListOfPairs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mPresenter.getTickersListToPresenter();
        mSpFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTvExchangeResult.setText("");
                if (!mSpFrom.getSelectedItem().toString().equals("--")) {
                    mPresenter.getFilterSecondListCurrencyForSpinner(mSpFrom.getSelectedItem()
                            .toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mSpTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTvExchangeResult.setText("");
                if (!mSpTo.getSelectedItem().toString().equals("--")
                        & !mSpFrom.getSelectedItem().toString().equals("--")) {
                    mPresenter.getExchangeRates(mSpFrom.getSelectedItem().toString() + "/"
                            + mSpTo.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mRlItem1.setOnClickListener(v -> showOrHideLayoutMyMoney());
        mRlItem2.setOnClickListener(v -> showOrHideLayoutBestRate());
        mRlItem3.setOnClickListener(v -> showOrHideLayoutCurrencyPair());
        mRlItem4.setOnClickListener(v -> showOrHideLayoutExchange());
    }

    public void showOrHideLayoutMyMoney() {
        if (!mLlMyMoney.isShown()) {
            mRlItem1.setBackgroundResource(R.drawable.item1_bg_disabled);
            mLlMyMoney.setVisibility(View.VISIBLE);
            mIvWallet.getDrawable().mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark), Mode.MULTIPLY);
            mTvMyMoney.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark));

        } else {
            mRlItem1.setBackgroundResource(R.drawable.item1_bg_eabled);
            mLlMyMoney.setVisibility(View.GONE);
            mIvWallet.getDrawable().mutate().setColorFilter(null);
            mTvMyMoney.setTextColor(Color.WHITE);
        }
    }

    public void showOrHideLayoutBestRate() {
        if (!mLlBestRate.isShown()) {
            mRlItem2.setBackgroundResource(R.drawable.item2_bg_disabled);
            mLlBestRate.setVisibility(View.VISIBLE);
            mIvRate.getDrawable().mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark), Mode.MULTIPLY);
            mTvBestRate.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark));
        } else {
            mRlItem2.setBackgroundResource(R.drawable.item2_bg_enabled);
            mLlBestRate.setVisibility(View.GONE);
            mIvRate.getDrawable().mutate().setColorFilter(null);
            mTvBestRate.setTextColor(Color.WHITE);
        }
    }

    public void showOrHideLayoutCurrencyPair() {
        if (!mFlCurrencyPair.isShown()) {
            mRlItem3.setBackgroundResource(R.drawable.item2_bg_disabled);
            mFlCurrencyPair.setVisibility(View.VISIBLE);
            mIvCurrencyPair.getDrawable().mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark), Mode.MULTIPLY);
            mTvCurrencyPair.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark));
        } else {
            mRlItem3.setBackgroundResource(R.drawable.item2_bg_enabled);
            mFlCurrencyPair.setVisibility(View.GONE);
            mIvCurrencyPair.getDrawable().mutate().setColorFilter(null);
            mTvCurrencyPair.setTextColor(Color.WHITE);
        }
    }

    public void showOrHideLayoutExchange() {
        if (!mLlExchange.isShown()) {
            mRlItem4.setBackgroundResource(R.drawable.item2_bg_disabled);
            mLlExchange.setVisibility(View.VISIBLE);
            mIvExchange.getDrawable().mutate().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark), Mode.MULTIPLY);
            mTvExchange.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark));
        } else {
            mRlItem4.setBackgroundResource(R.drawable.item2_bg_enabled);
            mLlExchange.setVisibility(View.GONE);
            mIvExchange.getDrawable().mutate().setColorFilter(null);
            mTvExchange.setTextColor(Color.WHITE);
        }
    }

    public void init() {
        mRlItem1 = (RelativeLayout) findViewById(R.id.rl_item1);
        mRlItem2 = (RelativeLayout) findViewById(R.id.rl_item2);
        mRlItem3 = (RelativeLayout) findViewById(R.id.rl_item3);
        mRlItem4 = (RelativeLayout) findViewById(R.id.rl_item4);
        mLlMyMoney = (LinearLayout) findViewById(R.id.ll_my_money);
        mLlBestRate = (LinearLayout) findViewById(R.id.ll_best_rate);
        mFlCurrencyPair = (FrameLayout) findViewById(R.id.fl_currency_pair);
        mLlExchange = (LinearLayout) findViewById(R.id.ll_exchange);
        mIvWallet = (ImageView) findViewById(R.id.iv_wallet);
        mIvRate = (ImageView) findViewById(R.id.iv_rate);
        mIvCurrencyPair = (ImageView) findViewById(R.id.iv_currency_pair);
        mIvExchange = (ImageView) findViewById(R.id.iv_exchange);
        mTvMyMoney = (TextView) findViewById(R.id.tv_my_money);
        mTvBestRate = (TextView) findViewById(R.id.tv_best_rate);
        mTvCurrencyPair = (TextView) findViewById(R.id.tv_currency_pair);
        mTvExchange = (TextView) findViewById(R.id.tv_exchange);
        mTvExchangeResult = (TextView) findViewById(R.id.tv_exchange_result);
        mPresenter = new MainPresenterImpl(this);
        mSpFrom = (Spinner) findViewById(R.id.spinner_from);
        mSpTo = (Spinner) findViewById(R.id.spinner_to);
        mPbCurrencyPair = (ProgressBar) findViewById(R.id.pb_currency_pair);
        mPbExchangeResult = (ProgressBar) findViewById(R.id.pb_exchange_result);
        mPbListOfPairs = (ProgressBar) findViewById(R.id.pb_list_pairs);
        mRvCurrencyPair = (RecyclerView) findViewById(R.id.rv_currency_pair);
        mLlSpinners = (LinearLayout) findViewById(R.id.ll_spinners);
    }

    @Override
    public void showToastException(String e) {
        Toast.makeText(this, e, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPairsOfCurrencyList(List<String> pairsOfCurrencyList) {
        mRvCurrencyPair.setHasFixedSize(true);
        mRvCurrencyPair.setLayoutManager(new GridLayoutManager(this, 4));
        MainAdapter mainAdapter = new MainAdapter(pairsOfCurrencyList);
        mRvCurrencyPair.setAdapter(mainAdapter);
    }

    @Override
    public void showProgressBarLoadCurrencyPairs() {
        mPbCurrencyPair.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBarLoadCurrencyPairs() {
        mPbCurrencyPair.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBarExchangeResult() {
        mPbExchangeResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBarExchangeResult() {
        mPbExchangeResult.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBarListPairs() {
        mPbListOfPairs.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBarListPairs() {
        mPbListOfPairs.setVisibility(View.GONE);
    }

    @Override
    public void showSpinnerLayout() {
        mLlSpinners.setVisibility(View.VISIBLE);
    }

    @Override
    public void setResultData(String text) {
        mTvExchangeResult.setText(text);
    }

    @Override
    public void setDataToSpFrom(List<String> fromRates) {
        fromRates.add(0, "--");
        mSpFrom.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fromRates));

    }

    @Override
    public void setDataToSpTo(List<String> toRates) {
        toRates.add(0, "--");
        mSpTo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, toRates));
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }
}
