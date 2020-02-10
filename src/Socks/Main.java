package Socks;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Sock Matching of assignment...\n\n");
        int delay = 10;
        SockMaker red = new SockMaker(Sock.SockColor.Red,delay);
        SockMaker green = new SockMaker(Sock.SockColor.Green,delay);
        SockMaker blue = new SockMaker(Sock.SockColor.Blue,delay);
        SockMaker orange = new SockMaker(Sock.SockColor.Orange,delay);
        red.start();
        green.start();
        blue.start();
        orange.start();
    }
}
