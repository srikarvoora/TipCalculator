package com.example.tipcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etBillAmount;
    private EditText etTipPercent;
    private EditText etNumPeople;
    private TextView tvTipAmount;
    private TextView tvTipEach;
    private TextView tvTotalAmount;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBillAmount = findViewById(R.id.etNDAmtBill);
        etTipPercent = findViewById(R.id.etTipNumber);
        etNumPeople = findViewById(R.id.tvPeopleNumber);
        tvTipAmount = findViewById(R.id.tvTipAmt);
        tvTipEach = findViewById(R.id.tvTipEach);
        tvTotalAmount = findViewById(R.id.tvTotalAmt);
        btnCalculate = findViewById(R.id.bnCalculate);

        // Set up TextChangedListeners to update calculations whenever input changes
        etBillAmount.addTextChangedListener(calculateTextWatcher);
        etTipPercent.addTextChangedListener(calculateTextWatcher);
        etNumPeople.addTextChangedListener(calculateTextWatcher);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTip();
            }
        });
    }

    private void calculateTip() {
        // Retrieve values from the EditText fields
        double billAmount = Double.parseDouble(etBillAmount.getText().toString());
        double tipPercent = Double.parseDouble(etTipPercent.getText().toString());
        int numPeople = Integer.parseInt(etNumPeople.getText().toString());

        // Calculate tip, individual tip, and total bill
        double tipAmount = (billAmount * tipPercent) / 100;
        double totalAmount = billAmount + tipAmount;
        double tipEach = tipAmount / numPeople;

        // Display the calculated values
        tvTipAmount.setText(NumberFormat.getCurrencyInstance().format(tipAmount));
        tvTipEach.setText(NumberFormat.getCurrencyInstance().format(tipEach));
        tvTotalAmount.setText(NumberFormat.getCurrencyInstance().format(totalAmount));
    }

    // TextWatcher to observe changes in EditText fields
    private TextWatcher calculateTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Not needed in this case
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Not needed in this case
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Call the calculateTip method whenever the text changes
            calculateTip();
        }
    };
}