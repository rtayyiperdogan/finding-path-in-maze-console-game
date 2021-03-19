import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author RECEP TAYYIP ERDOGAN
 * @since 17.11.2020
 */
public class Move extends Map {

    ArrayList<String> movedPlayerList = new ArrayList<>();
    int notMovement = 0;
    Scanner input;
    String userSelection;
    int fileCounter = 0;

    public Move() throws IOException{
        input = new Scanner(System.in);
        userSelect();
    }

    public void userSelect() throws IOException{

        System.out.println("Press 'Y' to display game step by step");
        System.out.println("Press 'N' to display all game");

        userSelection = input.nextLine();
        userControl(userSelection.equals("Y"));
    }

    public void findPlayer(String[][] map) throws IOException {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                boolean isMoved = false;
                if(map[i][j].contains("P")){

                    for (int k = 0; k < movedPlayerList.size(); k++) {
                        if(movedPlayerList.get(k).equals(map[i][j])) {
                            isMoved = true;

                        }
                    }

                    if(!isMoved) {
                        movePlayer(map, i, j);
                    }
                }

            }

        }
    }

    public void movePlayer(String [][] map,int x,int y) throws IOException {

        HashSet<String> coordinates = new HashSet<>();
        int randomUpDown = (int)((Math.random()*3)-1);
        int randomLeftRight = (int)((Math.random()*3)-1);

        while (Math.abs(randomUpDown - randomLeftRight) != 1 ||
                !(x+randomUpDown < map.length &&
                y+randomLeftRight < map[0].length &&
                0 <= x+randomUpDown && 0 <= y+randomLeftRight) ||
                (!( map[x+randomUpDown][y+randomLeftRight].contains("00")) &&
                        !(map[x+randomUpDown][y+randomLeftRight].contains("FI")))) {
            coordinates.add(randomUpDown + " " + randomLeftRight);
            randomUpDown = (int)((Math.random()*3))-1;
            randomLeftRight = (int)((Math.random()*3))-1;


            if(coordinates.size() == 9 ) {
                notMovement++;
                if(notMovement == getNumberOfPeople()){
                    System.err.println("Players Cant find available place to " +
                            "return!" );
                    System.exit(-1);
                }
                return;
            }

        }

        if(map[x+randomUpDown][y+randomLeftRight].contains("FI")){
            map[x+randomUpDown][y+randomLeftRight] = map[x][y];
            map[x][y] = "00";
            if(userSelection.equals("Y")) {
                userSelection = input.nextLine();
            }
            printMap();
            System.out.println("Winner Player is " +
                    map[x+randomUpDown][y+randomLeftRight] );
            writetoFile("Winner Player is " +
                    map[x+randomUpDown][y+randomLeftRight]);
            System.exit(0);
        }
        else {

            map[x+randomUpDown][y+randomLeftRight] = map[x][y];
            map[x][y] = "00";
            movedPlayerList.add(map[x+randomUpDown][y+randomLeftRight]);

        }

    }
    public void userControl(boolean condition) throws IOException {

        while(true) {
            printMap();
            writetoFile("");
            System.out.println("------");
            notMovement = 0;
            findPlayer(map);
            movedPlayerList.clear();

            if (condition) {
                userSelection = input.nextLine();
            }
        }
    }
    public void writetoFile(String winner) throws IOException{

        PrintWriter output = null;

            File file = new File(("Step" + fileCounter +".txt"));

            output = new PrintWriter(file);

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    output.printf("%s\t",map[i][j]);
                }
                output.println();

            }
            output.println(winner);
            fileCounter++;
            output.close();
            if(fileCounter == 500){
                System.out.println("There is no available way to reach Final " +
                        "point please Run Program again.");

                for (int i = 0; i < 500; i++) {
                    File file1 = new File("Step"+ i + ".txt");
                    file1.delete();


                }
                System.exit(-1);

            }

    }
}
