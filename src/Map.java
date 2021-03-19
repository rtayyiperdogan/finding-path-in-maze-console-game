/**
 * @author RECEP TAYYIP ERDOGAN
 * @since 17.11.2020
 */
public class Map extends Config {
    final int FINAL_POINT_NUMBER =1;
    String[][] map;

    public Map() {

        controlInput();
        map = new String[getHeight()][getWidth()];
        fillMap(map);
        String finalPoint = placeFinalPoint(map);
        placeComponents(map, "P", getNumberOfPeople());
        placeComponents(map, "O", getNumberOfObstacle());
        controlFinalPoint(map,finalPoint);

    }
    public void controlInput() {
        if(getWidth()*getHeight() < getNumberOfObstacle() + getNumberOfPeople() +
                FINAL_POINT_NUMBER) {
            System.out.println("Values Are not valid For 2 Dimensional Array");
            System.exit(-1);
        }
    }
    public void fillMap(String[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j <map[0].length ; j++) {
                map[i][j] = "00";
            }
        }
    }

    public String placeFinalPoint(String[][] map) {
        int randomRow = (int)(Math.random()*map.length);
        int randomColumn = (int)(Math.random()*map[0].length);

        map[randomRow][randomColumn] = "FI";
        return randomRow + " " + randomColumn;
    }


    public void placeComponents(String[][] map, String component, int number) {

        int randomRow = (int)(Math.random()*map.length);
        int randomColumn = (int)(Math.random()*map[0].length);

        int i = 0;

        while (i<number) {
            if(map[randomRow][randomColumn].equals("00")) {
                i++;
                map[randomRow][randomColumn] = component + i;
            }
            randomRow = (int) (Math.random() * map.length);
            randomColumn = (int) (Math.random() * map[0].length);
        }

    }

    public void controlFinalPoint(String[][] map,String finalPoint) {
        String[] temp = finalPoint.split(" ");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        int obstacleCounter = 0;
        int validCounter = 0;

        if (0 <= x - 1) {
            if (map[x - 1][y].contains("O")) {
                obstacleCounter++;
            }
        } else {
            validCounter++;
        }
        if (x + 1 < map.length) {
            if (map[x + 1][y].contains("O")) {
                obstacleCounter++;
            }
        } else {
            validCounter++;
        }

        if (0 <= y - 1) {
            if (map[x][y - 1].contains("O")) {
                obstacleCounter++;
            }
        } else {
            validCounter++;
        }

        if (y + 1 < map[0].length) {
            if (map[x][y + 1].contains("O")) {
                obstacleCounter++;
            }
        } else {
            validCounter++;
        }

        if (validCounter + obstacleCounter == 4) {
            System.err.println("There is no way to reach final point.");
            printMap();
            System.exit(-1);
        }
    }

    public void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%s\t",map[i][j]);
            }
            System.out.println();
        }
    }
   }


