import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.awt.event.MouseEvent.*;

public class MouseHandler implements MouseListener
{
    //Stores a reference to the main GameFrame object. Used to access the Timer instance variable , timer.
    private GameFrame gameFrame;
    //Boolean variable to indicate whether the current game is paused or not.
    private boolean paused = false;

    //Constructor for MouseHandler. Assigns the gameFrame variable a reference to the main GameFrame object passed..
    public MouseHandler(GameFrame gameFrame)
    {this.gameFrame = gameFrame;}

    //Handles events when the mouse buttons are pressed down.
    @Override
    public void mousePressed(MouseEvent e)
    {
        /*Uses the getButton() MouseEvent method to obtain the value of the button pressed. This is then compared to
         *the value of BUTTON1. If they are equal, then button 1 was pressed. BUTTON1 = 1;*/
        if(e.getButton() == BUTTON1)
        {
            //If the timer has not been instantiated, then do nothing.
            if(gameFrame.timer == null){}
            /*Otherwise, check if the game is running. If so, then stop the timer, un-hide the paused JLabel, and
             *change the paused instance variable to true to indicate the game is now paused.*/
            else if(gameFrame.timer.isRunning())
            {
                gameFrame.timer.stop();
                gameFrame.paused.setVisible(true);
                gameFrame.repaint();
                paused = true;
            }
        }
        /*Uses the getButton() MouseEvent method to obtain the value of the button pressed. This is then compared to
         *the value of BUTTON2. If they are equal, then button 2 was pressed. BUTTON2 = 2
         *However, this is not implemented as it is not required for this game.*/
        else if(e.getButton() == BUTTON2)
        {}
        /*Uses the getButton() MouseEvent method to obtain the value of the button pressed. This is then compared to
         *the value of BUTTON3. If they are equal, then button 3 was pressed. BUTTON3 = 3*/
        else if(e.getButton() == BUTTON3)
        {
            //If the timer has not been instantiated, then do nothing.
            if(gameFrame.timer == null){}
            /*Otherwise, check if the game is not running and that its not running because it is paused.
             *If so, then start the timer, hide the paused JLabel,
             *and change the paused instance variable to false to indicate the game is now un-paused.*/
            else if(!gameFrame.timer.isRunning() && paused)
            {
                gameFrame.timer.start();
                gameFrame.paused.setVisible(false);
                gameFrame.repaint();
                paused = false;
            }
        }
    }

    /*This would handle events when a mouse button, for example, is released.
     *However, this has not been implemented as this was not required for this game.*/
    @Override
    public void mouseReleased(MouseEvent e)
    {}

    /*This would handle events when a mouse button, for example, is clicked (pressed and released).
     *However, this has not been implemented as this was not required for this game.*/
    @Override
    public void mouseClicked(MouseEvent e)
    {}

    /*This would handle events when the cursor, for example, enters the component this mouse listener is added too.
     *However, this has not been implemented as this was not required for this game.*/
    @Override
    public void mouseEntered(MouseEvent e)
    {}

    /*This would handle events when the cursor, for example, exits the component this mouse listener is added too.
     *However, this has not been implemented as this was not required for this game.*/
    @Override
    public void mouseExited(MouseEvent e)
    {}
}
