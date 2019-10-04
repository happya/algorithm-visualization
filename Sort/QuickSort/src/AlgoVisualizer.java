import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int DELAY = 20;
    private QuickSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, QuickSortData.Type dataType){

        // 初始化数据
        data = new QuickSortData(N, sceneHeight, dataType);
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Quick Sort", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){
        setData(-1,-1,-1,-1,-1);
//        quickSort(0, data.N()-1);
        quickSort2Ways(0, data.N()-1);
        setData(-1,-1,-1,-1,-1);
    }
    private void quickSort2Ways(int l, int r){
        if(l>r)
            return;
        if(l == r){
            return;
        }
        int p = partition(l, r);
        quickSort2Ways(l,p-1);
        quickSort2Ways(p+1, r);
    }


    private void quickSort(int l, int r) {
        if(l>r)
            return;
        if(l == r) {
            setData(l, r, l, -1, -1);
            return;
        }
        setData(l, r, -1, -1,-1);
        int p = partition2Ways(l,r);
        quickSort(l,p-1);
        quickSort(p+1, r);
    }
    private int partition2Ways(int l, int r) {
        // 随机选取标定点
        int p = (int)(Math.random()*(r-l+1))+l;
        setData(l, r, -1, p, -1);
        data.swap(l, p);
        int v = data.get(l);
        int i = l+1, j = r;
        while(true){
            while(i<=r && data.get(i)<v)
                i++;
            while(j>=l+1 && data.get(j)>v)
                j--;
            if(i>j)
                break;
            data.swap(i,j);
            i++;
            j--;
        }
        data.swap(l,j);
        return j;
    }

    private int partition(int l, int r){
        // 随机选取标定点
        int p = (int)(Math.random()*(r-l+1))+l;
        setData(l, r, -1, p, -1);
        data.swap(l, p);
        // 选取标定点
        int v = data.get(l);
        setData(l, r, -1, l, -1);
        int j=l;
        // arr[l+1, j]<v, arr[j+1,i)>v
        for(int i=l+1;i<=r;i++){
            setData(l, r, -1, l, i);
            if(data.get(i)<v){
                j ++;
                data.swap(j, i);
                setData(l, r, -1, l, i);
            }
        }
        data.swap(l, j);
        setData(l, r, j, -1,-1);
        return j;
    }
    private void setData(int l, int r, int fixPivot, int curPivot, int curElement) {
        data.l = l;
        data.r = r;
        data.curElement = curElement;
        data.curPivot = curPivot;
        if(fixPivot!=-1)
            data.fixedPivots[fixPivot] = true;

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
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N, QuickSortData.Type.Identical);
    }
}
