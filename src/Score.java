//This class implement the Comparable interface so that it may be sorted when stored in a collection e.g. ArrayList.
public class Score implements Comparable
{
    //int variable stores the score achieved
    private int score;
    //long variable stores the time the score was achieved.
    private long time;
    //String variable stores the name of the player to have achieved this score at this time.
    private String name;

    //Constant value. TO_SECS converts time from milliseconds to seconds.
    private final int TO_SECS = 1000;
    //Constant value. REDUCE_TIME converts time from the time past since 01/01/1970 to the time past since 01/01/2017.
    private final long REDUCE_TIME = 1482192000;

    //Score class Constructor: Assigns the name, score and time values passed when a score is created.
    public Score(String name, int score, long time)
    {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    //Access method to return the Scores score
    public int getScore(){return score;}

    //Access method to return the Scores time
    public long getTime(){return time;}

    //Access method to return the Scores name
    public String getName(){return name;}

    /*Method to convert the time from the time past in milliseconds since 01/01/1970
     *to time past in seconds since 01/01/2017*/
    protected void convertTime(){time = (time/TO_SECS)-REDUCE_TIME;}

    //Override the compareTo method as part of the Comparable interface to provide a means of comparing Score objects.
    //For times such as, sorting a list of Score objects.
    @Override
    public int compareTo(Object o)
    {
        Score s = (Score) o;
        return Integer.compare(this.score, s.score);
    }

    //Override the equals method to provide a means for comparing equality of Score objects.
    @Override
    public boolean equals(Object o)
    {
        Score s = (Score) o;
        if((this.name.equals(s.name)) && (this.score == s.score) && (this.time == s.time))
        {return true;}
        else{return false;}
    }

    /*Override the toString method to provide a means of representing the Score object as a String.
     *Used for storing the Score object as a string in e.g. a text file.*/
    @Override
    public String toString()
    {
        return name + " " + score + " " + time;
    }
}