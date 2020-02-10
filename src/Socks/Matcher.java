package Socks;

public class Matcher extends Thread{
    private volatile int numRed, numGreen, numBlue, numOrange;

    public Matcher(){
        setName("Matching Thread");
    }
    @Override
    public void run() {

    }
}
