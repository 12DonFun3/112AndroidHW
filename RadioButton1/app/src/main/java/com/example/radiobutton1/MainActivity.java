package com.example.radiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, af.class);
                startActivity(intent);
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String outputStr = "";
//
//                RadioButton boy = findViewById(R.id.rdbBoy);
//                RadioButton girl = findViewById(R.id.rdbGirl);
//                if (boy.isChecked())
//                    outputStr += "男生\n";
//                else if (girl.isChecked())
//                    outputStr += "女生\n";
//
//                RadioGroup type = findViewById(R.id.rgType);
//                if (type.getCheckedRadioButtonId() == R.id.rdbAdult)
//                    outputStr += "全票\n";
//                if (type.getCheckedRadioButtonId() == R.id.rdbChild)
//                    outputStr += "兒童票\n";
//                if (type.getCheckedRadioButtonId() == R.id.rdbStudent)
//                    outputStr += "學生\n";
//
//                TextView output = findViewById(R.id.lblOutput);
//                output.setText(outputStr);
//            }
//        });

        // 添加下拉式选择票数功能
        Spinner spinnerTickets = findViewById(R.id.spiTickets);
        // 创建一个数组或列表来存储票数选项
        String[] ticketOptions = {"1 ", "2 ", "3 ", "4 ", "5 ", "7 ", "8 ", "9 ", "10 "};
        // 创建一个 ArrayAdapter 并设置数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ticketOptions);
        // 设置下拉菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 设置适配器
        spinnerTickets.setAdapter(adapter);

        // 添加选择事件监听器
        spinnerTickets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // 获取所选项的文本
                String selectedTicket = parentView.getItemAtPosition(position).toString();
                // 处理所选项
                // 例如，可以将所选项显示在 TextView 中
                TextView lblOutput = findViewById(R.id.lblOutput);
                lblOutput.setText("已選擇：" + selectedTicket);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // 未选择任何项时的处理
            }
        });
    }
}
