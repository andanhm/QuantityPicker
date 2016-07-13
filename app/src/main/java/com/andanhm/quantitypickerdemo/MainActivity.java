package com.andanhm.quantitypickerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.andanhm.quantitypicker.QuantityPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuantityPicker quantityPicker=(QuantityPicker)findViewById(R.id.quantityPicker);
        if (quantityPicker != null) {
            quantityPicker.setOnQuantityChangeListener(new QuantityPicker.OnQuantityChangeListener() {
                @Override
                public void onValueChanged(int quantity) {
                    Toast.makeText(MainActivity.this,String.valueOf(quantity),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
