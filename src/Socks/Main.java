package Socks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Sock Matching of assignment...\n\n");
        BlockingQueue<Sock> matchQ = new ArrayBlockingQueue<Sock>(400);
        BlockingQueue<Sock[]> washQ = new ArrayBlockingQueue<Sock[]>(200);

        CountDownLatch latch = new CountDownLatch(4);
        int delay = 100;
        SockMaker red = new SockMaker(Sock.SockColor.Red, delay, matchQ, latch);
        SockMaker green = new SockMaker(Sock.SockColor.Green, delay, matchQ, latch);
        SockMaker blue = new SockMaker(Sock.SockColor.Blue, delay, matchQ, latch);
        SockMaker orange = new SockMaker(Sock.SockColor.Orange, delay, matchQ, latch);

        Matcher matcher = new Matcher(matchQ, washQ);
        Washer washer = new Washer(washQ);

        matcher.start();
        washer.start();
        red.start();
        green.start();
        blue.start();
        orange.start();

        try {
            latch.await();
            matcher.finish();
            matcher.join();
            washer.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
