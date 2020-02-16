package Socks;

public class Sock {
    public enum SockColor {Red, Green, Blue, Orange};
    private SockColor color;

    public Sock(SockColor color){
        this.color = color;
    }

    public SockColor getColor(){
        return color;
    }
}
