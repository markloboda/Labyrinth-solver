import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LabyrinthReader {

    Scanner sc;

    LabyrinthReader(String filePath) throws FileNotFoundException {
        this.sc = new Scanner(new File(filePath));
    }

    int[][] readToMatrix() {
        String[] lines;
        StringBuilder matrixString = new StringBuilder();
        while (sc.hasNextLine()) {
            matrixString.append(sc.nextLine());
            matrixString.append("\n");
        }
        lines = matrixString.toString().split("\n");
        int[][] matrix = null;
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(",");
            if (matrix == null) {
                matrix = new int[lines.length][line.length];
            }
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        return matrix;
    }
}
