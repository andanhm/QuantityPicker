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
        QuantityPicker quantityPicker = (QuantityPicker) findViewById(R.id.quantityPicker);

        //Returns the selected quantity
        quantityPicker.getQuantity();

        //Allows to set the minimum quantity
        quantityPicker.setMinQuantity(1);

        //Allows to set the maximum quantity
        quantityPicker.setMaxQuantity(10);

        //Enable/Disable quantity picker
        quantityPicker.setQuantityPicker(true);

        //To set the quantity text color
        quantityPicker.setQuantityTextColor(R.color.colorPrimaryDark);

        //To set the quantity button color
        quantityPicker.setQuantityButtonColor(R.color.colorAccent);

        quantityPicker.setOnQuantityChangeListener(new QuantityPicker.OnQuantityChangeListener() {
            @Override
            public void onValueChanged(int quantity) {
                // Returns the quantity selected on quantity selection
            }
        });

    }
}
