package Election;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting elected officials code...\n");

        System.out.println("How many officials to make?");
        int numOfOfficials = new Scanner(System.in).nextInt();
        RankThread rankThread = new RankThread(numOfOfficials);
        rankThread.start();
        for(int i=0;i<numOfOfficials;i++) {
            new OfficialThread("Leader " + (1+i), rankThread).start();
            sleep((int)(Math.random()*400));
        }
        rankThread.join();
        System.out.println("\nFinished Ordered Leader Election section");
    }
}
