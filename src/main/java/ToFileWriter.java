import java.io.*;

public class ToFileWriter {
    public void saveToFile(Instance instance) {

        try {

            String path = "data/" + instance.getName();
            if (instance.getType() == Instance.type_enum.TSP) path = path + ".tsp";
            else if (instance.getType() == Instance.type_enum.ATSP) path = path + ".atsp";
            System.out.println(path);

            File fout = new File(path);
            FileOutputStream fos = new FileOutputStream(fout);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);

            String temp = "NAME: " + instance.getName();
            bw.write(temp);
            bw.newLine();

            temp = "TYPE: " + instance.getType();
            bw.write(temp);
            bw.newLine();

            temp = "DIMENSION: " + instance.getDimension();
            bw.write(temp);
            bw.newLine();

            temp = "EDGE_WEIGHT_TYPE: " + instance.getEdge_weight_type();
            bw.write(temp);
            bw.newLine();

            if (instance.getEdge_weight_format() != null) {
                temp = "EDGE_WEIGHT_FORMAT: " + instance.getEdge_weight_format();
                bw.write(temp);
                bw.newLine();
            }

            if (instance.getEdge_weight_type() == Instance.edge_weight_type_enum.EUC_2D) {
                bw.write("NODE_COORD_SECTION");
                bw.newLine();

                double x;
                double y;

                for (int i = 1; i <= instance.getDimension(); i++) {
                    x = instance.node_coord_list.get(i-1).getX();
                    y = instance.node_coord_list.get(i-1).getY();
                    temp = i + " " + x + " " + y;
                    bw.write(temp);
                    bw.newLine();
                }
            } else if (instance.getEdge_weight_type() == Instance.edge_weight_type_enum.EXPLICIT &&
                    instance.getEdge_weight_format() == Instance.edge_weight_format_enum.FULL_MATRIX) {
                bw.write("EDGE_WEIGHT_SECTION");
                bw.newLine();

                for (int i = 0; i < instance.getDimension(); i++) {
                    for (int j = 0; j < instance.getDimension(); j++) {
                        bw.write(instance.edge_weight_matrix[i][j] + " ");
                    }
                    bw.newLine();
                }
            } else if (instance.getEdge_weight_type() == Instance.edge_weight_type_enum.EXPLICIT &&
                    instance.getEdge_weight_format() == Instance.edge_weight_format_enum.LOWER_DIAG_ROW) {
                bw.write("EDGE_WEIGHT_SECTION");
                bw.newLine();

                for (int i = 0; i < instance.getDimension(); i++) {
                    for (int j = 0; j <= i; j++) {
                        bw.write(instance.edge_weight_matrix[i][j] + " ");
                    }
                    bw.newLine();
                }
            }

            bw.write("EOF");
            bw.newLine();

            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveToFile(Solution solution) {

        try {
            String path = "data/" + solution.instance.getName();
            if (solution.instance.getType() == Instance.type_enum.TSP) path = path + ".tsp";
            else if (solution.instance.getType() == Instance.type_enum.ATSP) path = path + ".atsp";

            File fout = new File(path);
            if (!(fout.exists())) {
                this.saveToFile(solution.instance);
            }


            int n = 1;
            do {
                path = "data/" + solution.instance.getName() + "_" + n + ".tour";
                fout = new File(path);
                n++;
            } while (fout.exists());

            FileOutputStream fos = new FileOutputStream(fout);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);

            String temp = "NAME: " + solution.instance.getName();
            bw.write(temp);
            bw.newLine();

            temp = "TYPE: TOUR";
            bw.write(temp);
            bw.newLine();

            temp = "DIMENSION: " + solution.instance.getDimension();
            bw.write(temp);
            bw.newLine();

            temp = "TOUR_SECTION ";
            bw.write(temp);
            bw.newLine();

            for (int i = 0; i < solution.instance.getDimension(); i++) {
                bw.write(solution.order.get(i).toString());
                bw.newLine();
            }

            bw.write("-1");
            bw.newLine();

            bw.write("EOF");
            bw.newLine();

            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
