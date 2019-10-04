import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int DELAY = 20;
    private ThreeWaysQuickSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, ThreeWaysQuickSortData.Type dataType){

        // 初始化数据
        data = new ThreeWaysQuickSortData(N, sceneHeight, dataType);
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
        this(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){
        setData(-1,-1,-1,-1,-1,-1);
//        quickSort(0, data.N()-1);
//        quickSort2Ways(0, data.N()-1);
        quickSort3Ways(0, data.N()-1);
        setData(-1,-1,-1,-1,-1,-1);
    }
    private void quickSort3Ways(int l, int r){
        if(l>r)
            return;
        if(l == r){
            setData(l,l,l,-1,-1,-1);
            return;
        }
        int p = (int)(Math.random()*(r-l+1))+l;
        setData(l, r, -1, p, -1, -1);
        data.swap(l, p);
        // 标定点值
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);
        // 三路快排的partition
        int lt = l; // [l+1,lt] < v
        int gt = r + 1; // [gt,r] > v
        int i = l + 1; //[lt+1, i) == v
        setData(l, r, -1, l, lt, gt);

        while(i < gt) {
            //当前值比pivot小
            if(data.get(i) < v) {
                lt ++;
                data.swap(i, lt);
                i ++;
            }
            // 当前值比pivot大
            else if (data.get(i) > v) {
                data.swap(i, gt-1);
                gt --;
            }
            else { i ++; }
            setData(l, r, -1, l, i, gt);
        }
        // 扫描结束后，[l,lt]<v, [gt,r]>v,[lt+1,gt-1]==v
        //将l与lt交换，则：[l,lt-1]<v, [lt,gt-1]==v, [gt,r]>v
        data.swap(l, lt);
        setData(l, r, lt, -1, -1, -1);
        quickSort3Ways(l,lt-1);
        quickSort3Ways(gt, r);
    }



    private void setData(int l, int r, int fixPivot, int curPivot, int curL, int curR) {
        data.l = l;
        data.r = r;
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;
        if(fixPivot!=-1) {
            data.fixedPivots[fixPivot] = true;
            int i = fixPivot;
            while(i<data.N() && data.get(i) == data.get(fixPivot)){
                data.fixedPivots[i] = true;
                i ++;
            }
        }


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
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N, ThreeWaysQuickSortData.Type.Identical);
//        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N);
    }
}
