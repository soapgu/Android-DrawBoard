package com.soapgu.drawboard;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.FileIOUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawingView drawingView;
    private boolean red = true;
    private float thickness = 20f;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type mapType = new TypeToken<List<InkStrokeEntity>>() {}.getType();

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

        findViewById(R.id.button_save).setOnClickListener( v -> {
            File file = new File(getExternalFilesDir(null), "save.json");
            writeJsonConfig( file, this.drawingView.getStrokes());
        } );

        findViewById(R.id.button_load).setOnClickListener( v-> {
            File file = new File(getExternalFilesDir(null), "save.json");
            if( file.exists() ){
                List<InkStrokeEntity> strokes = gson.fromJson(FileIOUtils.readFile2String(file), mapType);
                this.drawingView.setStrokes( strokes );
            }

        } );
    }

    private boolean writeJsonConfig(File file , Object src ){
        String jsonContent = gson.toJson(src, mapType);
        return FileIOUtils.writeFileFromString(file, jsonContent);
    }
}