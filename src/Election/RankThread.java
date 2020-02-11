package Election;

public class RankThread extends Thread {
    private int currentHighestRank;
    private String currentLeader;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting elected officials code...\n\n");
        RankThread rankThread = new RankThread();
        rankThread.start();
        sleep(100);
        new OfficialThread("Bobby", rankThread).start();
    }

    public RankThread(){
        currentHighestRank = Integer.MIN_VALUE;
        currentLeader = "";
    }

    @Override
    public void run() {
        waitForUpdate();
    }

    private synchronized void waitForUpdate(){
        try {
            while (true)
                this.wait();
        } catch (InterruptedException e) {
            System.out.println("caught!");
        }
    }

}
