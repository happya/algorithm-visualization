import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int DELAY = 20;
    private HeapSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, HeapSortData.Type dataType){

        // 初始化数据
        data = new HeapSortData(N, sceneHeight, dataType);
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Quick Sort Three Ways", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, HeapSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){
        setData(data.N());

        // 建堆
        for(int i=(data.N()-1-1)/2;i>=0;i--){
            shiftDown(data.N(), i);
        }
        // 堆排序
        for(int i=data.N()-1;i>-0;i--){
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i);
        }
        setData(0);
    }

    private void shiftDown(int n, int parent){
        while(2*parent+1<n){
            int child = 2*parent + 1;
            if(child+1<n && data.get(child+1)>data.get(child))
                child ++;
            if(data.get(parent) > data.get(child))
                break;
            data.swap(parent, child);
            setData(data.heapIndex);
            parent = child;

        }
    }



    private void setData(int heapIndex) {
        data.heapIndex = heapIndex;


        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        int sceneWidth = 1600;
        int sceneHeight = 1600;
        int N = 100;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N, HeapSortData.Type.Identical);
//        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N);
    }
}
