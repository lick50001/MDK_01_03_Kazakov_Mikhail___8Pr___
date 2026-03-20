package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] ArraySpinner = new String[]{
                "Доллар", "Рубль"
        };
        Spinner s =(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ArraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

    public void AlertDialogs(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) { dialog.cancel();}
                })
                .show();
    }

    public void Consider(View view){
        EditText count = findViewById(R.id.count);
        TextView tv = findViewById(R.id.result);
        EditText course = findViewById(R.id.cours);

        float f_count = Float.parseFloat(count.getText().toString());
        float result = 0;
        float f_course = 0;

        Spinner spinner = findViewById(R.id.spinner);
        String selectedCurrency = spinner.getSelectedItem().toString();

        if (selectedCurrency.equals("Доллар")) {
            f_course = 80;
        } else {
            f_course = 1;
        }

        course.setText(String.valueOf(f_course));

        if (count.getText().length() > 0){
            switch (selectedCurrency) {
                case "Доллар":
                    result = f_course * f_count;
                    tv.setText(result + " $");
                    break;
                case "Рубль":
                    result = f_count / f_course;
                    tv.setText(result + " ₽");
                    break;
            }
        }else AlertDialogs("Уведомление", "Введите кол-во доллара.");
    }

    public void URL(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sberbank.ru/ru/quotes/currencies"));
        startActivity(intent);
    }
}