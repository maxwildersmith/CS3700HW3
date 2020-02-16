package Election;

public class Official {
    private String name;
    private int rank;

    public Official(String name, int rank){
        this.name = name;
        this.rank = rank;
    }

    public static String getRandomName(){
        return "";
    }

    public int getRank(){
        return rank;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name + "\'s rank is " + rank;
    }
}
