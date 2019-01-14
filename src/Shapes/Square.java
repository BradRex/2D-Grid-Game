package Shapes;

import java.awt.*;

public class Square extends Shape
{
    //Variables x, y and size store the x and y coordinates and the square dimensions of the Square.
    private int x, y, size;

    /*Square Constructor: assigns the x, y and size to the values passed and
     *sends rotation and color to the super class.*/
    public Square(int x, int y, int size, double rotation, Color color)
    {
        super(rotation, color);
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /*draw method uses a Graphics2D object created from the Graphics object passed so that rotation can be performed.
     *Uses the shapes instance variable to draw the appropriate filled shape.*/
    public void draw(Graphics g)
    {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(rotation, x + (size/2), y + (size/2));
        g2.fillRect(x, y, size, size);
        g2.rotate(-rotation, x + (size/2), y + (size/2));
    }

                                    //ACCESS METHODS FOR THE SQUARE CLASS//

    //Returns the squares x coordinate.
    public int getX()
    {return x;}

    //Sets the squares x coordinate.
    public void setX(int x)
    {this.x = x;}

    //Returns the squares y coordinate.
    public int getY()
    {return y;}

    //Sets the squares y coordinate to the int passed.
    public void setY(int y)
    {this.y = y;}

    //Returns the size of the square.
    public int getSize()
    {return size;}

    //Sets the size of the square to the int passed.
    public void setSize(int size)
    {this.size = size;}

    //Returns the rotation of the square.
    public double getRotation()
    {return rotation/(Math.PI/180);}

    //Set the rotation of the square to the double passed.
    public void setRotation(double rotation)
    {this.rotation = rotation*(Math.PI/180);}
}
