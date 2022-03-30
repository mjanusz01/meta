
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /*Unzipper unzipper = new Unzipper();
        unzipper.unzip(Path.of("data/ft70.atsp.gz"), Path.of("data/ft70.atsp"));
        unzipper.unzip(Path.of("data/ftv33.atsp.gz"), Path.of("data/ftv33.atsp"));
        */

        Tests t = new Tests();
        t.runTests();



        /*ToFileWriter tfw = new ToFileWriter();
        Parser parser = new Parser();
        /*Instance instance = new Instance();
        instance.generateRandomInstanceEUC_2D(40,1000); //generowanie 10 losowych punktow o wspolrzednych z przedzialu [0,40]
        Solution solution = instance.getSolution();*/

        /*File file = new File("data/ch130.opt.tour");
        Solution solution = new Solution();
        parser.parseSolution(file, solution);
        solution.visualize();*/

        /*solution.visualize();
        solution.randomOrder();
        System.out.println("Na gorze: " + solution.order);
        System.out.print("Na dole: ");
        solution.v.printOrder();
        solution.visualize();

        solution.randomOrder();
        System.out.println("Na gorze: " + solution.order);
        System.out.print("Na dole: ");
        solution.v.printOrder();
        solution.visualize();

        solution.randomOrder();
        System.out.println("Na gorze: " + solution.order);
        System.out.print("Na dole: ");
        solution.v.printOrder();
        solution.visualize();*/

        /*AlgorithmHolder alg = new AlgorithmHolder();

        solution.randomOrder();
        solution.printOrder();
        solution.visualize();*/

        /*solution = alg.KRandomAlgorithm(instance,9000000);
        solution.visualize();*/

        /*solution = alg.NearestNeighbor(instance);
        solution.printOrder();
        solution.visualize();

        solution = alg.ExNearestNeighbor(instance);
        solution.printOrder();
        solution.visualize();*/

        /*solution = alg.TwoOptAlgorithm(instance);
        solution.printOrder();
        solution.visualize();*/


        //solution.instance.setName("abc");
        //tfw.saveToFile(solution);


        //instance.setName("aaaaaaaa");
        //tfw.saveToFile(solution);

        /*File file = new File("data/tsp225.tsp");
        Instance i = new Instance();
        parser.setParameters(file,i);
        //i.printMatrix(10);
        Solution s = new Solution();
        s.setFields(i);


        AlgorithmHolder a = new AlgorithmHolder();
        //s = a.ExNearestNeighbor(i);
        //System.out.println(s.totalDistance());
        //s.visualize();

        /*long start = System.currentTimeMillis();
        s = a.TwoOptAlgorithm(i);
        long end = System.currentTimeMillis();
        System.out.println("total distance: " + s.totalDistance());
        System.out.println("time : " + (end - start) + "ms");

        long start1 = System.currentTimeMillis();
        s = a.AccelTwoOptAlgorithm(i);
        long end1 = System.currentTimeMillis();
        System.out.println("total distance: " + s.totalDistance());
        System.out.println("time : " + (end1 - start1) + "ms");*/

        //s.visualize();

        //File file1 = new File("data/hk48.tsp");
        //Instance i1 = new Instance();
        //parser.setParameters(file1,i1);
        //i1.printMatrix(10);

        //i1.setName("test1");
        //ToFileWriter tfw = new ToFileWriter();
        //tfw.saveToFile(i1);

        //File file2 = new File("data/st70.opt.tour");
        //Solution s = new Solution();
        //parser.parseSolution(file2, s);
        //System.out.println("dist " + s.totalDistance());

        //ToFileWriter tfw1 = new ToFileWriter();
        //tfw1.saveToFile(s);

    }
}
