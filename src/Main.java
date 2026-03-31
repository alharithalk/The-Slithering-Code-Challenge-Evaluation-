import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        // The Slithering Code Challenge [Evaluation]
        ArrayList<String[]> grid = new ArrayList<>();

        File mapp = new File("src/map.txt");
        Path path = mapp.toPath();
        // try-with-resources: Scanner will be closed automatically
        try (Scanner mapScanner = new Scanner(mapp)) {
            while (mapScanner.hasNextLine()) {
                String data = mapScanner.nextLine();
                //System.out.println(data);
                String[] row = data.split(" ");
                grid.add(row);

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //String[][] grid = new String[0][];
        for (String[] row : grid) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }





        }
    }
