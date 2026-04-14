import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class copy01 {
    public static void main(String[] args) throws IOException {

        ArrayList<String[]> mapGrid = new ArrayList<>();
        LinkedList<int[]> snake = new LinkedList<>();
        String filePath = "src/map.txt";
        File file = new File(filePath);


        try (Scanner mapScanner = new Scanner(file)) {
            while (mapScanner.hasNextLine()) {
                String data = mapScanner.nextLine();
                String[] row = data.split(" ");
                mapGrid.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        int rows = mapGrid.size();
        int cols = mapGrid.get(0).length;
        String[][] map = new String[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                map[r][c] = mapGrid.get(r)[c];
            }
        }


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }



        if (snake.isEmpty()) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (map[r][c].equals("o")) {
                        snake.addLast(new int[]{r, c});
                    }
                }
            }
        }


        System.out.println("Tail: [" + snake.getFirst()[0] + "," + snake.getFirst()[1] + "]");
        System.out.println("Head: [" + snake.getLast()[0] + "," + snake.getLast()[1] + "]");



        StringBuilder snakeLine = new StringBuilder("SNAKE");
        for (int[] segment : snake) {
            snakeLine.append(" ").append(segment[0]).append(" ").append(segment[1]);
        }

            // movmet
        String dir = args[0].toLowerCase();
        int steps =Integer.parseInt(args[1]);

        for(int step =0; step< steps;step++) {
            int[] head = snake.getLast();
            int headRow = head[0];
            int headCol = head[1];

            int newRow = headRow;
            int newCol = headCol;

            if (dir.equals("up")) newRow = headRow - 1;
            if (dir.equals("down")) newRow = headRow + 1;
            if (dir.equals("left")) newCol = headCol - 1;
            if (dir.equals("right")) newCol = headCol + 1;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                System.out.println("you hit the wall hehehe " + dir);
                break;
            }
            int[] oldTail=snake.getFirst();
            snake.addLast(new int[]{newRow,newCol});
            snake.removeFirst();
            map[oldTail[0]][oldTail[1]]="-";
            map[newRow][newCol] = "o";
        }
        System.out.println("\t=== new map here  === ");
        for (int i=0;i<map.length;i++)
        {
            for(int j =0; j<map[i].length;j++)
            {
                System.out.print(map[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(snakeLine);






    }
}