import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Index {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>(0);
        Scanner kbd = new Scanner(System.in);
        List<String> lnames = new ArrayList<>(0);
        Integer [] goose = {5, 9, 14, 18, 23, 27};
        int c = 0;
        String p = kbd.nextLine();
        do {
            String namePlayer = p.replace("add player ", "");
            if(!lnames.contains(namePlayer)){
                String names = "players : ";
                players.add(new Player(namePlayer, 0));
                lnames.add(p.replace("add player ", ""));
                for (int i = 0; i < c + 1; i++)
                    names += (players.get(i).getName() + ", ");
                names = names.substring(0, names.length() - 2);
                System.out.println(names);
                c++;
            }
            else
                System.out.println(namePlayer+": already existing player");
            p = kbd.nextLine();
        }while(!p.contains("move"));
        //"move Pippo 4, 2"
        do {
            String comand = p.replace("move ", "");
            char lastChar = p.toCharArray()[p.toCharArray().length - 1];
            for(Player pl: players) {
                if(comand.contains(pl.getName())){
                    comand = comand.replace(pl.getName()+ " ", "");
                    int casual1;
                    int casual2;
                    if(Character.isDigit(lastChar)) {
                        casual1 = Integer.parseInt(comand.split(", ")[0]);
                        casual2 = Integer.parseInt(comand.split(", ")[1]);
                        if(casual1<0 || casual1>6)
                            casual1 = (int)(Math.random()*6);
                        if(casual2<0 || casual2>6)
                            casual2 = (int)(Math.random()*6);
                    }
                    else {
                        casual1 = (int)(Math.random()*6+1);
                        casual2 = (int)(Math.random()*6+1);
                    }
                    int pos = pl.getPosition();
                    pl.setPosition(pl.getPosition() + casual1 + casual2);
                    if(pl.getPosition()>63){
                        pl.setPosition(126-pl.getPosition());
                        System.out.println(pl.getName() + " roles " + casual1 + ", " + casual2 + ". " + pl.getName() + " moves from " + pos +
                            " to 63. "+pl.getName()+" bounces! "+pl.getName()+"returns to "+pl.getPosition());
                    }
                    else if(pl.getPosition()==6) {
                        pl.setPosition(12);
                        System.out.println(pl.getName()+" roles "+casual1+", "+casual2+". "+pl.getName()+" moves from "+pos+" to The Bridge. "
                            +pl.getName()+" jumps to 12");
                    }
                    else if(Arrays.asList(goose).contains(pl.getPosition())) {
                        int pos2 = pl.getPosition();
                        pl.setPosition(pl.getPosition() + casual1 + casual2);
                        if(Arrays.asList(goose).contains(pl.getPosition())) {
                            pl.setPosition(pl.getPosition() + casual1 + casual2);
                            System.out.println(pl.getName() + " roles " + casual1 + ", " + casual2 + ". " + pl.getName() + " moves from " + pos +
                                " to " + pos2 + ", The Goose. " + pl.getName() + " moves again and goes to "+(pos2+casual1+casual2)+", The Goose. "
                                +pl.getName()+"moves again and goes to "+ pl.getPosition());
                        }
                        else {
                            System.out.println(pl.getName() + " roles " + casual1 + ", " + casual2 + ". " + pl.getName() + " moves from " + pos +
                                " to " + pos2 + ", The Goose. " + pl.getName() + " moves again and goes to " + pl.getPosition());
                        }
                    }
                    else {
                        String fin = pl.getName() + " roles " + casual1 + ", " + casual2 + ". " + pl.getName() + " moves from " + pos + " to " + pl.getPosition();
                        if(pl.getPosition()==63)
                            fin+=(". "+pl.getName()+" Wins!!");
                        System.out.println(fin);
                    }
                    p = kbd.nextLine();
                    break;
                }
            }
        }while(p.contains("move ") && !p.contains("to"));
    }
}
