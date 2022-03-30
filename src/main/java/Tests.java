public class Tests {

    public long[][] KRandomTest(Instance instance, long maxTime) {

        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = 100;
        long[][] data = ah.KRandomAlgorithmTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println(data[0][i] + ", " + data[1][i]);
            i++;
        }
        return data;
    }

    public long[][] ExNearestNeighborTest(Instance instance, long maxTime) {

        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = 100;
        long[][] data = ah.ExNearestNeighborTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            if(data[0][i] != 0 && data[1][i] != 0)
            System.out.println(data[0][i] + ", " + data[1][i]);
            i++;
        }
        return data;
    }

    public long[][] TwoOptTest(Instance instance, long maxTime) {

        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = 100;
        long[][] data = ah.TwoOptAlgorithmTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println(data[0][i] + ", " + data[1][i]);
            i++;
        }
        return data;
    }



    public void runTests(){
        Instance instance = new Instance();
        instance.generateRandomInstanceEUC_2D(600,5000);
        long maxTime = 5000;
        long[][] data = TwoOptTest(instance, maxTime);

    }




}
