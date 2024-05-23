package com.example.spandlv;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        TextView tvSelectedItems = findViewById(R.id.tvSelectedItems);

        // 获取传递的数据
        String mainCourse = getIntent().getStringExtra("主餐");
        String sideDish = getIntent().getStringExtra("附餐");
        String drink = getIntent().getStringExtra("飲料");

// 检查主餐是否为 null，如果是，则将其替换为 "未選取"
        if(mainCourse == null) {
            mainCourse="未選取";
        }
        if(sideDish == null) {
            sideDish="未選取";
        }
        if(drink == null) {
            drink="未選取";
        }
// 显示选择的数据
        String selection = "主餐: " + mainCourse + "\n"
                + "附餐: " + sideDish + "\n"
                + "飲料: " + drink;
        tvSelectedItems.setText(selection);

    }
}
