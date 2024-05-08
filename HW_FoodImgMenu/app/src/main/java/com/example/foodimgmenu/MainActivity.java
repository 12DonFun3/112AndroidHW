package com.example.foodimgmenu;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox chk1, chk2, chk3, chk4;
    private ImageView output1, output2, output3, output4;
    private TextView showOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        output1 = findViewById(R.id.output1);
        output2 = findViewById(R.id.output2);
        output3 = findViewById(R.id.output3);
        output4 = findViewById(R.id.output4);
        showOrder = findViewById(R.id.showOrder);

        output1.setVisibility(ImageView.GONE);
        output2.setVisibility(ImageView.GONE);
        output3.setVisibility(ImageView.GONE);
        output4.setVisibility(ImageView.GONE);
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                output1.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
                updateOrderText();
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                output2.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
                updateOrderText();
            }
        });

        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                output3.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
                updateOrderText();
            }
        });

        chk4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                output4.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
                updateOrderText();
            }
        });
    }

    private void updateOrderText() {
        StringBuilder order = new StringBuilder("您點的餐點如下：");
        if (chk1.isChecked()) {
            order.append(" 漢堡");
        }
        if (chk2.isChecked()) {
            order.append(" 薯條");
        }
        if (chk3.isChecked()) {
            order.append(" 可樂");
        }
        if (chk4.isChecked()) {
            order.append(" 玉米濃湯");
        }
        showOrder.setText(order.toString());
    }
}





