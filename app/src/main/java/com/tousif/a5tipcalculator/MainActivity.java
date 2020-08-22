package com.tousif.a5tipcalculator;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private Button clearAllButton;
    private TextView totalBillAmount;
    private TextView resultTextView;
    private TextView tipPercentageTextView;
    private TextView seekbarTextView;
    private int seekbarPercentage;
    private float enteredBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmount = findViewById(R.id.bill_amount_id);
        seekBar = findViewById(R.id.seekBar);
        calculateButton = findViewById(R.id.calculate_button_id);
        resultTextView = findViewById(R.id.result_id);
        tipPercentageTextView = findViewById(R.id.tip_percentage_id);
        seekbarTextView = findViewById(R.id.seekbar_textView_id);
        clearAllButton = findViewById(R.id.clear_all_id);
        totalBillAmount = findViewById(R.id.total_result_id);

        // seekbar logic
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarTextView.setText(seekBar.getProgress() + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarTextView.setTextColor(Color.BLUE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() >= 11)
                    seekbarTextView.setTextColor(Color.RED);
                seekbarPercentage = seekBar.getProgress();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
            }
        });

        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredAmount.setText("");
                seekBar.setProgress(0);
                resultTextView.setText("");
                tipPercentageTextView.setText("");
                seekbarTextView.setText("");
                totalBillAmount.setText("");
                enteredBill = 0.0f;
                seekbarPercentage = 0;
            }
        });
    }

    public void Calculate() {
        float result = 0.0f;
        if (!enteredAmount.getText().toString().equals("")) {
            enteredBill = Float.parseFloat(enteredAmount.getText().toString());
            result = enteredBill * seekbarPercentage / 100;
            resultTextView.setText("Your tip will be " + result + "$");
            result += enteredBill;
            totalBillAmount.setText("Total bill will be " + result + "$");
        } else
            Toast.makeText(MainActivity.this, "Please enter a bill amount", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing confirmation")
                .setMessage("Are you sure you want to close Tip Calculator App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
