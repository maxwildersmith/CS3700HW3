package Socks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Matcher extends Thread{
    private BlockingQueue<Sock> matchQ;
    private BlockingQueue<Sock[]> washQ;
    private Sock redHold, blueHold, greenHold, orangeHold;
    private int total;
    private volatile boolean looping = true;

    public Matcher(BlockingQueue<Sock> matchQ, BlockingQueue<Sock[]> washQ) {
        this.matchQ = matchQ;
        this.washQ = washQ;
        this.setName("Matching Thread");
    }

    public void finish(){
        looping = false;
    }
//total even

    
    @Override
    public void run() {
        try {
            while (looping || !matchQ.isEmpty()) {
                Sock temp = matchQ.poll(100, TimeUnit.MILLISECONDS);
                if(temp != null)
                switch (temp.getColor()) {
                    case Red:
                        if (redHold == null)
                            redHold = temp;
                        else {
                            washQ.put(new Sock[]{redHold, temp});
                            redHold = null;
                            total+=2;
                            System.out.printf("%s: Send Red Socks to Washer. Total socks %d. Total inside queue %d\n",
                                    getName(), total, washQ.size());
                        }
                        break;
                    case Blue:
                        if (blueHold == null)
                            blueHold = temp;
                        else {
                            washQ.put(new Sock[]{blueHold, temp});
                            blueHold = null;
                            total+=2;
                            System.out.printf("%s: Send Blue Socks to Washer. Total socks %d. Total inside queue %d\n",
                                    getName(), total, washQ.size());
                        }
                        break;
                    case Green:
                        if (greenHold == null)
                            greenHold = temp;
                        else {
                            washQ.put(new Sock[]{greenHold, temp});
                            greenHold = null;
                            total+=2;
                            System.out.printf("%s: Send Green Socks to Washer. Total socks %d. Total inside queue %d\n",
                                    getName(), total, washQ.size());
                        }
                        break;
                    case Orange:
                        if (orangeHold == null)
                            orangeHold = temp;
                        else {
                            washQ.put(new Sock[]{orangeHold, temp});
                            orangeHold = null;
                            total+=2;
                            System.out.printf("%s: Send Orange Socks to Washer. Total socks %d. Total inside queue %d\n",
                                    getName(), total, washQ.size());
                        }
                        break;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
