
import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Instance implements Serializable {

    private String name;

    public enum type_enum {
        TSP, ATSP
    }

    private type_enum type;

    private int dimension;

    public enum edge_weight_type_enum {
        EXPLICIT, EUC_2D
    }
    private edge_weight_type_enum edge_weight_type;

    public enum edge_weight_format_enum {
        FULL_MATRIX, LOWER_DIAG_ROW
    }
    private edge_weight_format_enum edge_weight_format;

    public ArrayList<Point2D.Double> node_coord_list;
    public int[][] edge_weight_matrix;

    public String getName() {
        return name;
    }

    public type_enum getType() {
        return type;
    }

    public int getDimension() {
        return dimension;
    }

    public edge_weight_type_enum getEdge_weight_type() {
        return edge_weight_type;
    }

    public edge_weight_format_enum getEdge_weight_format() {
        return edge_weight_format;
    }

    public void setType(type_enum type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setEdge_weight_type(edge_weight_type_enum edge_weight_type) {
        this.edge_weight_type = edge_weight_type;
    }

    public void setEdge_weight_format(edge_weight_format_enum edge_weight_format) {
        this.edge_weight_format = edge_weight_format;
    }

    public void printList(){
        int i = 0;
        while(i<node_coord_list.size()){
            System.out.println(node_coord_list.get(i));
            i++;
        }
    }


    public Instance() {

    }

    public void generateRandomInstanceEUC_2D(int size, int upper){
        this.setType(type_enum.TSP);
        this.setEdge_weight_type(edge_weight_type_enum.EUC_2D);
        this.setDimension(size);
        Random rand = new Random();
        int x;
        int y;
        ArrayList<Point2D.Double> pom_list = new ArrayList<>();
        int i = 0;
        while(i<size){
            x = rand.nextInt(upper);
            y = rand.nextInt(upper);
            Point2D.Double point = new Point2D.Double(x,y);
            pom_list.add(point);
            i++;
        }
        this.node_coord_list = pom_list;

        Parser parser = new Parser();
        parser.EUC2D_toMatrix(this);
    }

    public Solution getSolution(){
        //System.out.println(dimension);
        Solution solution = new Solution();
        solution.setFields(this);
        return solution;
    }

    public ArrayList<Point2D.Double> getNode_coord_list(){
        return node_coord_list;
    }

    public void visualize(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500,500);
    }

    public int getDistance(int n, int m){
        return (int) (Math.sqrt(((node_coord_list.get(n).getX()-node_coord_list.get(m).getX())*(node_coord_list.get(n).getX()-node_coord_list.get(m).getX()))
                +((node_coord_list.get(n).getY()-node_coord_list.get(m).getY())*(node_coord_list.get(n).getY()-node_coord_list.get(m).getY())))+0.5);
    }

    public void printMatrix(int limit){
        int i = 0;
        int j = 0;
        while(i<limit){
            j = 0;
            while (j < limit) {
                System.out.print(this.edge_weight_matrix[i][j] + " ");
                j++;
            }
            System.out.println(" ");
            i++;
        }
    }

}