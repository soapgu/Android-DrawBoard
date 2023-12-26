package com.soapgu.drawboard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private DrawingView drawingView;
    private boolean red = true;
    private float thickness = 20f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.drawingView = findViewById(R.id.drawing_view);
        findViewById(R.id.button_shift).setOnClickListener( v -> {
            red = !red;
            this.drawingView.setBrushColor( red ? Color.RED : Color.BLUE );
        } );
        findViewById(R.id.button_thickness).setOnClickListener( v -> {
            thickness = (thickness == 20f ? 5f : 20f);
            this.drawingView.setThickness(thickness);
        } );

    }
}