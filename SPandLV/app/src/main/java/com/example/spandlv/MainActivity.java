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

    // Other variable declarations
    private Spinner spinnerMealType;
    private ListView listViewItems;
    private TextView tvSelection;
    private String[] mealTypes, mainCourses, sideDishes, drinks;
    private ArrayAdapter<String> listAdapter;
    private String selectedMainCourse, selectedSideDish, selectedDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        mealTypes = getResources().getStringArray(R.array.meal_types);
        mainCourses = getResources().getStringArray(R.array.main_courses);
        sideDishes = getResources().getStringArray(R.array.side_dishes);
        drinks = getResources().getStringArray(R.array.drinks);

        spinnerMealType = findViewById(R.id.spinnerMealType);
        listViewItems = findViewById(R.id.listViewItems);
        tvSelection = findViewById(R.id.txvSelection);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mealTypes);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(spinnerAdapter);

        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        listViewItems.setAdapter(listAdapter);

        spinnerMealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                switch (spinnerMealType.getSelectedItemPosition()) {
                    case 0:
                        selectedMainCourse = selectedItem;
                        break;
                    case 1:
                        selectedSideDish = selectedItem;
                        break;
                    case 2:
                        selectedDrink = selectedItem;
                        break;
                }
                updateSelection();
            }
        });
    }

    private void updateListView(int categoryPosition) {
        switch (categoryPosition) {
            case 0:
                listAdapter.clear();
                listAdapter.addAll(mainCourses);
                break;
            case 1:
                listAdapter.clear();
                listAdapter.addAll(sideDishes);
                break;
            case 2:
                listAdapter.clear();
                listAdapter.addAll(drinks);
                break;
        }
        listAdapter.notifyDataSetChanged();
    }

    private void updateSelection() {
        String selection = "";
        if (selectedMainCourse != null && !selectedMainCourse.isEmpty()) {
            selection += "主餐: " + selectedMainCourse + "\n";
        }
        if (selectedSideDish != null && !selectedSideDish.isEmpty()) {
            selection += "附餐: " + selectedSideDish + "\n";
        }
        if (selectedDrink != null && !selectedDrink.isEmpty()) {
            selection += "飲料: " + selectedDrink;
        }
        tvSelection.setText(selection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.adjustmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.Send) {
            Intent intent = new Intent(MainActivity.this, SendActivity.class);
            intent.putExtra("主餐", selectedMainCourse);
            intent.putExtra("附餐", selectedSideDish);
            intent.putExtra("飲料", selectedDrink);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.Cancel) {
            selectedMainCourse = "";
            selectedSideDish = "";
            selectedDrink = "";
            updateSelection();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
