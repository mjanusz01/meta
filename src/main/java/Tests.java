import java.io.File;
import java.io.IOException;


public class Tests {

    public void runTests() throws IOException {

        File file = new File("data/u574.tsp");
        Parser parser = new Parser();
        AlgorithmHolder a = new AlgorithmHolder();
        Instance instance = new Instance();
        parser.setParameters(file, instance);
        Solution s = new Solution();
        s.setFields(instance);

        long maxTime = 10000;
        //long[][] data2opt = TwoOptTest(instance, maxTime);
        long[][] dataAcc2opt = AccelTwoOptTest(instance, maxTime);
        long[][] dataNN = ExNearestNeighborTest(instance, maxTime);
        long[][] dataKrand = KRandomTest(instance, maxTime);
        //s = a.ExNearestNeighbor(instance);
        //System.out.println(s.totalDistance());
        //s.setFields(instance);
        //s = a.TwoOptAlgorithm(instance, s);
        //System.out.println(s.totalDistance());

        //long start;
        //long end;
        /*long sum = 0;
        int iter = 1;
        while(iter<2) {
            s.randomOrder();
            start = System.currentTimeMillis();
            s = a.AccelTwoOptAlgorithm(instance, s);
            end = System.currentTimeMillis();
            sum = sum + (end-start);
            iter++;
        }
        System.out.println(sum);
        sum = 0;
        iter = 1;
        while(iter<2){
            Solution s2 = a.ExNearestNeighbor(instance);
            start = System.currentTimeMillis();
            s2 = a.AccelTwoOptAlgorithm(instance, s2);
            end = System.currentTimeMillis();
            sum = sum + (end-start);
            iter++;
        }
        System.out.println(sum);*/
        /*s.randomOrder();
        start = System.currentTimeMillis();
        s = a.AccelTwoOptAlgorithm(instance, s);
        end = System.currentTimeMillis();
        System.out.println(end-start);
        Solution s2 = a.ExNearestNeighbor(instance);
        start = System.currentTimeMillis();
        s2 = a.AccelTwoOptAlgorithm(instance, s2);
        end = System.currentTimeMillis();
        System.out.println(end-start);*/


    }

    public long[][] KRandomTest(Instance instance, long maxTime) {

        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = maxTime;
        long[][] data = ah.KRandomAlgorithmTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println(data[0][i]);
            i++;
        }
        return data;
    }

    public long[][] ExNearestNeighborTest(Instance instance, long maxTime) {

        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = maxTime;
        long[][] data = ah.ExNearestNeighborTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println(data[0][i]);
            i++;
        }
        return data;
    }

    public long[][] TwoOptTest(Instance instance, long maxTime) {

        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = maxTime;
        long[][] data = ah.TwoOptAlgorithmTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println(data[0][i]);
            i++;
        }
        return data;
    }

    public long[][] AccelTwoOptTest(Instance instance, long maxTime) {
        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = maxTime;
        long[][] data = ah.AccelTwoOptAlgorithmTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println(data[0][i]);
            i++;
        }
        return data;
    }
    public int[][] KRandWith2OPT(File file) throws IOException {
        Parser parser = new Parser();
        Instance instance = new Instance();
        parser.setParameters(file,instance);
        AlgorithmHolder ah = new AlgorithmHolder();
        Solution s = null;
        int k = 1000;
        int maxLimit = 1000000;
        int[][] results = new int[3][maxLimit/k];
        while(k<maxLimit){
            s = ah.KRandomAlgorithm(instance,k);
            results[1][(k/1000)-1] = s.totalDistance();
            //System.out.print(results[1][(k / 1000) - 1] + " ");
            s = ah.TwoOptAlgorithm(instance,s);
            results[2][(k/1000)-1] = s.totalDistance();
            //System.out.println(results[2][(k / 1000) - 1]);
            k=k+1000;
        }
        k =1000;
        while(k<maxLimit){
            System.out.println(results[1][(k / 1000) - 1] + " ");
            k=k+1000;
        }
        System.out.println("-------------------------------");
        k = 1000;
        while(k<maxLimit){
            System.out.println(results[2][(k / 1000) - 1]);
            k=k+1000;
        }

        return results;
    }

    public int[][] NeighborWith2OPT(File file) throws IOException {
        Parser parser = new Parser();
        Instance instance = new Instance();
        parser.setParameters(file,instance);
        AlgorithmHolder ah = new AlgorithmHolder();
        Solution s = null;
        int k = 10;
        int maxLimit = instance.getDimension();
        int[][] results = new int[3][maxLimit/k];
        while(k<maxLimit){
            s = ah.ExNearestNeighborTest2(instance,k);
            results[1][(k/10)-1] = s.totalDistance();
            System.out.println(results[1][(k/10)-1] + " ");
            s = ah.TwoOptAlgorithm(instance,s);
            results[2][(k/10)-1] = s.totalDistance();
            k+=10;
        }
        k = 10;
        while(k<maxLimit){
            System.out.println(results[1][(k/10)-1] + " ");
            k+=10;
        }
        System.out.println("-------------------------------");
        k = 10;
        while(k<maxLimit){
            System.out.println(results[2][(k/10)-1]);
            k+=10;
        }

        return results;
    }

}
