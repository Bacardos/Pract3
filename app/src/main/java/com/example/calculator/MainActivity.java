package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.widget.Toolbar; // вроде как является современной android.support.v7.widget.Toolbar(но это не точно, в случае чего в java и xml заменить на Toolbar

import org.w3c.dom.EntityReference;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    private Toolbar toolbar_calc;
    private Integer global_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.calculate);
        TextView res = (TextView) findViewById(R.id.res);
        EditText value1 = (EditText) findViewById(R.id.value1);
        EditText value2 = (EditText) findViewById(R.id.value2);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toolbar_calc = (Toolbar) findViewById(R.id.toolbar_calc);
        setSupportActionBar(toolbar_calc);
        getSupportActionBar().setTitle("Calculator");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                global_id = id;

                CheckFunc(id);
            }
        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CheckFunc(global_id);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Ошибка, попробуйте еще раз", Toast.LENGTH_SHORT);
                    toast.show();
                    res.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                Intent intent = new Intent("android.intent.action.Information");
                intent.setAction("android.intent.action.Information");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else
                    Log.d(TAG, "Не получается обработать намерение!");
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: OnDestroy");
    }

    private void CheckFunc(Integer id) {
        TextView res = (TextView) findViewById(R.id.res);
        EditText value1 = (EditText) findViewById(R.id.value1);
        EditText value2 = (EditText) findViewById(R.id.value2);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        String str;

        switch (id) {
            case R.id.plus:
                try {
                    toggleButton.setEnabled(true);
                    Float num1 = Float.parseFloat(value1.getText().toString());
                    Float num2 = Float.parseFloat(value2.getText().toString());
                    float sum = num1 + num2;
                    float zero = 0;
                    if (toggleButton.isChecked()) {
                        res.setText(Float.toString(zero));
                    } else {
                        res.setText(Float.toString(sum));
                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите корректное значение", Toast.LENGTH_SHORT);
                    toast.show();
                    res.setText("");
                }
                break;
            case R.id.minus:
                try {
                    toggleButton.setEnabled(true);
                    Float num1 = Float.parseFloat(value1.getText().toString());
                    Float num2 = Float.parseFloat(value2.getText().toString());

                    float sum = num1 - num2;
                    float zero = 0;
                    if (toggleButton.isChecked()) {
                        res.setText(Float.toString(zero));
                    } else {
                        res.setText(Float.toString(sum));
                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите корректное значение", Toast.LENGTH_SHORT);
                    toast.show();
                    res.setText("");
                }
                break;
            case R.id.multiply:
                try {
                    toggleButton.setEnabled(true);
                    Float num1 = Float.parseFloat(value1.getText().toString());
                    Float num2 = Float.parseFloat(value2.getText().toString());
                    float sum = num1 * num2;
                    float zero = 0;
                    if (toggleButton.isChecked()) {
                        res.setText(Float.toString(zero));
                    } else {
                        res.setText(Float.toString(sum));
                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите корректное значение", Toast.LENGTH_SHORT);
                    toast.show();
                    res.setText("");
                }
                break;
            case R.id.division:
                try {
                    toggleButton.setEnabled(true);
                    Float num1 = Float.parseFloat(value1.getText().toString());
                    Float num2 = Float.parseFloat(value2.getText().toString());
                    float sum = num1 / num2;
                    float zero = 0;
                    if (toggleButton.isChecked()) {
                        res.setText(Float.toString(zero));
                    } else {
                        res.setText(Float.toString(sum));
                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите корректное значение", Toast.LENGTH_SHORT);
                    toast.show();
                    res.setText("");
                }
                break;
            case R.id.sqrt:
                try {
                    toggleButton.setEnabled(false);
                    Float num1 = Float.parseFloat(value1.getText().toString());
                    double sum = Math.round(Math.pow(num1, 1.0 / 2.0));
                    res.setText(Double.toString(sum));

                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите корректное значение", Toast.LENGTH_SHORT);
                    toast.show();
                    res.setText("");
                }
                break;
            case R.id.div1:
                try {
                    toggleButton.setEnabled(false);
                    Float num1 = Float.parseFloat(value1.getText().toString());
                    double sum = 1.0/num1;
                    res.setText(Double.toString(sum));
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите корректное значение", Toast.LENGTH_SHORT);
                    toast.show();
                    res.setText("");
                }
                break;
        }
    }
}