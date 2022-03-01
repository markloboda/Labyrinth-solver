import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class PathFinder {

    public static int nodeChecks = 0;
    public static int maxDepth = 0;

    public static void main(String[] args) throws IOException, InterruptedException {


        for (int i = 1; i < 10; i++) {
            System.out.println("++++++++++++++++++" + i + "+++++++++++++++++++++");
            int numOfLab = i;
            String filePath = "./labyrinth/labyrinth_" + numOfLab + ".txt";
            String imgPath = "./labyrinthImg/labyrinth" + numOfLab + ".png";
            LabyrinthReader lr = new LabyrinthReader(filePath);
            int[][] lab = lr.readToMatrix();

            int[] startNode = findStart(lab);
            ArrayList<int[]> treasures = findTreasures(lab);
            int[] endNode = findEnd(lab);

            nodeChecks = 0;


//        int numOfLab = 1;
//        String filePath = "./labyrinth/labyrinth_" + numOfLab + ".txt";
//        String imgPath = "./labyrinthImg/labyrinth" + numOfLab + ".png";
//        LabyrinthReader lr = new LabyrinthReader(filePath);
//        int[][] lab = lr.readToMatrix();
//
//        int[] startNode = findStart(lab);
//        ArrayList<int[]> treasures = findTreasures(lab);
//        int[] endNode = findEnd(lab);

            ////////////////////////////////////////////////////////////

//            Instant start = Instant.now();
//            ArrayList<int[]> pathDFS = searchDFS(lab, startNode, treasures.size(), endNode);
//            Instant end = Instant.now();
//            Duration timeElapsed = Duration.between(start, end);
//            System.out.println(timeElapsed.toNanos());


//        outputPath(pathDFS);
//            outputPrice(pathDFS, lab);
//            outputMovesNum(pathDFS);
//        showPath(imgPath, pathDFS, lab[0].length, lab.length);
//        outputStatistics();

            ////////////////////////////////////////////////////////////

//            startNode = findStart(lab);
//            treasures = findTreasures(lab);

//            Instant start = Instant.now();
//            ArrayList<int[]> pathBFS = searchBFS(lab, startNode, treasures.size(), -3, new Stack<>());
//            Instant end = Instant.now();
//            Duration timeElapsed = Duration.between(start, end);
//            System.out.println(timeElapsed.toNanos());


//        outputPath(pathBFS);
//            outputPrice(pathBFS, lab);
//            outputMovesNum(pathBFS);
//        showPath(imgPath, pathBFS, lab[0].length, lab.length);
//            outputStatistics();

            ////////////////////////////////////////////////////////////

//            startNode = findStart(lab);
//            treasures = findTreasures(lab);
//            Instant start = Instant.now();
//            ArrayList<int[]> pathIDDFS = searchIDDFS(lab, startNode, treasures.size(), -3, new Stack<>());
//            Instant end = Instant.now();
//            Duration timeElapsed = Duration.between(start, end);
//            System.out.println(timeElapsed.toNanos());

//        outputPath(pathIDDFS);
//            System.out.println("- Cena:");
//            System.out.print("      ");
//            outputPrice(pathIDDFS, lab);
//            System.out.println("    - Koraki:");
//            System.out.print("      ");
//            outputMovesNum(pathIDDFS);
//        showPath(imgPath, pathIDDFS, lab[0].length, lab.length);
//            outputStatistics();


            ////////////////////////////////////////////////////////////

//            startNode = findStart(lab);
//            treasures = findTreasures(lab);
//
//            Instant start = Instant.now();
//            ArrayList<int[]> pathAStar = searchAStar(lab, startNode, treasures, endNode, new Stack<>());
//            Instant end = Instant.now();
//            Duration timeElapsed = Duration.between(start, end);
//            System.out.println(timeElapsed.toMillis());

//            outputPath(pathAStar);
//            System.out.println("- Cena:");
//            System.out.print("      ");
//            outputPrice(pathAStar, lab);
//            System.out.println("    - Koraki:");
//            System.out.print("      ");
//            outputMovesNum(pathAStar);
//            outputStatistics();
//        showPath(imgPath, pathAStar, lab[0].length, lab.length);
        }
    }


    static void outputStatistics() {
        System.out.printf("Število obdelanih vozlišč: %d\n", nodeChecks);
    }


    static void showPath(String imgPath, ArrayList<int[]> path, int x, int y) throws IOException, InterruptedException {
        BufferedImage bimg = ImageIO.read(new File(imgPath));
        int width = bimg.getWidth();
        int height = bimg.getHeight();

        double offsetX = ((width / x) / 2.0) / width;
        double offsetY = ((height / y) / 2.0) / height;

        StdDraw.setCanvasSize(width, height);
        StdDraw.picture(0.5, 0.5, imgPath);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < path.size() - 1; i++) {
            StdDraw.line(path.get(i)[1] * 1.0 / x + offsetX, 1 - path.get(i)[0] * 1.0 / x - offsetY, path.get(i + 1)[1] * 1.0 / x + offsetX, 1 - path.get(i + 1)[0] * 1.0 / x - offsetY);
            Thread.sleep(10);
        }
    }

    static void outputPath(ArrayList<int[]> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.printf("[%d, %d] --> ", path.get(i)[0], path.get(i)[1]);
        }
        System.out.println();
    }

    static void outputPrice(ArrayList<int[]> path, int[][] lab) {
        int price = 0;
        for (int i = 0; i < path.size(); i++) {
            int cur = lab[path.get(i)[0]][path.get(i)[1]];
            if (cur > 0) {
                price += cur;
            }
        }
        System.out.println(price);
    }

    static void outputMovesNum(ArrayList<int[]> path) {
        System.out.println(path.size() - 1);
    }

    static int[] findStart(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == -2) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static int[] findEnd(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == -4) {
                    return new int[]{i, j};
                }
            }
        }
        System.out.println("Wrong input!");
        return null;
    }

    static ArrayList<int[]> findTreasures(int[][] graph) {
        ArrayList<int[]> treasures = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == -3) {
                    treasures.add(new int[]{i, j});
                }
            }
        }
        return treasures;
    }

    static ArrayList<int[]> searchDFS(int[][] graph, int[] startNode, int treasureNum, int[] endNode) {
        ArrayList<int[]> solution = null;
        boolean[][] marked = new boolean[graph.length][graph[0].length];

        Stack<int[]> pathStack = new Stack<>();
        boolean endFound = false;
        Stack<int[]> pathToEnd = new Stack<>();

        marked[startNode[0]][startNode[1]] = true;

        pathStack.push(startNode);
        boolean treasureException = false;
        ArrayList<int[]> treasureExceptionArray = new ArrayList<>();

        boolean endLooking = false;


        while (!pathStack.isEmpty() || !endFound) {
            nodeChecks++;
            int[] curNode = pathStack.peek();

            if (treasureNum <= 0) {
                if (endLooking) {
                    endFound = true;
                }
                if (endFound) {
                    solution = new ArrayList<>();
                    pathStack = reverseStack(pathStack);
                    while (!pathStack.empty()) {
                        solution.add(pathStack.pop());
                    }
                    while (!pathToEnd.empty()) {
                        solution.add(pathToEnd.pop());
                    }
                    return solution;
                } else {
                    // work until path to the end found
                    treasureNum += 1;
                    graph[endNode[0]][endNode[1]] = -3;
                    endLooking = true;
                }

            }


            // najdi neobiskanega naslednjika
            boolean found = false;
            for (int i = 0; i < 4; i++) {
                int[] nextNode = nextNode(curNode, i);
                nodeChecks++;
                if (graph[nextNode[0]][nextNode[1]] != -1 && !marked[nextNode[0]][nextNode[1]]) {
                    if (graph[nextNode[0]][nextNode[1]] == -3 && !marked[nextNode[0]][nextNode[1]]) {
                        treasureNum--;
                        if (endFound && treasureNum == 0) {
                            pathToEnd.push(nextNode);
                        }
                    }
                    marked[nextNode[0]][nextNode[1]] = true;

                    if (treasureException) {
                        for (int j = treasureExceptionArray.size() - 1; j >= 0; j--) {
                            pathStack.push(treasureExceptionArray.get(j));
                        }
                        for (int j = 1; j < treasureExceptionArray.size(); j++) {
                            pathStack.push(treasureExceptionArray.get(j));
                        }
                        treasureExceptionArray.clear();
                        pathStack.push(curNode);
                        treasureException = false;
                    }
                    pathStack.push(nextNode);

                    found = true;
                    break;
                }
            }
            if (graph[curNode[0]][curNode[1]] == -4) {
                endFound = true;
            }

            if (endFound) {
                pathToEnd.push(curNode);
            }

            if (!found) {
                if (graph[curNode[0]][curNode[1]] == -3) {
                    treasureException = true;
                }
                if (treasureException) {
                    treasureExceptionArray.add(curNode);
                }
                pathStack.pop();
            }
        }
        return solution;
    }


    static ArrayList<int[]> searchBFS(int[][] graph, int[] startNode, int treasureNum, int searching, Stack<int[]> stack) {
        boolean[][] marked = new boolean[graph.length][graph[0].length];
        int[][][] from = new int[graph.length][graph[0].length][2];
        Stack<int[]> thisPath = new Stack<>();
        ArrayList<int[]> solution;

        Queue<int[]> queue = new LinkedList<>();

        marked[startNode[0]][startNode[1]] = true;
        from[startNode[0]][startNode[1]] = new int[]{-1, -1};

        queue.add(startNode);

        while (!queue.isEmpty()) {
            nodeChecks++;
            int[] curNode = queue.remove();

            for (int i = 0; i < 4; i++) {
                int[] nextNode = nextNode(curNode, i);
                nodeChecks++;
                if (graph.length > nextNode[0] && graph[0].length > nextNode[1] && graph[nextNode[0]][nextNode[1]] != -1 && !marked[nextNode[0]][nextNode[1]]) {
                    marked[nextNode[0]][nextNode[1]] = true;
                    from[nextNode[0]][nextNode[1]] = curNode;
                    queue.add(nextNode);
                }
            }

            if (graph[curNode[0]][curNode[1]] == searching) {
                treasureNum--;
                graph[curNode[0]][curNode[1]] = -2;
                int[] last = curNode;
                while (true) {
                    curNode = from[curNode[0]][curNode[1]];
                    if (curNode[0] != -1) {
                        thisPath.push(curNode);
                    } else {
                        stack = mergeStack(stack, thisPath);
                        break;
                    }
                }
                if (searching == -4) {
                    // izpis
                    stack = reverseStack(stack);
                    solution = new ArrayList<>();
                    while (!stack.isEmpty()) {
                        solution.add(stack.pop());
                    }
                    solution.add(last);
                    return solution;

                }
                if (treasureNum == 0) {
                    searching = -4;
                }
                return searchBFS(graph, last, treasureNum, searching, stack);
            }
        }
        return null;
    }

    static ArrayList<int[]> searchIDDFS(int[][] graph, int[] startNode, int treasureNum, int searching, Stack<int[]> mainStack) {
        ArrayList<int[]> solution = null;
        Stack<int[]> thisPath = new Stack<>();
        int[] last;


        for (int depthLimit = 0; depthLimit < graph.length * graph[0].length; depthLimit++) {
            boolean[][] marked = new boolean[graph.length][graph[0].length];
            int[][][] from = new int[graph.length][graph[0].length][2];
            Stack<int[]> stack = new Stack<>();


            from[startNode[0]][startNode[1]] = new int[]{-1, -1};
            marked[startNode[0]][startNode[1]] = true;
            stack.push(startNode);

            while (!stack.isEmpty()) {
                nodeChecks++;
                int[] curNode = stack.peek();

                if (graph[curNode[0]][curNode[1]] == searching) {
                    treasureNum--;
                    graph[curNode[0]][curNode[1]] = -2;
                    last = curNode;
                    while (true) {
                        curNode = from[curNode[0]][curNode[1]];
                        if (curNode[0] != -1) {
                            thisPath.push(curNode);
                        } else {
                            mainStack = mergeStack(mainStack, thisPath);
                            break;
                        }
                    }
                    if (searching == -4) {
                        // izpis
                        mainStack = reverseStack(mainStack);
                        solution = new ArrayList<>();
                        while (!mainStack.isEmpty()) {
                            solution.add(mainStack.pop());
                        }
                        solution.add(last);
                        return solution;

                    }
                    if (treasureNum == 0) {
                        searching = -4;
                    }
                    return searchIDDFS(graph, last, treasureNum, searching, mainStack);
                }

                boolean found = false;
                if (stack.size() <= depthLimit) {
                    // najdi neobiskanega naslednjika

                    for (int i = 0; i < 4; i++) {
                        nodeChecks++;
                        int[] nextNode = nextNode(curNode, i);
                        if (graph[nextNode[0]][nextNode[1]] != -1 && !marked[nextNode[0]][nextNode[1]]) {
                            marked[nextNode[0]][nextNode[1]] = true;
                            from[nextNode[0]][nextNode[1]] = curNode;
                            stack.push(nextNode);

                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    stack.pop();
                }
            }

        }
        return null;
    }


    public static ArrayList<int[]> searchAStar(int[][] graph, int[] startNode, ArrayList<int[]> searching, int[] endNode, Stack<int[]> pathStack) {
        LinkedList<int[]> open = new LinkedList<>();
        boolean[][] closed = new boolean[graph.length][graph[0].length];
        int[][][] from = new int[graph.length][graph[0].length][2];
        int[] last = new int[0];

        int[][] gScore = new int[graph.length][graph[0].length];
        int[][] fScore = new int[graph.length][graph[0].length];

        for (int i = 0; i < gScore.length; i++) {
            for (int j = 0; j < gScore[0].length; j++) {
                gScore[i][j] = Integer.MAX_VALUE;
                fScore[i][j] = Integer.MAX_VALUE;
            }
        }

        gScore[startNode[0]][startNode[1]] = 0;
        fScore[startNode[0]][startNode[1]] = calculateHeuristicManhatn(startNode, searching);
        from[startNode[0]][startNode[1]] = new int[]{-1, -1};

        open.add(startNode);

        while (!open.isEmpty()) {
            nodeChecks++;
            int minVal = Integer.MAX_VALUE;
            int minPos = 0;
            int[] curNode = new int[]{0, 0};

            for (int i = 0; i < open.size(); i++) {
                int[] node = open.get(i);
                if (fScore[node[0]][node[1]] < minVal) {
                    minVal = fScore[node[0]][node[1]];
                    minPos = i;
                    curNode = node;
                }
            }

            open.remove(minPos);
            closed[curNode[0]][curNode[1]] = true;

            if (graph[curNode[0]][curNode[1]] == -3) {
                last = curNode;
                // remove from treasures
                graph[curNode[0]][curNode[1]] = -2;
                for (int i = 0; i < searching.size(); i++) {
                    if (searching.get(i)[0] == curNode[0] && searching.get(i)[1] == curNode[1]) {
                        searching.remove(i);
                        break;
                    }
                }
                // add to pathStack
                Stack<int[]> thisPathStack = new Stack<>();
                while (true) {
                    curNode = from[curNode[0]][curNode[1]];
                    if (curNode[0] != -1)
                        thisPathStack.add(curNode);
                    else
                        break;
                }
                pathStack = mergeStack(pathStack, thisPathStack);
                if (searching.size() == 0) {
                    pathStack.push(last);                   // zato, da lahko v naslednji rekurziji klicemo start na koncni node
                }
                return searchAStar(graph, last, searching, endNode, pathStack);
            }

            if (searching.size() <= 0) {
                if (endNode == null) {
                    // izpis
                    ArrayList<int[]> solution = new ArrayList<>();
                    pathStack = reverseStack(pathStack);
                    while (!pathStack.empty()) {
                        solution.add(pathStack.pop());
                    }
                    return solution;
                }

                // find end
                searching.add(endNode);

                // chanage end node to -3 for the if
                graph[endNode[0]][endNode[1]] = -3;
                return searchAStar(graph, pathStack.peek(), searching, null, pathStack);
            }

            // check if end found already

            for (int i = 0; i < 4; i++) {
                int[] nextNode = nextNode(curNode, i);
                if (graph[nextNode[0]][nextNode[1]] != -1 && !closed[nextNode[0]][nextNode[1]]) {
                    if (!open.contains(nextNode)) {
                        open.add(nextNode);
                    }
                    int dist = gScore[curNode[0]][curNode[1]] + graph[nextNode[0]][nextNode[1]];

                    if (dist < gScore[nextNode[0]][nextNode[1]]) {
                        from[nextNode[0]][nextNode[1]] = curNode;
                        gScore[nextNode[0]][nextNode[1]] = dist;
                        fScore[nextNode[0]][nextNode[1]] = gScore[nextNode[0]][nextNode[1]] + calculateHeuristicManhatn(nextNode, searching);
                    }
                }
            }
        }
        return null;
    }

    static int calculateHeuristicManhatn(int[] node, ArrayList<int[]> treasures) {
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < treasures.size(); i++) {
            int x = Math.abs(treasures.get(i)[0] - node[0]);
            int y = Math.abs(treasures.get(i)[1] - node[1]);
            int curCost = x + y;
            if (curCost < minCost) {
                minCost = curCost;
            }
        }
        return minCost;
    }


    static int[] nextNode(int[] curNode, int side) {
        int[][] sides = new int[][]{new int[]{0, -1}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0}};
        int[] newNode = new int[]{curNode[0], curNode[1]};
        for (int i = 0; i < 2; i++) {
            newNode[i] += sides[side][i];
        }
        return newNode;
    }

    static Stack<int[]> reverseStack(Stack<int[]> stack) {
        Stack<int[]> temp = new Stack<>();
        while (!stack.empty()) {
            temp.push(stack.pop());
        }
        return temp;
    }

    static Stack<int[]> mergeStack(Stack<int[]> stack1, Stack<int[]> stack2) {
        Stack<int[]> newStack = new Stack<>();
        stack1 = reverseStack(stack1);
        while (!stack1.isEmpty()) {
            newStack.push(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            newStack.push(stack2.pop());
        }
        return newStack;
    }
}
