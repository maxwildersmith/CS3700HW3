package Socks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SockMaker extends Thread{
    private Sock.SockColor color;
    private int numOfSocksToMake, numOfSocksMade, delay;
    private BlockingQueue<Sock> matcher;
    private CountDownLatch inc;

    public SockMaker(Sock.SockColor color, int maxDelay, BlockingQueue<Sock> sockMatcher, CountDownLatch latch){
        this.color = color;
        this.delay = maxDelay;
        this.setName(color+" Sock");
        this.inc = latch;
        matcher = sockMatcher;
        numOfSocksToMake = (int)(Math.random()*100+1);
    }

    @Override
    public void run() {
        try {
            while (numOfSocksMade < numOfSocksToMake) {
                System.out.printf("%s: Produced %d of %d %ss\n",this.getName(),++numOfSocksMade,numOfSocksToMake,this.getName());
                matcher.put(new Sock(color));
                sleep((int)(delay*Math.random()));
            }

        } catch (InterruptedException e){
            System.out.println(this.getName()+" was interrupted");
        }
        inc.countDown();
    }
}
