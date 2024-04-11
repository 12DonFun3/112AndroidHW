package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvShow = findViewById(R.id.txvShow);
        txvShow.setTextSize(36);
        EditText edtH = findViewById(R.id.edtH);
        EditText edtW = findViewById(R.id.edtW);
        Button btnCal = findViewById(R.id.btnCal);
        Button btnClear = findViewById(R.id.btnClear);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = edtH.getText().toString();
                String weightStr = edtW.getText().toString();

                // Check if input is empty or non-numeric
                if (heightStr.isEmpty() || weightStr.isEmpty() ||
                        !isNumeric(heightStr) || !isNumeric(weightStr)) {
                    Toast.makeText(MainActivity.this, "請輸入有效數字", Toast.LENGTH_SHORT).show();
                    return;
                }

                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);

                // Check if input is 0
                if (height == 0 || weight == 0) {
                    Toast.makeText(MainActivity.this, "請輸入有效數字", Toast.LENGTH_SHORT).show();
                    return;
                }

                double bmi = weight / Math.pow(height / 100.0, 2); // Convert height to meters and square
                if (bmi >= 24)
                    txvShow.setTextColor(Color.RED);
                else if (bmi < 18.5)
                    txvShow.setTextColor(Color.BLUE);
                else
                    txvShow.setTextColor(Color.GREEN);

                txvShow.setText(String.format("%.2f", bmi));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtH.setText("");
                edtW.setText("");
                txvShow.setText("");
            }
        });
    }

    // Function to check if a string is numeric
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
