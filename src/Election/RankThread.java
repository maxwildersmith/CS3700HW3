package Election;

public class RankThread extends Thread {
    private volatile Official leader;
    private int count, max;

    public RankThread(int numThreads){
        leader = null;
        max = numThreads;
    }

    public Official getLeader(){
        return leader;
    }

    @Override
    public void run() {
        synchronized (this){
                try {
                    while(count<max) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    leader = null;
                    notifyAll();
                }
    }}

    public void newLeader(Official official){
        count++;
        if(leader==null||leader.getRank()<official.getRank()){
            leader = official;
            notifyAll();
        }
        if(count>=max)
            interrupt();
    }

}
