package Shapes;

import java.awt.*;

public class Triangle extends Shape
{
    //Variable x, y, width and height store the x and y coordinates of the top left corner and the Triangles dimensions.
    private int x, y, width, height;
    //Int arrays to store the x and y coordinates of each of the triangles points.
    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];

    /*Triangle Constructor: assigns the x, y, width and height to the values passed and
     *sends rotation and color to the super class.
     *This also calls the calcPoints method to populate the x and y point arrays.*/
    public Triangle(int x, int y, int width, int height, double rotation, Color color)
    {
        super(rotation, color);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        calcPoints();
    }

    /*This method calculates the three points of the triangle using the width and height of the
     *Triangle with respect to the x and y coordinates, x and y.*/
    private void calcPoints()
    {
        xPoints[0] = (x + (width / 2));
        yPoints[0] = y;
        xPoints[1] = x;
        yPoints[1] = (y + height);
        xPoints[2] = (x + width);
        yPoints[2] = (y + height);
    }

    /*draw method uses a Graphics2D object created from the Graphics object passed so that rotation can be performed.
     *Uses the shapes instance variable to draw the appropriate filled shape.*/
    public void draw(Graphics g)
    {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(rotation, x + (width/2), y + (height/2));
        g2.fillPolygon(xPoints, yPoints, xPoints.length);
        g2.rotate(-rotation, x + (width/2), y + (height/2));
    }

                                    //ACCESS METHODS FOR THE TRIANGLE CLASS//

    //Returns the triangles x coordinate.
    public int getX()
    {return x;}

    //Sets the triangles x coordinate and recalculates the triangles points.
    public void setX(int x)
    {
        this.x = x;
        calcPoints();
    }

    //Returns the triangles y coordinate.
    public int getY()
    {return y;}

    //Sets the triangles y coordinate and recalculates the triangles points.
    public void setY(int y)
    {
        this.y = y;
        calcPoints();
    }

    //Returns the width of the triangle.
    public int getWidth()
    {return width;}

    //Sets the width of the triangle to the int passed and recalculates the triangles points.
    public void setWidth(int width)
    {
        this.width = width;
        calcPoints();
    }

    //Returns the height of the triangle.
    public int getHeight()
    {return height;}

    //Sets the height of the triangle to the int passed and recalculates the triangles points.
    public void setHeight(int height)
    {
        this.height = height;
        calcPoints();
    }

    //Returns the rotation of the triangle.
    public double getRotation()
    {return rotation/(Math.PI/180);}

    //Set the rotation of the triangle to the double passed.
    public void setRotation(double rotation)
    {this.rotation = rotation*(Math.PI/180);}
}
