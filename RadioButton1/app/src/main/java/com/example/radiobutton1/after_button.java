package com.example.radiobutton1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class after_button extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_button);

        // 從 intent 中讀取結果資訊
        Intent intent = getIntent();
        String outputStr = intent.getStringExtra("output");

        // 顯示結果資訊
        TextView txvsheets = findViewById(R.id.txv2);
        txvsheets.setText(outputStr);
    }
    public void backButtonClicked(View view) {
        finish(); // 关闭当前 activity
    }

}
