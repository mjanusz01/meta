public class Tests {

    public long[][] KRandomTest(Instance instance, long maxTime) {
        
        long maxTime = 2500;
        long[][] arr = KRandomTest(instance,maxTime);

        AlgorithmHolder ah = new AlgorithmHolder();
        int[] results = new int[(int) (maxTime/100)];
        long timeCount = 100;
        long[][] data = ah.KRandomAlgorithmTest(instance, maxTime, timeCount);
        int i = 0;
        while(i<maxTime/timeCount){
            System.out.println();
        }
    }

    public void runTests(){
        Instance instance = new Instance();
        instance.generateRandomInstanceEUC_2D(50,5000);

        int i = 0;
        System.out.println("Funkcja celu");
        while(i<maxTime/100){
            System.out.println(arr[i]);
            i++;
        }
    }



}
