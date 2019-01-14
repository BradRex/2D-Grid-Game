import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
    //Stores a reference to the GameFrame object. Required to retrieve the list of shapes to be drawn by this panel.
    private GameFrame gameFrame;
    //Constant int variable to indicate the dimensions of each grid square.
    private final int SQR_SIZE = 20;
    //Constant int variable to indicate the dimensions of the overall grid (20x20 grid of squares).
    private final int GRID_SIZE = 20;
    //Holds values used to construct the empty game grid.
    private int[] gameGrid = new int[GRID_SIZE*GRID_SIZE];

    /*Constructor for GamePanel. Assigns the gameFrame variable the reference to the main GameFrame object passed.
     *Also constructs the initial gameGrid used to draw the empty grid.*/
    public GamePanel(GameFrame gameFrame)
    {
        this.gameFrame = gameFrame;
        initGrid();
    }

    //Fill the gameGrid array with the values 0-399 inclusive.
    private void initGrid()
    {for(int i = 0; i < 400; i++){gameGrid[i] = i;}}

    //Method for using the gameGrid array to paint empty squares on the GUI.
    private void paintGrid(Graphics g)
    {for(int i : gameGrid){g.drawRect( ((i%GRID_SIZE)*SQR_SIZE), ((i/GRID_SIZE)*SQR_SIZE), SQR_SIZE, SQR_SIZE );}}

    /*Manipulate the Graphics object to paint the empty grid and all currently active shapes to the GUI.
     *This is invoked whenever this panel is added to the GameFrame and when repaint() is invoked.*/
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintGrid(g);
        for(Shapes.Shape shape : gameFrame.shapes){shape.draw(g);}
    }

    //Override the preferred size method to return a new preferred size. Used when the GameFrame is packed (pack()).
    @Override
    public Dimension getPreferredSize(){return new Dimension(GRID_SIZE*SQR_SIZE, GRID_SIZE*SQR_SIZE);}
}
