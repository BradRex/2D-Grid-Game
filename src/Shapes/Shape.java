package Shapes;

import java.awt.*;

public abstract class Shape
{
    //double variable to store the rotation of the Shape. Share attribute among ALL shapes.
    protected double rotation;
    //Color object variable to store the color the shape should be painted int. Shares attribute among ALL shapes.
    protected Color color;

    /*Shape Constructor: rotation is assigned the value of the rotation passed, after being converted from degrees to
     *radians. The color variable is set to the Color object passed.*/
    public Shape(double rotation, Color color)
    {
        this.rotation = rotation * (Math.PI/180);
        this.color = color;
    }

    //Abstract draw method to ensure each subclass of Shape implements a draw method or declares it abstract.
    public abstract void draw(Graphics g);
}
