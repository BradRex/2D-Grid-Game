package Shapes;

import java.awt.*;

public class Circle extends Shape
{
    //Variables x, y and radius store the x and y coordinates of the top left corner and the radius of the Circle.
    private int x, y, radius;

    /*Circle Constructor: assigns the x, y and radius to the values passed and
     *sends rotation and color to the super class.*/
    public Circle(int x, int y, int radius, double rotation, Color color)
    {
        super(rotation, color);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /*draw method uses a Graphics2D object created from the Graphics object passed so that rotation can be performed.
     *Uses the shapes instance variable to draw the appropriate filled shape.*/
    public void draw(Graphics g)
    {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(rotation, x + radius, y + radius);
        g2.fillOval(x, y, radius*2, radius*2);
        g2.rotate(-rotation, x + radius, y + radius);
    }

                                    //ACCESS METHODS FOR THE CIRCLE CLASS//

    //Returns the circles x coordinate.
    public int getX()
    {return x;}

    //Sets the circles x coordinate.
    public void setX(int x)
    {this.x = x;}

    //Returns the circle y coordinate.
    public int getY()
    {return y;}

    //Sets the circles y coordinate to the int passed.
    public void setY(int y)
    {this.y = y;}

    //Returns the radius of the circle.
    public int getRadius()
    {return radius;}

    //Sets the radius of the circle to the int passed.
    public void setRadius(int radius)
    {this.radius = radius;}

    //Returns the rotation of the circle.
    public double getRotation()
    {return rotation/(Math.PI/180);}

    //Set the rotation of the circle to the double passed.
    public void setRotation(double rotation)
    {this.rotation = rotation*(Math.PI/180);}
}
