package Socks;

public class SockMaker extends Thread{
    private Sock.SockColor color;
    private int numOfSocksToMake, numOfSocksMade, delay;
    private Matcher matcher;

    public SockMaker(Sock.SockColor color, int delay, Matcher sockMatcher){
        this.color = color;
        this.delay = delay;
        this.setName(color+" Sock");
        matcher = sockMatcher;
        numOfSocksToMake = (int)(Math.random()*100+1);
    }

    @Override
    public void run() {
        try {
            while (numOfSocksMade <= numOfSocksToMake) {
                System.out.printf("%s: Produced %d of %d %ss\n",this.getName(),++numOfSocksMade,numOfSocksToMake,this.getName());
                matcher.notify();
                sleep(delay);
            }
        } catch (InterruptedException e){
            System.out.println(this.getName()+" was interrupted");
        }
    }
}
