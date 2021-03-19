import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author RECEP TAYYIP ERDOGAN
 * @since 17.11.2020
 */
public class Config {
    private int height;
    private int width;
    private int numberOfPeople;
    private int numberOfObstacle;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getNumberOfObstacle() {
        return numberOfObstacle;
    }

    public Config(){ // we must write class name to constructor.
        reader();

    }
    public void reader(){
        //var.cfg file directory
        File file = new File("C:\\Users\\erdog\\IdeaProjects\\DataGame\\src\\var.cfg");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(input.hasNext()) {
            String command = input.nextLine();
            command = command.replaceAll(" ", "");
            command = command.toLowerCase();
            String[] commandNumbers = command.split(":");


            if (command.contains("height")) {
                height = Integer.parseInt(commandNumbers[1]);

            } else if (command.contains("width")) {
                width = Integer.parseInt(commandNumbers[1]);
            } else if (command.contains("numberofpeople")) {
                numberOfPeople = Integer.parseInt(commandNumbers[1]);
            } else {
                numberOfObstacle = Integer.parseInt(commandNumbers[1]);
            }
        }
    }
}
