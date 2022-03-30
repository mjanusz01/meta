
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution implements Serializable {

    public Visualization v;
    public String frameTitle = "";
    public Instance instance;
    public int size;
    public ArrayList<Point2D.Double> pointList; // na wypadek euklidesowego i wizualizacji
    public ArrayList<Integer> order;
    public int[][] edge_weight_matrix;

    public void randomOrder(){
        Collections.shuffle(order);
    }

    public Solution randomOrder2(){
        Collections.shuffle(order);
        return this;
    }

    public void setFields(Instance instance){
        order = new ArrayList<>();
        this.instance = instance;
        this.edge_weight_matrix = instance.edge_weight_matrix;
        this.size = instance.getDimension();
        this.pointList = instance.getNode_coord_list();
        int i = 0;
        while(i<size){
            order.add(i+1);
            i++;
        }
    }

    public Solution() {}

    public Solution copy() {
        Solution s = new Solution();
        s.instance = this.instance;
        s.frameTitle = this.frameTitle;
        s.size = this.size;
        s.pointList = this.pointList;
        s.order = new ArrayList<>();

        for(int i = 0; i < this.order.size(); i++) {
            s.order.add(this.order.get(i));
        }

        s.edge_weight_matrix = this.edge_weight_matrix;

        return s;
    }

    public void printOrder(){
        int i = 0;
        while(i<size){
            System.out.print(order.get(i) + " ");
            i++;
        }
        System.out.println(" ");
    }

    public void printPoints(){
        int i = 0;
        while(i<size){
            System.out.print(pointList.get(i) + " ");
            i++;
        }
        System.out.println(" ");
    }

    public int totalDistance2(){
        int sum = 0;
        int j1, j2;
        for (int i = 0; i < size; i++) {
            j1 = order.get(i)-1;
            if(i < size - 1) j2 = order.get(i+1)-1;
            else j2 = order.get(0) - 1;
            sum += edge_weight_matrix[j1][j2];
        }
        return sum;
    }

    public int totalDistance(){
        int sum = 0;
        int i = 0;
        while(i<size){
            if(i == size - 1){
                sum = sum + edge_weight_matrix[order.get(i)-1][order.get(0)-1];
            }
            else {
                sum = sum + edge_weight_matrix[order.get(i) - 1][order.get(i + 1) - 1];
            }

            i++;
        }
        return sum;
    }




    public void printMatrix(){
        int i = 0;
        int j = 0;
        while(i<size){
            j=0;
            while(j<size){
                System.out.print(edge_weight_matrix[i][j] + " ");
                j++;
            }
            i++;
            System.out.println(" ");
        }
    }

    public void visualize(){

        JFrame frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(850,850);
        frame.setVisible(true);
        v = new Visualization(this);
        frame.add(v);

    }

}
