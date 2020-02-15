package Election;

public class OfficialThread extends Thread {
    private Official leader;
    private Thread rankThread;
    private int rank;

    public OfficialThread(String name, Thread rankThread){
        setName(name);
        this.rankThread = rankThread;
        rank = (int)(Math.random()-.5)*2*Integer.MAX_VALUE;
    }

    @Override
    public void run() {
        System.out.println(getName()+": Rank: "+rank+" Guess for Leader: "+leader);
        rankThread.interrupt();
    }
}
