import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer {
    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;


    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        // 初始化数据
        circles = new Circle[N];
        int R = 50;
        for(int i=0;i<N;i++) {
            int x = (int)(Math.random()*(sceneWidth-R*2))+R;
            int y = (int)(Math.random()*(sceneHeight-R*2))+R;
            int vx = (int)(Math.random()*11 - 5);
            int vy = (int)(Math.random()*11 - 5);
            circles[i] = new Circle(x,y,R,vx,vy);
        }

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                runAnimation();
            }).start();
        });
    }
    // 动画逻辑
    private void runAnimation(){
        while(true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);

            // 更新数据
            if(isAnimated) {
                for(Circle circle: circles)
                    circle.move(0,0, frame.getCanvasWidth(), frame.getCanvasHeight());
            }

        }
    }
    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            // getKeyChar():获取按键的值
            if(event.getKeyChar() == ' '){
                isAnimated = !isAnimated;
            }
        }
    }
    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public  void mousePressed(MouseEvent event) {
            // frame.getBounds()：获取frame这个框
            event.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
            // getPoint() 获取点击位置
            for(Circle circle: circles)
                if(circle.contain(event.getPoint()))
                    circle.isFilled = !circle.isFilled;

        }
    }
}
