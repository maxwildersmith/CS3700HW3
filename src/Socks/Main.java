package Socks;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Sock Matching of assignment...\n\n");
        Matcher matcher = new Matcher();
        matcher.start();

        int delay = 10;
        SockMaker red = new SockMaker(Sock.SockColor.Red,delay,matcher);
        SockMaker green = new SockMaker(Sock.SockColor.Green,delay,matcher);
        SockMaker blue = new SockMaker(Sock.SockColor.Blue,delay,matcher);
        SockMaker orange = new SockMaker(Sock.SockColor.Orange,delay,matcher);
        red.start();
        green.start();
        blue.start();
        orange.start();
    }
}
