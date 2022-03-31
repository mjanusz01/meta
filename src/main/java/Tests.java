import java.io.File;
import java.io.IOException;


public class Tests {

    public long[][] KRandomTest(Instance instance, long maxTime) {

        AlgorithmHolder ah = new AlgorithmHolder();
        long timeCount = 10;
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
        long timeCount = 10;
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
        long timeCount = 10;
        long[][] data = ah.TwoOptAlgorithmTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println(data[0][i]);
            i++;
        }
        return data;
    }



    public void runTests() throws IOException {
        //Instance instance = new Instance();
        //instance.generateRandomInstanceEUC_2D(600,5000);
        //long maxTime = 5000;
        //long[][] data = TwoOptTest(instance, maxTime);

        /*File file = new File("data/kroA200.tsp");
        int[][] r2 = KRandWith2OPT(file);*/

        /*File file = new File("data/lin105.tsp");
        int[][] r2 = NeighborWith2OPT(file);*/

        File file = new File("data/rat783.tsp");
        Parser parser = new Parser();
        Instance instance = new Instance();
        parser.setParameters(file, instance);
        long maxTime = 100;
        //long[][] data2opt = TwoOptTest(instance, maxTime);
        long[][] dataNN = ExNearestNeighborTest(instance, maxTime);
        //long[][] dataKrand = KRandomTest(instance, maxTime);



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
