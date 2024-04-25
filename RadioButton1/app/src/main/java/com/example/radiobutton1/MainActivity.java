package com.example.radiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerTickets = findViewById(R.id.spiTickets);
        String[] ticketOptions = generateTicketOptions(15); // 生成 1 到 15 的选项
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ticketOptions);

        // 在票选项数组的开头添加零选项
        String[] optionsWithZero = new String[ticketOptions.length + 1];
        optionsWithZero[0] = "0";
        System.arraycopy(ticketOptions, 0, optionsWithZero, 1, ticketOptions.length);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsWithZero);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTickets.setAdapter(adapter);

        // 設置 Spinner 為可編輯狀態
        spinnerTickets.setOnLongClickListener(v -> {
            spinnerTickets.setEnabled(true); // 啟用 Spinner 的編輯功能
            return true; // 表示已處理長按事件
        });

        // 添加 RadioButton 的点击事件监听器
        RadioGroup genderRadioGroup = findViewById(R.id.rgGender);
        genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            updateTicketPrice(); // 当 RadioButton 被选中时更新票价文本视图
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (isFormValid()) {
                Intent intent = new Intent(MainActivity.this, after_button.class);
                handleSelection(intent);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "请填写完整所有项目", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateGender() {
        TextView genderTextView = findViewById(R.id.textView);
        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);

        if (boy.isChecked()) {
            genderTextView.setText("性别: 男");
        } else if (girl.isChecked()) {
            genderTextView.setText("性别: 女");
        }
    }

    private void updateTicketPrice() {
        EditText etTickets = findViewById(R.id.etTickets);
        TextView ticketPriceTextView = findViewById(R.id.txvshow);
        RadioGroup genderRadioGroup = findViewById(R.id.rgGender);
        RadioGroup typeRadioGroup = findViewById(R.id.rgType);

        // 获取用户选择的性别
        String gender = "";
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == R.id.rdbBoy) {
            gender = "男性";
        } else if (selectedGenderId == R.id.rdbGirl) {
            gender = "女性";
        } else {
            gender = "未选择性别";
        }

        // 获取票价和票张数
        int numTickets;
        if (!etTickets.getText().toString().isEmpty()) {
            numTickets = Integer.parseInt(etTickets.getText().toString());
        } else {
            numTickets = 0; // 没有手动输入时默认为 0
        }
        int ticketPrice = getTicketPrice();

        // 获取用户选择的票种
        String ticketType = "";
        int selectedTypeId = typeRadioGroup.getCheckedRadioButtonId();
        if (selectedTypeId == R.id.rdbAdult) {
            ticketType = "全票";
        } else if (selectedTypeId == R.id.rdbChild) {
            ticketType = "兒童票";
        } else if (selectedTypeId == R.id.rdbStudent) {
            ticketType = "學生票";
        }

        // 计算总票价并更新文本视图
        int totalAmount = ticketPrice * numTickets;
        ticketPriceTextView.setText("性别：" + gender + "\n已选：" + ticketType + " " + numTickets + " 张票\n总票价：" + totalAmount + "元");
    }




    private int getTicketPrice() {
        RadioGroup rgType = findViewById(R.id.rgType);

        if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
            return 500; // 全票价格
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
            return 250; // 兒童票价格
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
            return 400; // 学生票价格
        }

        return 0; // 默认为 0
    }

    private String[] generateTicketOptions(int count) {
        String[] options = new String[count];
        for (int i = 0; i < count; i++) {
            options[i] = String.valueOf(i + 1); // 从 1 开始
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
        int ticketPrice = 0; // 初始化票价
        if (type.getCheckedRadioButtonId() == R.id.rdbAdult) {
            outputStr += "全票\n";
            ticketPrice = 500; // 全票价格
        }
        if (type.getCheckedRadioButtonId() == R.id.rdbChild) {
            outputStr += "儿童票\n";
            ticketPrice = 250; // 儿童票价格
        }
        if (type.getCheckedRadioButtonId() == R.id.rdbStudent) {
            outputStr += "学生票\n";
            ticketPrice = 400; // 学生票价格
        }

        // 添加 Spinner 的选择逻辑
        Spinner spinnerTickets = findViewById(R.id.spiTickets);
        int numTickets;

        // 检查手动输入的票张数
        TextView manualInput = findViewById(R.id.etTickets);
        if (!manualInput.getText().toString().isEmpty()) {
            numTickets = Integer.parseInt(manualInput.getText().toString());
            spinnerTickets.setSelection(0); // 将 Spinner 设置为第一个选项
        } else {
            numTickets = Integer.parseInt(spinnerTickets.getSelectedItem().toString());
        }

        int totalAmount = ticketPrice * numTickets; // 计算总票价
        outputStr += "已选：" + numTickets + "张票\n总票价：" + totalAmount + "元";

        // 将结果作为 extra 放入 intent 中
        intent.putExtra("output", outputStr);
    }

    private boolean isFormValid() {
        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        RadioGroup type = findViewById(R.id.rgType);
        Spinner spinnerTickets = findViewById(R.id.spiTickets);

        // 检查是否有选择 RadioButton
        if (!boy.isChecked() && !girl.isChecked()) {
            return false;
        }

        // 检查是否有选择 RadioButton
        if (type.getCheckedRadioButtonId() == -1) {
            return false;
        }

        // 检查是否有选择票数
        if (spinnerTickets.getSelectedItem() == null) {
            return false;
        }
        return true;
    }
}

