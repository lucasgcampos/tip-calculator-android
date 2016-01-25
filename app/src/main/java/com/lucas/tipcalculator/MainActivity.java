package com.lucas.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private TextView percentTextView;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private double percent = 0.15;
    private double billAmount = 0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        this.percentTextView = (TextView) findViewById(R.id.percentTextView);
        this.tipTextView = (TextView) findViewById(R.id.tipTextView);
        this.amountTextView = (TextView) findViewById(R.id.amountTextView);
        this.totalTextView = (TextView) findViewById(R.id.totalTextView);

        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        amountEditText.addTextChangedListener(amountEditTextWatcher);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calculate() {
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double total = billAmount + tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billAmount = Double.parseDouble(s.toString()) / 100.0;
            } catch (NumberFormatException e) {
                billAmount = 0.0;
            }

            amountTextView.setText(currencyFormat.format(billAmount));
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
