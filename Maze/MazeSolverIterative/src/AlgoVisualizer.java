import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Stack;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    // 小格子边长
    private static int blockSide = 12;
    private static int DELAY = 5;
    private MazeData data;        // 数据
    private AlgoFrame frame;    // 视图

    private static final int d[][] = {{-1,0},{1,0},{0,1},{0,-1}};

    public AlgoVisualizer(String MazeFile){

        // 初始化数据
        data = new MazeData(MazeFile);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(-1,-1, false);

        Stack<Position> stack = new Stack<Position>();

        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        stack.push(entrance);

        data.visited[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while(!stack.empty()){
            Position curPos = stack.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if(curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()){
                isSolved = true;
                findPath(curPos);
                break;
            }

            for(int i=0;i<4;i++){
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];

                if(data.inArea(newX, newY)
                        && !data.visited[newX][newY]
                        && data.getMaze(newX, newY) == MazeData.ROAD){
                    stack.push(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }
        if(!isSolved)
            System.out.println("The maze has no solution");
        setData(-1,-1, false);
    }

    private void findPath(Position des){
        Position cur = des;
        while(cur != null){
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
    }

    private void setData(int x, int y, boolean isPath) {
        if(data.inArea(x,y)){
            data.path[x][y] = isPath;
        }


        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        String mazeFile = "maze_101_101.txt";
        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(mazeFile);
    }
}
