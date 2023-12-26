package com.soapgu.drawboard;


import android.graphics.Path;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class InkStrokeEntity {
    private int color;
    private float thickness;
    private List<Pair<Float,Float>> points;

    public InkStrokeEntity() {}

    public InkStrokeEntity( int color, float thickness ){
        this.color = color;
        this.thickness = thickness;
        this.points = new ArrayList<>();
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public List<Pair<Float, Float>> getPoints() {
        return points;
    }

    public void setPoints(List<Pair<Float, Float>> points) {
        this.points = points;
    }

    public void add( float x,float y ){
        this.points.add(new Pair<>(x,y));
    }

    public Path createPath(){
        Path path = new Path();
        if( this.points != null && this.points.size() > 0 ){
            path.moveTo( this.points.get(0).first, this.points.get(0).second );
            for( int i=1;i<this.points.size();i++ ){
                path.lineTo( this.points.get(i).first,this.points.get(i).second );
            }
        }
        return path;
    }

}
