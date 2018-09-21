import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int position;

    int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Player(String name, int position) {
        this.name = name;
        this.position = position;
    }
    /*
    List<Integer> roll(){
        List<Integer> result = new ArrayList<>(0);
        int random1 = (int)(Math.random()*6);
        int random2 = (int)(Math.random()*6);
        result.add(random1);
        result.add(random2);
        return result;
    }
    */
}




