import Shapes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

//GameFrame class which extends JFrame as to provide the GUI.
public class GameFrame extends JFrame
{
    //ArrayList to store all of the shapes used in the game.
    protected ArrayList<Shapes.Shape> shapes = new ArrayList<>();

    //ScoresFile object to be used throughout the program to add and retrieve scores from a text file.
    protected ScoresFile scores = new ScoresFile();

    //GamePanel object which will be the base for all graphics drawn in relation to the game play.
    protected GamePanel gamePanel = new GamePanel(this);

    //ScorePanel object which provides the GUI for displaying the recent and all-time top 10 scores.
    protected ScorePanel scorePanel = new ScorePanel(this);

    //Timer object is the base for all game updates. This updates the game periodically, hence, creating a game loop.
    protected Timer timer;

    //JLabel which can be accessed by the mouse listener class to indicate when the game is paused.
    protected JLabel paused;

    /*Instantiate a KeyHandler object which provides a way to interact with the GUI through the keyboard keys.
     *KeyHandler object instantiated outside of the constructor to allow it to be added and removed when switching
     *view.*/
    protected KeyHandler kh1 = new KeyHandler(this);

    /*Instantiate a MouseHandler object which provides a way to interact with the GUI through the mouse buttons.
     *MouseHandler object instantiated outside of the constructor to allow it to be added and removed when switching
     *view.*/
    protected MouseHandler mh1 = new MouseHandler(this);

    //GameFrame constructor. Sets up the initial GUI with: buttons; panels; etc
    public GameFrame()
    {
        //Retrieve the frames content pane and add the initial gamePanel to the Centre.
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        //Instantiate the paused indicator JLabel and ensure it is initially invisible.
        paused = new JLabel("### PAUSED ###");
        paused.setVisible(false);

        /*Add the pause JLabel, KeyListener, MouseListener to the gamePanel. Set this game panel to be focusable and
         *request it to be.*/
        gamePanel.add(paused);
        gamePanel.addKeyListener(kh1);
        gamePanel.addMouseListener(mh1);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        //Instantiate a Jpanel to house the buttons to appear at the bottom of the screen.
        JPanel buttPanel = new JPanel();

        /*Create the Scores button and appropriate button handler. Add this button handler
         *to the button and then add the button to the buttPanel.*/
        ButtonHandler bh1 = new ButtonHandler(1);
        JButton scoreButton = new JButton("Scores");
        scoreButton.addActionListener(bh1);
        buttPanel.add(scoreButton);

        /*Create the Start game button and appropriate button handler. Add this button handler
         *to the button and then add the button to the buttPanel.*/
        ButtonHandler bh2 = new ButtonHandler(2);
        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(bh2);
        buttPanel.add(startGame);

        /*Create the Game board button and appropriate button handler. Add this button handler
         *to the button and then add the button to the buttPanel.*/
        ButtonHandler bh3 = new ButtonHandler(3);
        JButton gameButton = new JButton("Game Board");
        gameButton.addActionListener(bh3);
        buttPanel.add(gameButton);

        //Get the frames content pane and add this button panel to the bottom of the frame.
        getContentPane().add(buttPanel, BorderLayout.SOUTH);

        //Set details of the frame, such as: title and visibility.
        setTitle("Bradley Rex - 1504843 - Sky Fall.");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //Pack the frame. Hence, adjusting the size to that of the components preferred sizes or more than if necessary.
        pack();
    }

    /*Removes the score panel if necessary, adds the game panel to the Game Frame, ensures it is focused,
     *stops the game if necessary, before starting a new game.*/
    private void addGamePanel()
    {
        getContentPane().remove(scorePanel);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        paused.setVisible(false);
        gamePanel.removeMouseListener(mh1);
        gamePanel.addMouseListener(mh1);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        stopGame(-2);
        validate();
        startGame();
    }

    //Removes the game panel, adds the score panel, stops the game if necessary
    private void addScorePanel()
    {
        getContentPane().remove(gamePanel);
        getContentPane().add(scorePanel, BorderLayout.CENTER);
        stopGame(-2);
        validate();
        repaint();
    }

    /*Removes the score panel, clears the shapes list, before adding the game frame, hiding the pause indicator,
     *and stopping the game if necessary.*/
    private void addBaseGamePanel()
    {
        getContentPane().remove(scorePanel);
        shapes.clear();
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        paused.setVisible(false);
        gamePanel.removeMouseListener(mh1);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        stopGame(-2);
        validate();
        repaint();
    }

    /*Clears the shapes list, adds the players shape to the list (Always first element), create the DropBlock
     *actionListener, instantiates the timer with a 75 millisecond delay and then starts said timer.*/
    private void startGame()
    {
        shapes.clear();
        shapes.add(new Circle(200, 380, 10, 0, Color.BLUE));
        DropBlock dropBlock = new DropBlock();
        timer = new Timer(75, dropBlock);
        timer.start();
    }

    /*If the timer hasn't been instantiated yet, do nothing. Otherwise, check if the timer is running.
     *If so, then decide whether it was switching panels which stopped the game or because the player lost.
     *If it was a switching of panels, then just stop the timer.
     *Otherwise, clear the list, get players name via the JOptionPane's input box, add their score the scores file.
     *Then get their rank and inform the Player of their score and all-time ranking via a JOptionPane message dialog.
     *Then stop the timer, and redirect the player to the scores panel to view the leader board.*/
    private void stopGame(int score)
    {
        if(timer == null){}
        else if(timer.isRunning())
        {
            if(score == -2){timer.stop();}
            else
            {
                shapes.clear();
                String name = JOptionPane.showInputDialog(this, "Enter Name: ");
                if(name == null){name = "aaa";}
                else if(name.isEmpty()){name = "aaa";}
                else
                {
                    name = name.split("\\s")[0];
                    if(name.length() > 6){name = name.substring(0, 6);}
                }
                scores.addScore(new Score(name, score, System.currentTimeMillis()));
                int rank = scores.getMostRecentRank();
                JOptionPane.showMessageDialog(this, "You Scored: " + score + " Points. " +
                "Current Rank: " + rank);
                timer.stop();
                addScorePanel();
            }

        }
    }

    /*DropBlock class that implements ActionListener. This forms the base for all game updates and
     *could constitute the game itself.*/
    class DropBlock implements ActionListener
    {
        //Stores the positions, from left to right, of all blocks to be drawn.
        private Vector<Integer> blocksPos= new Vector<>();
        //Indicates the level of the currently falling blocks. Used to determine when the blocks are at player level.
        private int level = 0;
        //Initial score of player. -1 as the score is increased to 0 as soon as the blocks positions are created.
        private int score = -1;
        //Stores a list of Colors to be randomly picked from. Allows blocks to randomly chose a colour.
        private Color[] colors = new Color[]{Color.BLACK, Color.MAGENTA, Color.BLUE, Color.RED};

        //This method performs all game updates and repaints.
        @Override
        public void actionPerformed(ActionEvent e) {
            //If some blocks have been generated, then update their positions.
            if(blocksPos.size() != 0)
            {
                //Create a Circle variable to store reference to the players Circle.
                Circle player;
                //Declare a Shape variable to store reference to a subclass of Shape.
                Shapes.Shape curr;

                //If the blocks reach this level then the player made it through and new blocks should be generated.
                if(level == 20)
                {
                    //Clear the current falling blocks from the list.
                    for(int i = 19; i >= 1 ; i--)
                    {
                        shapes.remove(i);
                    }
                    //Clear the array of block positions.
                    blocksPos.clear();
                    //Reset the level for the next set of falling blocks.
                    level = 0;
                }
                /*Otherwise, the blocks positions must be moved down.
                 *This performs collision detection between the player and the falling blocks.*/
                else
                {
                    /*Set curr to the first falling block. This is determined to decide what shape the blocks are
                     *and hence, how they should be moved down.*/
                    curr =  shapes.get(1);

                    //If the blocks are Squares. Use this block of code to move them down.
                    if(curr instanceof Square)
                    {
                        //Declare a Square object.
                        Square curr1;

                        //Move each falling block down a grid position.
                        for(int i = 1; i < 20; i++)
                        {
                            curr1 = (Square) shapes.get(i);
                            curr1.setY(curr1.getY()+20);
                        }
                        level++;
                        repaint();

                        /*After the blocks have been moved down, check for collision with the player.
                         *If there is, then stop the game, using the stopGame method, along with the players score.*/
                        for(int i = 1; i < 20; i++)
                        {
                            curr1 = (Square) shapes.get(i);
                            player = (Circle) shapes.get(0);
                            if(curr1.getX() == player.getX() && curr1.getY() == player.getY())
                            {stopGame(score); break;}
                        }
                    }
                    //If the blocks are Triangles. Use this block of code to move them down.
                    else if(curr instanceof Triangle)
                    {
                        //Declare a Triangle object.
                        Triangle curr1;

                        //Move each falling block down a grid position.
                        for(int i = 1; i < 20; i++)
                        {
                            curr1 = (Triangle) shapes.get(i);
                            curr1.setY(curr1.getY()+20);
                        }
                        level++;
                        repaint();

                        /*After the blocks have been moved down, check for collision with the player.
                         *If there is, then stop the game, using the stopGame method, along with the players score.*/
                        for(int i = 1; i < 20; i++)
                        {
                            curr1 = (Triangle) shapes.get(i);
                            player = (Circle) shapes.get(0);
                            if(curr1.getX() == player.getX() && curr1.getY() == player.getY())
                            {stopGame(score); break;}
                        }
                    }
                    //If the blocks are Pies. Use this block of code to move them down.
                    else if(curr instanceof Pie)
                    {
                        //Declare a Pie object.
                        Pie curr1;

                        //Move each falling block down a grid position.
                        for(int i = 1; i < 20; i++)
                        {
                            curr1 = (Pie) shapes.get(i);
                            curr1.setY(curr1.getY()+20);
                        }
                        level++;
                        repaint();

                        /*After the blocks have been moved down, check for collision with the player.
                         *If there is, then stop the game, using the stopGame method, along with the players score.*/
                        for(int i = 1; i < 20; i++)
                        {
                            curr1 = (Pie) shapes.get(i);
                            player = (Circle) shapes.get(0);
                            if(curr1.getX() == player.getX() && curr1.getY() == player.getY())
                            {stopGame(score); break;}
                        }
                    }
                }
            }
            //Otherwise, generate some new blocks.
            else
            {
                //Instantiate a Random object.
                Random random = new Random();

                //Generate a random opening point.
                int opening = random.nextInt(20);
                //Generate the list of block positions. Excluding the randomly generated opening.
                for(int i = 0; i < 20; i++)
                {
                    if(i != opening)
                    {
                        blocksPos.add(i);
                    }
                }

                //Generate a random number to be used to randomly choose the shape to be used.
                int shapeNum = random.nextInt(3);
                //Generate a random number to be used to randomly choose the colour of these shapes.
                int colorNum = random.nextInt(4);

                /*Decide what shape was chosen and call the appropriate method
                 *with the number to indicate the random colour.*/
                if(shapeNum == 0){makeSquares(colors[colorNum]);}
                else if(shapeNum == 1){makeTriangles(colors[colorNum]);}
                else if(shapeNum == 2){makePies(colors[colorNum]);}

                /*Each time this entire block of code is called, it can be assumed the player survived the
                 *previous wave. Therefore, increase their score.*/
                score++;
                //Repaint the frame so the newly generate shapes appear on screen.
                repaint();
            }
        }

        //Adds Squares to the shapes list at their initial positions of y = 0.
        private void makeSquares(Color color)
        {
            for(Integer i : blocksPos)
            {
                shapes.add(new Square(i * 20, 0, 20, 0, color));
            }
        }

        //Adds Triangles to the shapes list at their initial positions of y = 0.
        private void makeTriangles(Color color)
        {
            for(Integer i : blocksPos)
            {
                shapes.add(new Triangle(i * 20, 0, 20, 20, 180, color));
            }
        }

        //Adds Pies to the shapes list at their initial positions of y = 0.
        private void makePies(Color color)
        {
            for(Integer i : blocksPos)
            {
                shapes.add(new Pie(i * 20, 0, 10, -60, 300, 0, color));
            }
        }
    }

    //ButtonHandler class that implements ActionListener. This is used to give the buttons their functionality.
    class ButtonHandler implements ActionListener
    {
        //Used to decide on the action to perform based on which button was pressed.
        private int action;

        //Assign the action number passed to the action number instance variable.
        public ButtonHandler(int action)
        {this.action = action;}

        //Chooses the action to be performed when the buttons are pressed.
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //Switch to the score board panel by calling the addScorePanel() method.
            if(action == 1)
            {
                addScorePanel();
            }
            //Switch to the game board panel and start the game by calling the addGamePanel() method.
            else if(action == 2)
            {
                addGamePanel();
            }
            //Switch to the basic game board panel by calling the addBaseGamePanel() method.
            else if(action == 3)
            {
                addBaseGamePanel();
            }
        }
    }
}
