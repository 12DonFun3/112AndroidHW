package com.example.spandlv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 其他變量聲明
    private Spinner spinnerMealType; // 下拉菜單
    private ListView listViewItems; // 列表視圖
    private TextView tvSelection; // 顯示所選項目的文本視圖
    private String[] mealTypes, mainCourses, sideDishes, drinks; // 餐點類型、主餐、附餐和飲料
    private ArrayAdapter<String> listAdapter; // 適配器用於填充列表視圖
    private String selectedMainCourse, selectedSideDish, selectedDrink; // 保存所選項目的變量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化變量
        mealTypes = getResources().getStringArray(R.array.meal_types); // 獲取餐點類型
        mainCourses = getResources().getStringArray(R.array.main_courses); // 獲取主餐
        sideDishes = getResources().getStringArray(R.array.side_dishes); // 獲取附餐
        drinks = getResources().getStringArray(R.array.drinks); // 獲取飲料

        spinnerMealType = findViewById(R.id.spinnerMealType); // 初始化下拉菜單
        listViewItems = findViewById(R.id.listViewItems); // 初始化列表視圖
        tvSelection = findViewById(R.id.txvSelection); // 初始化顯示所選項目的文本視圖

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mealTypes); // 初始化下拉菜單適配器
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(spinnerAdapter); // 設置下拉菜單適配器

        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1); // 初始化列表視圖適配器
        listViewItems.setAdapter(listAdapter); // 設置列表視圖適配器

        // 下拉菜單選擇監聽器
        spinnerMealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListView(position); // 更新列表視圖
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 什麼都不做
            }
        });

        // 列表視圖點擊監聽器
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                // 根據所選的下拉菜單項目確定類別
                switch (spinnerMealType.getSelectedItemPosition()) {
                    case 0:
                        selectedMainCourse = selectedItem; // 更新所選的主餐
                        break;
                    case 1:
                        selectedSideDish = selectedItem; // 更新所選的附餐
                        break;
                    case 2:
                        selectedDrink = selectedItem; // 更新所選的飲料
                        break;
                }
                updateSelection(); // 更新顯示的選擇
            }
        });
    }

    // 根據所選餐點類型更新列表視圖的方法
    private void updateListView(int categoryPosition) {
        switch (categoryPosition) {
            case 0:
                listAdapter.clear();
                listAdapter.addAll(mainCourses); // 填充主餐
                break;
            case 1:
                listAdapter.clear();
                listAdapter.addAll(sideDishes); // 填充附餐
                break;
            case 2:
                listAdapter.clear();
                listAdapter.addAll(drinks); // 填充飲料
                break;
        }
        listAdapter.notifyDataSetChanged(); // 通知列表適配器數據集已更改
    }

    // 更新顯示的選擇的方法
    private void updateSelection() {
        // 初始化一個空字符串以保存選擇的文本
        String selection = "";
        // 如果所選的主餐不為空且不為空字符串，則將所選的主餐附加到選擇文本中
        if (selectedMainCourse != null && !selectedMainCourse.isEmpty()) {
            selection += "主餐: " + selectedMainCourse + "\n";
        }
        // 如果所選的附餐不為空且不為空字符串，則將所選的附餐附加到選擇文本中
        if (selectedSideDish != null && !selectedSideDish.isEmpty()) {
            selection += "附餐: " + selectedSideDish + "\n";
        }
        // 如果所選的飲料不為空且不為空字符串，則將所選的飲料附加到選擇文本中
        if (selectedDrink != null && !selectedDrink.isEmpty()) {
            selection += "飲料: " + selectedDrink;
        }
        tvSelection.setText(selection); // 設置顯示的選擇文本
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.adjustmenu, menu); // 填充菜單
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.Send) { // 如果選擇的是“發送”
            Intent intent = new Intent(MainActivity.this, SendActivity.class); // 創建意圖以跳轉到發送活動
            intent.putExtra("主餐", selectedMainCourse); // 在意圖中添加所選主餐的數據
            intent.putExtra("附餐", selectedSideDish); // 在意圖中添加所選附餐的數據
            intent.putExtra("飲料", selectedDrink); // 在意圖中添加所選飲料的數據
            startActivity(intent); // 開始發送意圖，跳轉到發送活動
            return true; // 返回true以指示選項已成功處理
        } else if (itemId == R.id.Cancel) { // 如果選擇的是“取消”
            selectedMainCourse = ""; // 清除所選主餐
            selectedSideDish = ""; // 清除所選附餐
            selectedDrink = ""; // 清除所選飲料
            updateSelection(); // 更新顯示的選擇
            return true; // 返回true以指示選項已成功處理
        }
        return super.onOptionsItemSelected(item); // 否則，使用父類的方法處理選項
    }

}
