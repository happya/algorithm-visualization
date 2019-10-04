import java.awt.*;

public class MonteCarloExperiment {
    private int squareSide;
    private int N;
    private int outputInterval = 0;
    public MonteCarloExperiment(int squareSide, int N) {
        if(squareSide<=0 || N<=0)
            throw new IllegalArgumentException("square and N must be positive integer");
        this.squareSide = squareSide;
        this.N = N;
    }
    public void setOutputInterval(int interval){
        if(interval<=0)
            throw new IllegalArgumentException("interval must be positive integer");
        this.outputInterval = interval;
    }
    public void run() {
        Circle circle = new Circle(squareSide/2, squareSide/2, squareSide/2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        for(int i=0;i<N;i++) {
            if(i%outputInterval==0)
                System.out.println(data.estimatePi());
            int x = (int)(Math.random()*squareSide);
            int y = (int)(Math.random()*squareSide);
            data.addPoint(new Point(x,y));
        }
    }

    public static void main(String[] args) {
        int squareSide = 800;
        int N = 1000000;
        MonteCarloExperiment exp = new MonteCarloExperiment(squareSide,N);
        exp.setOutputInterval(10000);
        exp.run();
    }
}
