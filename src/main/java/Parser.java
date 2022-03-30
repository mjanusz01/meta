
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;


public class Parser {

    public void setParameters(File file, Instance instance) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = "";

        List<String> tokens;
        StringTokenizer tokenizer;

        while ((line = br.readLine()) != null) {
            if (line.startsWith("NODE_COORD_SECTION") || line.startsWith("EDGE_WEIGHT_SECTION")) break;

            tokens = new ArrayList<>();
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }

            if(tokens.get(0).startsWith("NAME")){
                if(tokens.get(1).equals(":")) instance.setName(tokens.get(2));
                else instance.setName(tokens.get(1));
            }
            else if(tokens.get(0).startsWith("TYPE")){
                if(tokens.get(1).equals(":")) instance.setType(Instance.type_enum.valueOf(tokens.get(2)));
                else instance.setType(Instance.type_enum.valueOf(tokens.get(1)));
            }
            else if(tokens.get(0).startsWith("DIMENSION")){
                if(tokens.get(1).equals(":")) instance.setDimension(Integer.parseInt(tokens.get(2)));
                else instance.setDimension(Integer.parseInt(tokens.get(1)));
            }
            else if(tokens.get(0).startsWith("EDGE_WEIGHT_TYPE")){
                if(tokens.get(1).equals(":")) instance.setEdge_weight_type(Instance.edge_weight_type_enum.valueOf(tokens.get(2)));
                else instance.setEdge_weight_type(Instance.edge_weight_type_enum.valueOf(tokens.get(1)));
            }
            else if(tokens.get(0).startsWith("EDGE_WEIGHT_FORMAT")){
                if(tokens.get(1).equals(":")) instance.setEdge_weight_format(Instance.edge_weight_format_enum.valueOf(tokens.get(2)));
                else instance.setEdge_weight_format(Instance.edge_weight_format_enum.valueOf(tokens.get(1)));
            }

        }

        if (instance.getEdge_weight_type() == Instance.edge_weight_type_enum.EUC_2D) {
            parseEUC_2D(file, instance);
        } else if (instance.getEdge_weight_type() == Instance.edge_weight_type_enum.EXPLICIT &&
                instance.getEdge_weight_format() == Instance.edge_weight_format_enum.FULL_MATRIX) {
            parseFULL_MATRIX(file, instance);
        } else if (instance.getEdge_weight_type() == Instance.edge_weight_type_enum.EXPLICIT &&
                instance.getEdge_weight_format() == Instance.edge_weight_format_enum.LOWER_DIAG_ROW) {
            parseLOWER_DIAG_ROW(file, instance);
        }

    }

    public void parseEUC_2D(File file, Instance instance) throws IOException {

        instance.node_coord_list = new ArrayList<>();

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();

        List<String> tokens = null;
        StringTokenizer tokenizer;

        while (!(line.startsWith("NODE_COORD_SECTION"))) {
            line = br.readLine();
        }
        int i = 0;
        while(!(line = br.readLine()).startsWith("EOF")){
            tokens = new ArrayList<>();
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }
            Point2D.Double point = new Point2D.Double();
            point.setLocation(Double.parseDouble(tokens.get(1)),Double.parseDouble(tokens.get(2)));
            instance.node_coord_list.add(i,point);
            i++;
        }
        EUC2D_toMatrix(instance);



    }

    public void EUC2D_toMatrix(Instance instance){

        int a = 0;
        int b = 0;
        int matrix[][] = new int[instance.node_coord_list.size()][instance.node_coord_list.size()];
        while(a<instance.node_coord_list.size()){
            b = 0;
            while(b<instance.node_coord_list.size()){
                matrix[a][b] = instance.getDistance(a,b);
                b++;
            }
            a++;
        }
        instance.edge_weight_matrix = matrix;

    }

    public void parseFULL_MATRIX(File file, Instance instance) throws IOException {

        instance.edge_weight_matrix = new int[instance.getDimension()][instance.getDimension()];

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer;

        while (!(line.startsWith("EDGE_WEIGHT_SECTION"))) {
            line = br.readLine();
        }
        int i = 0;
        while(!(line = br.readLine()).startsWith("EOF")){
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }
            while (tokens.size() >= instance.getDimension()) {
                for (int j = 0; j < instance.getDimension(); j++) {
                    instance.edge_weight_matrix[i][j] = Integer.parseInt(tokens.get(j));
                }
                i++;
                for (int j = 0; j < instance.getDimension(); j++) {
                    tokens.remove(0);
                }
            }
            if(i >= instance.getDimension()) break;

        }
        //instance.printMatrix(i-1);
    }

    public void parseLOWER_DIAG_ROW(File file, Instance instance) throws IOException {

        instance.edge_weight_matrix = new int[instance.getDimension()][instance.getDimension()];

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer;

        while (!(line.startsWith("EDGE_WEIGHT_SECTION"))) {
            line = br.readLine();
        }
        int i = 0;
        while(!(line = br.readLine()).startsWith("EOF")){
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }
            while (tokens.size() >= i + 1) {
                for (int j = 0; j < instance.getDimension(); j++) {
                    if (j <= i) {
                        instance.edge_weight_matrix[i][j] = Integer.parseInt(tokens.get(j));
                        instance.edge_weight_matrix[j][i] = Integer.parseInt(tokens.get(j));
                    }

                }
                for (int j = 0; j < i + 1; j++) {
                    tokens.remove(0);
                }
                i++;
            }
            if(i >= instance.getDimension()) break;

        }
        //instance.printMatrix(i-1);
    }

    public void parseSolution(File file, Solution solution) throws IOException {

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder content = new StringBuilder();
        String line = "";
        List<String> tokens;
        StringTokenizer tokenizer;

        while ((line = br.readLine()) != null) {

            tokens = new ArrayList<>();
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }

            if(tokens.get(0).startsWith("NAME")){
                String filename;
                if(tokens.get(1).equals(":")) filename = "data/" + tokens.get(2);
                else filename = "data/" + tokens.get(1);
                filename = filename.substring(0, filename.indexOf('.'));


                if(!((new File(filename + ".tsp")).exists() || (new File(filename + ".atsp")).exists())) {
                    throw new FileNotFoundException();
                }
                File inst_file = new File(filename + ".tsp");
                if(!(inst_file.exists())) inst_file = new File(filename + ".atsp");

                Instance inst = new Instance();
                setParameters(inst_file, inst);
                solution.setFields(inst);
                solution.order = new ArrayList<>();

            } else if(tokens.get(0).startsWith("DIMENSION")) {
                if(tokens.get(1).equals(":")) solution.size = Integer.parseInt(tokens.get(2));
                else solution.size = Integer.parseInt(tokens.get(1));

            } else if(tokens.get(0).startsWith("TOUR_SECTION")) {
                while (!((line = br.readLine()).startsWith("-1"))) {
                    tokens = new ArrayList<>();
                    tokenizer = new StringTokenizer(line, " ");
                    while (tokenizer.hasMoreElements()) {
                        tokens.add(tokenizer.nextToken());
                    }
                    solution.order.add(Integer.valueOf(tokens.get(0)));
                }
            }

        }
    }
}
