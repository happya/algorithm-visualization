import java.awt.*;
import javax.swing.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);

        setResizable(false);
        pack();    // 在setResizable(false)后进行pack()，防止在Windows下系统修改frame的尺寸
        // 具体参见：http://coding.imooc.com/learn/questiondetail/26420.html

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // TODO: 设置自己的数据
    private MazeData data;
    public void render(MazeData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth / data.M();
            int h = canvasWidth / data.N();
            for(int i = 0;i<data.N();i++){
                for(int j=0;j<data.M();j++){
                    if(data.getMaze(i,j)==MazeData.WALL)
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                    else
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                    if(data.path[i][j])
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Yellow);
                    if(data.result[i][j])
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                    AlgoVisHelper.fillRectangle(g2d, j*h, i*w ,w, h);
                }

            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


