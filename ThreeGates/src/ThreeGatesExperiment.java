public class ThreeGatesExperiment {
    private int N;

    public ThreeGatesExperiment(int N) {
        if(N<=0)
            throw new IllegalArgumentException("N must be positive integer");
        this.N = N;
    }
    private boolean play(boolean changeDoor) {
        // Door 0,1,2
        // 随机出有奖品的门
        int prizeDoor = (int)(Math.random() * 3);
        // 玩家选择的门
        int playerChoice = (int)(Math.random() * 3);
        if(playerChoice == prizeDoor)
            return !changeDoor;
        else
            return changeDoor;

    }
    public void run(boolean changeDoor) {
        int wins = 0;
        for(int i=0;i<N;i++) {
            if(play(changeDoor)) {
                wins++;
            }

        }
        System.out.println(changeDoor ? "Change" : "Not change");
        System.out.println(" winning rate: " + (double)wins/N);
    }
    public static void main(String[] args) {
        int N = 1000000;
        ThreeGatesExperiment exp = new ThreeGatesExperiment(N);
        exp.run(true);
        System.out.println();
        exp.run(false);


    }
}
