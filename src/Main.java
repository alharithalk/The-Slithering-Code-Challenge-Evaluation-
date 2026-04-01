import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // The Slithering Code Challenge [Evaluation]
        System.out.println("------------------------------");
        System.out.println("---------Game Started---------");
        System.out.println("------------------------------");

        ArrayList<String[]> grid = new ArrayList<>();

        File readMap = new File("src/map.txt");

        try (Scanner mapScanner = new Scanner(readMap)) {
            while (mapScanner.hasNextLine()) {
                String data = mapScanner.nextLine();
                String[] row = data.split(" ");
                grid.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int rows = grid.size();
        int cols = grid.get(0).length;

        String[][] mapArray = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mapArray[i][j] = grid.get(i)[j];
            }
        }
        System.out.println("number of rows is : "+rows);
        System.out.println("number of coulmn is : "+cols);


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mapArray[i][j] + " ");
            }
            System.out.println();
        }

        Queue <int[]>snake =new LinkedList<>();

        for(int row=0;row<mapArray.length;row++){
            for(int col=0;col<mapArray[0].length;col++) {
                if (mapArray[col][row].equals("o")) {
                    snake.add(new int[]{row, col});
                }
            }
        }


        for(int[] print:snake){
            System.out.println(Arrays.toString(print) +" ");
        }



    }
}