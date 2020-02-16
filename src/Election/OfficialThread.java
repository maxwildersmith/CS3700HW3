package Election;


public class OfficialThread extends Thread {
    private Official leader, self;
    private final RankThread rankThread;
    private boolean done = false;

    public OfficialThread(String name, RankThread rankThread){
        setName(name);
        this.rankThread = rankThread;
        self = new Official(name, (int)((Math.random()-.5)*2*Integer.MAX_VALUE));
        leader = self;
    }

    @Override
    public void run() {
        System.out.println(getName()+": Rank: "+self.getRank()+" Guess for Leader: "+leader.getName());
        synchronized (rankThread) {
            rankThread.newLeader(leader);
            try {
                while(!done) {
                    rankThread.wait();
                    updateLeader();
                    if(leader==null)
                        done = true;
                    else
                        System.out.println(getName()+" thinks "+leader.getName()+" is the leader");
                }
            } catch (InterruptedException e) {
                System.out.println(getName()+" was interrupted");
            }
        }
    }

    private void updateLeader(){
        leader = rankThread.getLeader();
    }
}
