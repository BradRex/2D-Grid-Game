package Shapes;

import java.awt.*;

public class Pie extends Shape
{
    //Variables x, y and radius store the x and y coordinates of the top left corner and the radius of the Pie.
    //Variables startAngle and arcAngle store the angle the arc should start at and the size of the arcs angle.
    private int x, y, radius, startAngle, arcAngle;

    /*Pie Constructor: assigns the x, y, radius, startAngle and arcAngle to the values passed and
     *sends rotation and color to the super class.*/
    public Pie(int x, int y, int radius, int startAngle, int arcAngle, double rotation, Color color)
    {
        super(rotation, color);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    /*draw method uses a Graphics2D object created from the Graphics object passed so that rotation can be performed.
     *Uses the shapes instance variable to draw the appropriate filled shape.*/
    public void draw(Graphics g)
    {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(rotation, x + radius, y + radius);
        g2.fillArc(x, y, radius*2, radius*2, startAngle, arcAngle);
        g2.rotate(-rotation, x + radius, y + radius);
    }

                                    //ACCESS METHODS FOR THE PIE CLASS//

    //Returns the pies x coordinate.
    public int getX()
    {return x;}

    //Sets the pies x coordinate.
    public void setX(int x)
    {this.x = x;}

    //Returns the pies y coordinate.
    public int getY()
    {return y;}

    //Sets the pies y coordinate to the int passed.
    public void setY(int y)
    {this.y = y;}

    //Returns the radius of the pie.
    public int getRadius()
    {return radius;}

    //Sets the radius of the pie to the int passed.
    public void setRadius(int radius)
    {this.radius = radius;}

    //Returns the start angle of the pie.
    public int getStartAngle()
    {return startAngle;}

    //Sets the start angle of the pie to the int passed.
    public void setStartAngle(int startAngle)
    {this.startAngle = startAngle;}

    //Returns the arc angle of the pie.
    public int getArcAngle()
    {return arcAngle;}

    //Sets the arc angle of the pie to the int passed.
    public void setArcAngle(int arcAngle)
    {this.arcAngle = arcAngle;}

    //Returns the rotation of the pie.
    public double getRotation()
    {return rotation/(Math.PI/180);}

    //Set the rotation of the pie to the double passed.
    public void setRotation(double rotation)
    {this.rotation = rotation*(Math.PI/180);}
}
