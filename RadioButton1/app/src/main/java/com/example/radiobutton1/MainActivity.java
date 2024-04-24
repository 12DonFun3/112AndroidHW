package com.example.radiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerTickets = findViewById(R.id.spiTickets);
        String[] ticketOptions = generateTicketOptions(15);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ticketOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTickets.setAdapter(adapter);

        // 設置 Spinner 為可編輯狀態
        spinnerTickets.setOnLongClickListener(v -> {
            spinnerTickets.setEnabled(true); // 啟用 Spinner 的編輯功能
            return true; // 表示已處理長按事件
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (isFormValid()) {
                Intent intent = new Intent(MainActivity.this, after_button.class);
                handleSelection(intent);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "請完整填寫所有字段", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String[] generateTicketOptions(int count) {
        String[] options = new String[count];
        for (int i = 0; i < count; i++) {
            options[i] = String.valueOf(i + 1); // 從 1 開始
        }
        return options;
    }

    private void handleSelection(Intent intent) {
        String outputStr = "";

        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        if (boy.isChecked())
            outputStr += "男生\n";
        else if (girl.isChecked())
            outputStr += "女生\n";

        RadioGroup type = findViewById(R.id.rgType);
        int ticketPrice = 0; // 初始化票價
        if (type.getCheckedRadioButtonId() == R.id.rdbAdult) {
            outputStr += "全票\n";
            ticketPrice = 500; // 全票價格
        }
        if (type.getCheckedRadioButtonId() == R.id.rdbChild) {
            outputStr += "兒童票\n";
            ticketPrice = 250; // 兒童票價格
        }
        if (type.getCheckedRadioButtonId() == R.id.rdbStudent) {
            outputStr += "學生\n";
            ticketPrice = 400; // 學生票價格
        }

        // 添加 Spinner 的選擇邏輯
        Spinner spinnerTickets = findViewById(R.id.spiTickets);
        int numTickets = Integer.parseInt(spinnerTickets.getSelectedItem().toString());
        int totalAmount = ticketPrice * numTickets; // 計算總票價
        outputStr += "已選擇：" + numTickets + "張票\n總票價：" + totalAmount + "元";

        // 將結果作為 extra 放入 intent 中
        intent.putExtra("output", outputStr);
    }

    private boolean isFormValid() {
        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        RadioGroup type = findViewById(R.id.rgType);
        Spinner spinnerTickets = findViewById(R.id.spiTickets);

        // 檢查是否有選擇 RadioButton
        if (!boy.isChecked() && !girl.isChecked()) {
            return false;
        }

        // 檢查是否有選擇 RadioButton
        if (type.getCheckedRadioButtonId() == -1) {
            return false;
        }

        // 檢查是否有選擇票數
        if (spinnerTickets.getSelectedItem() == null) {
            return false;
        }
        return true;
    }
}
