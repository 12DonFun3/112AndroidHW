package com.example.hw1_login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到登录按钮
        Button buttonLogin = findViewById(R.id.buttonLogin);
        // 设置按钮的点击事件监听器
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户名和密码输入框的文本内容
                EditText editTextUsername = findViewById(R.id.editTextUsername);
                EditText editTextPassword = findViewById(R.id.editTextPassword);
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // 检查用户名和密码是否正确
                if (username.equals("12345") && password.equals("54321")) {
                    // 登录成功，显示提示消息
                    Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 登录失败，显示错误消息
                    Toast.makeText(MainActivity.this, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
