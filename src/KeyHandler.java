import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.event.KeyEvent.*;

public class KeyHandler implements KeyListener
{
    //Stores a reference to the GameFrame object.
    private GameFrame gameFrame;

    //KeyHandler constructor to assign gameFrame a reference to the main GameFrame object passed.
    public KeyHandler(GameFrame gameFrame)
    {this.gameFrame = gameFrame;}

    //Decides on the current action to be performed, based on which keyboard button was pressed.
    @Override
    public void keyPressed(KeyEvent e)
    {
        /*If the key code of the event is equal to the value that is assigned to the up arrow.
         *Then the up arrow was the key that was pressed.*/
        if(e.getKeyCode() == VK_UP)
        {}
        /*If the key code of the event is equal to the value that is assigned to the down arrow.
         *Then the down arrow was the key that was pressed.*/
        else if(e.getKeyCode() == VK_DOWN)
        {}
        /*If the key code of the event is equal to the value that is assigned to the left arrow.
         *Then the left arrow was the key that was pressed.*/
        else if(e.getKeyCode() == VK_LEFT)
        {
            /*Decides if the game is running. If so, then the players shape should be moved a grid square to the left.
             *However, it doesn't allow the player to move out-of-bounds.*/
            if(gameFrame.timer != null && gameFrame.timer.isRunning())
            {
                Shapes.Circle player = (Shapes.Circle) gameFrame.shapes.get(0);
                if(player.getX() != 0){player.setX(player.getX()-20);}
                gameFrame.repaint();
            }
        }
        /*If the key code of the event is equal to the value that is assigned to the right arrow.
         *Then the right arrow was the key that was pressed.*/
        else if(e.getKeyCode() == VK_RIGHT)
        {
            /*Decides if the game is running. If so, then the players shape should be moved a grid square to the right.
             *However, it doesn't allow the player to move out-of-bounds.*/
            if(gameFrame.timer != null && gameFrame.timer.isRunning())
            {
                Shapes.Circle player = (Shapes.Circle) gameFrame.shapes.get(0);
                if (player.getX() != 380) {player.setX(player.getX() + 20);}
                gameFrame.repaint();
            }
        }
    }

    //This would handle events when the key that was pressed is released. Not implemented as not required for this game.
    @Override
    public void keyReleased(KeyEvent e)
    {}

    //This would handle events when a key is pressed and released. Not implemented as not required for this game.
    @Override
    public void keyTyped(KeyEvent e)
    {}
}
