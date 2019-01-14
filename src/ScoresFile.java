import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoresFile
{
    //Name of the Scores file to be used. If not found, a file with this name is made.
    private final String SCORES_FILE = "ScoreBoard.txt";
    //Constant variable that represents 24 hours in seconds.
    private final int DAY_IN_SECS = 86400;

    //File readers and writers for appending and retrieve player scores.
    private PrintWriter pWriter;
    private BufferedReader bReader;

    //Attempt to open the file with SCORES_FILE as the filename. Exits program if this can't be accessed.
    public ScoresFile()
    {
        try {openFile();}
        catch(IOException e){closeFile(); System.exit(1);}
    }

    /*Method for opening the file. Creates a file if it doesn't exist, and then assigns the file writer and reader
     *to access this file.*/
    private void openFile() throws IOException
    {
        File scoresFile = new File(SCORES_FILE);
        scoresFile.createNewFile();
        pWriter = new PrintWriter(new BufferedWriter(new FileWriter(SCORES_FILE, true)));
        bReader = new BufferedReader(new FileReader(SCORES_FILE));
    }

    /*Attempts to close the file reader and writer, hence, closing the file.
     *This is also required after the file is written to for the changes to have effect.*/
    private void closeFile()
    {
        try
        {
            pWriter.close();
            bReader.close();
        }
        catch(IOException e){System.exit(1);}
    }

    /*Takes a Score object as an argument and appends it to the Scores file.
     *It uses the convert time Score method to reduce the size of the time attribute before being stored in the file.*/
    public void addScore(Score score)
    {
        try{openFile();}
        catch(IOException e){closeFile(); System.exit(1);}
        score.convertTime();
        pWriter.println(score.toString());
        closeFile();
    }

    //Retrieves an ArrayList containing the top 10 scores achieved in the past 24 hours.
    public ArrayList<Score> getRecentTop10()
    {
        try{openFile();}
        catch(IOException e){closeFile(); System.exit(1);}

        //ArrayList used to store the Scores of the Scores file.
        List<Score> recentTop10 = new ArrayList<>();

        //Get the current time in the reduced format to comply with the Scores time attribute.
        long currTime = (System.currentTimeMillis()/1000)-1482192000;

        /*Retrieves the Score from the Scores file, converts them from strings to
         *Score objects and adds them to the Scores ArrayList.*/
        try
        {
            //Stores a line of the Scores File.
            String line;
            //Array to store the line once split using the delimiter.
            String[] scoreData;

            //While there are lines to be read, convert and add them.
            while((line = bReader.readLine()) != null)
            {
                //Splits the String, line, using \\s. \\s includes all white space such as: \t, " ", etc
                scoreData = line.split("\\s");
                //Create the Score object from the split line.
                Score score = new Score(scoreData[0], Integer.parseInt(scoreData[1]), Long.parseLong(scoreData[2]));
                //As this is only the top 10 in the past 24 hours, only add Scores achieved in the past 24 hours.
                if(currTime-DAY_IN_SECS <= score.getTime()){recentTop10.add(score);}
            }
        }
        catch(IOException e){closeFile(); System.exit(1);}

        //Sort this Array in ascending order of scores. (First element = Lowest score)
        Collections.sort(recentTop10);
        //Reverse the sorted Array as to get the Array in descending order of scores. (First element = Highest score)
        Collections.reverse(recentTop10);
        //If there are more than 10 scores, return a copy of the first 10 elements.
        if(recentTop10.size() > 10){return new ArrayList<>(recentTop10.subList(0, 10));}
        //Otherwise, return a copy of all elements.
        else{return new ArrayList<>(recentTop10);}
    }

    //Retrieves an ArrayList containing the top 10 scores achieved in the overall.
    public ArrayList<Score> getAllTimeTop10()
    {
        try{openFile();}
        catch(IOException e){closeFile(); System.exit(1);}

        //ArrayList used to store the Scores of the Scores file.
        List<Score> allTimeTop10 = new ArrayList<>();

        /*Retrieves the Score from the Scores file, converts them from strings to
         *Score objects and adds them to the Scores ArrayList.*/
        try
        {
            //Stores a line of the Scores File.
            String line;
            //Array to store the line once split using the delimiter.
            String[] scoreData;

            //While there are lines to be read, convert and add them.
            while((line = bReader.readLine()) != null)
            {
                //Splits the String, line, using \\s. \\s includes all white space such as: \t, " ", etc
                scoreData = line.split("\\s");
                //Create the Score object from the split line.
                Score score = new Score(scoreData[0], Integer.parseInt(scoreData[1]), Long.parseLong(scoreData[2]));
                //Add this Score object to the ArrayList.
                allTimeTop10.add(score);
            }
        }
        catch(IOException e){closeFile(); System.exit(1);}

        //Sort this Array in ascending order of scores. (First element = Lowest score)
        Collections.sort(allTimeTop10);
        //Reverse the sorted Array as to get the Array in descending order of scores. (First element = Highest score)
        Collections.reverse(allTimeTop10);
        //If there are more than 10 scores, return a copy of the first 10 elements.
        if(allTimeTop10.size() > 10){return new ArrayList<>(allTimeTop10.subList(0, 10));}
        //Otherwise, return a copy of all elements.
        else{return new ArrayList<>(allTimeTop10);}
    }

    //Gets the rank of the most recently added score. This will, hence, be the current players rank from last attempt.
    public int getMostRecentRank()
    {
        try{openFile();}
        catch(IOException e){closeFile(); System.exit(1);}

        //Create an ArrayList to store all of the scores from the Scores file.
        List<Score> allScores = new ArrayList<>();

        /*Retrieves the Score from the Scores file, converts them from strings to
         *Score objects and adds them to the Scores ArrayList.*/
        try
        {
            //Stores a line of the Scores File.
            String line;
            //Array to store the line once split using the delimiter.
            String[] scoreData;

            //While there are lines to be read, convert and add them.
            while((line = bReader.readLine()) != null)
            {
                //Splits the String, line, using \\s. \\s includes all white space such as: \t, " ", etc
                scoreData = line.split("\\s");
                //Create the Score object from the split line.
                Score score = new Score(scoreData[0], Integer.parseInt(scoreData[1]), Long.parseLong(scoreData[2]));
                //Add this Score object to the ArrayList.
                allScores.add(score);
            }
        }
        catch(IOException e){closeFile(); System.exit(1);}

        //Sort this Array in ascending order of scores. (First element = Lowest score)
        Collections.sort(allScores);
        //Reverse the sorted Array as to get the Array in descending order of scores. (First element = Highest score)
        Collections.reverse(allScores);

        //rank variable to keep track of the rank.
        int rank = 1;
        //Stores the most recent Score added to the file.
        Score mostRecent = allScores.get(0);
        //The current System time in the format used for Scores stored in the Scores file.
        long currTime = (System.currentTimeMillis()/1000)-1482192000;

        //Find the most recent score added to the Scores list.
        for(Score score : allScores)
        {
            if(currTime-score.getTime() < currTime-mostRecent.getTime()){mostRecent = score;}
        }

        //Find the position in the sorted Scores list, hence, obtaining all-time rank indicated by the rank variable.
        for(Score score : allScores)
        {
            if(score.equals(mostRecent)){break;}
            else{rank++;}
        }

        //Return this rank to be presented to the player.
        return rank;
    }
}
