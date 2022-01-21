import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MapReader {
    private Scanner scanner;

    public MapReader (File file){
        try {
            scanner = new Scanner(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char[][] readMap(){

            int height = scanner.nextInt();
            int width = scanner.nextInt();

            char[][] gameMap = new char[height][width];

            scanner.nextLine();

            for(int i = 0; i < height; i++){
                String line = scanner.nextLine();
                //System.out.println(line + " has length" + line.length() );
                for (int j = 0; j < width ; j++){
                    gameMap[i][j] = line.charAt(j);
                }
            }

        return gameMap;
    }

}

