import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("------------------------------");
        System.out.println("---------Game Started---------");
        System.out.println("------------------------------");

        // ── Load map ──────────────────────────────────────────
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
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mapArray[i][j] = grid.get(i)[j];

        // ── Build snake (rightmost 'o' = head, leftmost 'o' = tail) ──
        LinkedList<int[]> snake = new LinkedList<>();
        for (int row = 0; row < mapArray.length; row++)
            for (int col = 0; col < mapArray[0].length; col++)
                if (mapArray[row][col].equals("o"))
                    snake.addFirst(new int[]{row, col}); // rightmost = head (getFirst)

        System.out.println("Snake Head : " + Arrays.toString(snake.getFirst()));
        System.out.println("Snake Tail : " + Arrays.toString(snake.getLast()));

        // ── Print initial map ──────────────────────────────────
        printMap(mapArray, rows, cols);

        // ── Game Loop ──────────────────────────────────────────
        Scanner input = new Scanner(System.in);
        boolean isMove = true;

        while (isMove) {
            System.out.println("Enter direction (U/D/L/R) or Q to quit: ");
            String dir = input.nextLine();

            // Get current head position
            int[] head = snake.getFirst();
            int newRow = head[0];
            int newCol = head[1];

            switch (dir.toLowerCase()) {
                case "u":
                    System.out.println("Snake moves Up");
                    newRow--;
                    break;
                case "d":
                    System.out.println("Snake moves Down");
                    newRow++;
                    break;
                case "r":
                    System.out.println("Snake moves Right");
                    newCol++;
                    break;
                case "l":
                    System.out.println("Snake moves Left");
                    newCol--;
                    break;
                case "q":
                    System.out.println("Game Over! Goodbye.");
                    isMove = false;
                    continue;
                default:
                    System.out.println("Invalid direction! Use U, D, L, R");
                    continue;
            }

            // ── Check boundaries ───────────────────────────────
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                System.out.println("Cannot move there! Hit the wall.");
                continue;
            }

            // ── Move snake ─────────────────────────────────────
            // 1. Clear old tail from map
            int[] tail = snake.getLast();
            mapArray[tail[0]][tail[1]] = ".";

            // 2. Remove tail from snake list
            snake.removeLast();

            // 3. Add new head to snake list
            snake.addFirst(new int[]{newRow, newCol});

            // 4. Update new head on map
            mapArray[newRow][newCol] = "o";

            // ── Print updated info ─────────────────────────────
            System.out.println("Snake Head : " + Arrays.toString(snake.getFirst()));
            System.out.println("Snake Tail : " + Arrays.toString(snake.getLast()));
            printMap(mapArray, rows, cols);
        }

        input.close();
    }

    // ── Print Map Method ───────────────────────────────────────
    static void printMap(String[][] mapArray, int rows, int cols) {
        System.out.println("\n--- Current Map ---");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mapArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------\n");
    }
}