package Socks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Washer extends Thread {
    private BlockingQueue<Sock[]> washQ;
    private volatile boolean looping=true;

    public Washer(BlockingQueue<Sock[]> washQ){
        this.washQ = washQ;
        this.setName("Washer Thread");
    }

    public void finish(){
        looping = false;
    }

    @Override
    public void run() {
        try {
            while(looping || !washQ.isEmpty()){
                Sock[] tmp = washQ.poll(100, TimeUnit.MILLISECONDS);
                if(tmp!=null)
                    System.out.printf("%s: Destroyed %s socks\n", getName(), tmp[0].getColor());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
