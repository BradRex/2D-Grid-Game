package Shapes;

import java.awt.*;

public class Rectangle extends Shape
{
    //Variable x, y, width and height store the x and y coordinates and the Rectangles dimensions.
    private int x, y, width, height;

    /*Rectangle Constructor: assigns the x, y , width and height to the values passed and
     *sends rotation and color to the super class.*/
    public Rectangle(int x, int y, int width, int height, double rotation, Color color)
    {
        super(rotation, color);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /*draw method uses a Graphics2D object created from the Graphics object passed so that rotation can be performed.
     *Uses the shapes instance variable to draw the appropriate filled shape.*/
    public void draw(Graphics g)
    {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(rotation, x + (width/2), y + (height/2));
        g2.fillRect(x, y, width, height);
        g2.rotate(-rotation, x + (width/2), y + (height/2));
    }

                                    //ACCESS METHODS FOR THE RECTANGLE CLASS//

    //Returns the rectangles x coordinate.
    public int getX()
    {return x;}

    //Sets the rectangles x coordinate.
    public void setX(int x)
    {this.x = x;}

    //Returns the rectangles y coordinate.
    public int getY()
    {return y;}

    //Sets the rectangles y coordinate to the int passed.
    public void setY(int y)
    {this.y = y;}

    //Returns the width of the rectangle.
    public int getWidth()
    {return width;}

    //Sets the width of the rectangle to the int passed.
    public void setWidth(int width)
    {this.width = width;}

    //Returns the height of the rectangle.
    public int getHeight()
    {return height;}

    //Sets the height of the rectangle to the int passed.
    public void setHeight(int height)
    {this.height = height;}

    //Returns the rotation of the rectangle.
    public double getRotation()
    {return rotation/(Math.PI/180);}

    //Set the rotation of the rectangle to the double passed.
    public void setRotation(double rotation)
    {this.rotation = rotation*(Math.PI/180);}
}
