
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Instance instance = new Instance();
        //instance.generateRandomInstanceEUC_2D(100,1000); //generowanie 10 losowych punktow o wspolrzednych z przedzialu [0,40]

        /*Parser parser = new Parser();
        File file = new File("data/rat195.tsp");
        parser.setParameters(file,instance);

        Solution solution = new Solution();
        solution = instance.getSolution();*/
        //parser.parseSolution(file, solution);

        //AlgorithmHolder alg = new AlgorithmHolder();

        //solution.randomOrder();
        //long start = System.currentTimeMillis();
        //solution = alg.AccelTwoOptAlgorithm(instance, solution);
        //long end = System.currentTimeMillis();
        //System.out.println("total distance: " + solution.totalDistance());
        //System.out.println("time : " + (end - start) + "ms");*/

        //solution.visualize();

        //instance.setName("instance1");
        //ToFileWriter writer = new ToFileWriter();
        //writer.saveToFile(solution);

        Tests t = new Tests();
        t.runTests();

    }
}
