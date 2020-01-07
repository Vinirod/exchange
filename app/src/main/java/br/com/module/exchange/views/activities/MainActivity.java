package br.com.module.exchange.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.module.exchange.R;
import br.com.module.exchange.apis.ConfigService;
import br.com.module.exchange.apis.ExchangeRatesApiService;
import br.com.module.exchange.local.dao.HistoryDAO;
import br.com.module.exchange.model.ExchangeRatesWrapperObject;
import br.com.module.exchange.model.History;
import br.com.module.exchange.utils.ValueUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpnFrom;

    private Spinner mSpnTo;

    private List<String> listCoin;

    private TextView mTxtRequiredValue;

    private Button mBtnConvert;

    private EditText mEdtValue;

    private ExchangeRatesApiService mExchangeRatesApiService;

    private TextView mTxtResultConversion;

    private LinearLayout mLnAboutConversion;

    private ProgressBar mPgrConversion;

    private History mHistory;

    private HistoryDAO mHistoryDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindAll();
        startAll();
        addItemOnSpinnerFrom();
        addItemOnSpinnerTo();
    }

    private void bindAll() {
        mSpnFrom = (Spinner) findViewById(R.id.idSpnCoinType);
        mSpnTo = (Spinner) findViewById(R.id.idSpnCoinConverted);
        mBtnConvert = (Button) findViewById(R.id.idBtnConvert);
        mEdtValue = (EditText) findViewById(R.id.idEdtValue);
        mTxtRequiredValue = (TextView) findViewById(R.id.idTxtValueRequired);
        mTxtResultConversion = (TextView) findViewById(R.id.idTxtResultConversion);
        mLnAboutConversion = (LinearLayout) findViewById(R.id.idLnAboutConversion);
        mPgrConversion = (ProgressBar) findViewById(R.id.idPrgConversion);
    }

    private void startAll() {
        addCoinInList();
        this.setTitle(R.string.app_title_coin_convert);
        mTxtResultConversion.setVisibility(View.GONE);
        mTxtRequiredValue.setVisibility(View.INVISIBLE);
        mExchangeRatesApiService = ConfigService.createService(ExchangeRatesApiService.class);
        mHistoryDAO = new HistoryDAO(this);
        convert();
    }

    private void addCoinInList() {
        listCoin = new ArrayList<>();
        listCoin.add("CAD");
        listCoin.add("CHF");
        listCoin.add("BRL");
        listCoin.add("GBP");
        listCoin.add("SEK");
        listCoin.add("EUR");
        listCoin.add("USD");
    }

    private void convert() {
        mBtnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdtValue.getText().toString().isEmpty()){
                    mTxtRequiredValue.setVisibility(View.VISIBLE);
                }else{
                    mTxtRequiredValue.setVisibility(View.GONE);
                    String from = mSpnFrom.getSelectedItem().toString();
                    String to = mSpnTo.getSelectedItem().toString();
                    checkFromIsEqualTo(from, to);
                }
            }
        });
    }

    private void checkFromIsEqualTo(String from, String to) {
        if(from.equals(to)){
            Toast.makeText(MainActivity.this, "No need for conversion.", Toast.LENGTH_SHORT).show();
        }else {
            validateValue(from, to);
            mLnAboutConversion.setVisibility(View.VISIBLE);
        }
    }

    private void validateValue(String from, String to) {
        double value;
        try{
            value = Double.parseDouble(mEdtValue.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(MainActivity.this, "Wrong value input", Toast.LENGTH_SHORT).show();
            return;
        }
        convertValue(from, to, value);
    }

    private void convertValue(final String from, final String to, final double value) {

        mExchangeRatesApiService.getExchangeRates(from, to).enqueue(new Callback<ExchangeRatesWrapperObject>() {
            @Override
            public void onResponse(Call<ExchangeRatesWrapperObject> call, Response<ExchangeRatesWrapperObject> response) {
                ExchangeRatesWrapperObject obj = response.body();
                double exchangeRate = obj.getRates().getRateFor(to);
                double convertedValue = value*exchangeRate;
                String result = value + " " + from + " = " + ValueUtils.formatValue(convertedValue) + " " + to;
                mTxtResultConversion.setText(result);
                mPgrConversion.setVisibility(View.GONE);
                mTxtResultConversion.setVisibility(View.VISIBLE);
                saveLocal(from, to, result);
            }

            @Override
            public void onFailure(Call<ExchangeRatesWrapperObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addItemOnSpinnerTo() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listCoin);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnTo.setAdapter(dataAdapter);
    }

    private void addItemOnSpinnerFrom() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listCoin);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnFrom.setAdapter(dataAdapter);
    }

    private void saveLocal(String from, String to, String result) {
        mHistory = new History();
        mHistory.setFrom(from);
        mHistory.setTo(to);
        mHistory.setDate(getDateNow());
        mHistory.setHourMinuteAndSeconds(getHourMinuteAndSecondsNow());
        mHistory.setResult(result);
        mHistoryDAO.insert(mHistory);
    }

    private String getDateNow() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    private String getHourMinuteAndSecondsNow() {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss", Locale.forLanguageTag("pt-br"));
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuhistory:
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
