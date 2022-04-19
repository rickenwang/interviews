package huawei;


import java.util.*;

//    定义一个二维数组 N*M ，如 5 × 5 数组下所示：
//
//
//  int maze[5][5] = {
//    0, 1, 0, 0, 0,
//    0, 1, 1, 1, 0,
//    0, 0, 0, 0, 0,
//    0, 1, 1, 1, 0,
//    0, 0, 0, 1, 0,
//    };
//
//
//  它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，
//  要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。


public class HJ43_迷宫问题_DFS_重点关注 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            String[] ss = s.split(" ");
            int x = Integer.valueOf(ss[1]);
            int y = Integer.valueOf(ss[0]);
            int[][] maze = new int[y][x];
            for (int i = 0; i < y; i++) {
                String si = in.nextLine();
                String[] sis = si.split(" ");
                for (int j = 0; j < x; j++) {
                    maze[i][j] = Integer.valueOf(sis[j]);
                }
            }
            stepMaze(maze);
        }
    }


    private static void stepMaze(int[][] maze) {

        // 记录当前路径
        LinkedList<Position> route = new LinkedList<>();
        Position current = new Position(0, 0);
        route.add(current);

        // 记录所有有效路径
        List<LinkedList<Position>> successRoutes = new LinkedList<>();

        // 记录当前已经走过的位置，不能重复走
        boolean[][] step = new boolean[maze.length][maze[0].length];
        setStep(step, current, true);
        dfsStep(maze, current, route, step, successRoutes);

        LinkedList<Position> successRoute = successRoutes.get(0);

        for (Position p: successRoute) {
            System.out.println("("+p.y+","+p.x+")");
        }
    }


    // dfs 深度优先遍历，也可以称为回溯法
    private static void dfsStep(int[][] maze, Position current, LinkedList<Position> route,
                                boolean[][] step, List<LinkedList<Position>> successRoutes) {

        // System.out.println("route to " + current.x + "/" + current.y);

        // 1. 先检查是否已经走到目的地了
        if (maxX(maze) == current.x && maxY(maze) == current.y) {
            successRoutes.add(new LinkedList<>(route));
            // 找到了，回溯找另外的路吧
            route.removeLast();
            Position position = route.getLast();
            dfsStep(maze, position, route, step, successRoutes);
            return;
        }

        // 2. 继续走下面的路，下、右、上、左
        List<Position> nextPositions = new LinkedList<>();
        nextPositions.add(bottom(current));
        nextPositions.add(right(current));
        nextPositions.add(top(current));
        nextPositions.add(left(current));
        boolean hasNextPosition = false;
        for (Position position: nextPositions) {
            if (couldStep(position, maze, step)) {
                hasNextPosition = true;
                // 继续走吧
                route.add(position);
                setStep(step, position, true);
                dfsStep(maze, position, route, step, successRoutes);
            }
        }

        // 3. 如果无路可走，那么进行回溯
        if (!hasNextPosition && !route.isEmpty()) {
            route.removeLast();
            if (!route.isEmpty()) {
                Position position = route.getLast();
                dfsStep(maze, position, route, step, successRoutes);
            }
        }

    }

    // bfs 广度优先遍历，
    private static void bfsStep(int[][] maze, LinkedList<Position> route, Position point) {


    }

    private static int maxX(int[][] maze) {
        return maze[0].length - 1;
    }

    private static int maxY(int[][] maze) {
        return maze.length - 1;
    }

    // 该位置是否可以走
    // int[y][x]
    private static boolean couldStep(Position position, int[][] maze, boolean[][] step) {

        return position.x >= 0 && position.x <= maxX(maze)
                && position.y >=0 && position.y <= maxY(maze)
                && maze[position.y][position.x] == 0
                && !step[position.y][position.x];
    }

    private static Position top(Position current) {
        return new Position(current.x, current.y - 1);
    }

    private static Position bottom(Position current) {
        return new Position(current.x, current.y + 1);
    }

    private static Position left(Position current) {
        return new Position(current.x - 1, current.y);
    }

    private static Position right(Position current) {
        return new Position(current.x + 1, current.y);
    }

    private static void setStep(boolean[][] step, Position position, boolean sign) {
        step[position.y][position.x] = sign;
    }

    static class Position{

        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
