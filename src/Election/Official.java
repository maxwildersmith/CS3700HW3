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

    @Override
    public String toString() {
        return name + "\'s rank is " + rank;
    }
}
