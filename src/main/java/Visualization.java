import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Visualization extends JPanel implements Serializable {

    Solution solution;

    Visualization(Solution solution){
        this.solution = solution;
        JPanel panel = new JPanel();
        this.setLayout(null);
        this.setBackground(Color.CYAN);
        this.add(panel);
    }

    public int getMaxX(){
        int i = 0;
        double acc = 0;
        while(i<solution.size){
            if(solution.pointList.get(i).getX() > acc){
                acc = solution.pointList.get(i).getX();
            }
            i++;
        }
        return (int) acc;
    }

    public int getMaxY(){
        int i = 0;
        double acc = 0;
        while(i<solution.size){
            if(solution.pointList.get(i).getY() > acc){
                acc = solution.pointList.get(i).getY();
            }
            i++;
        }
        return (int) acc;
    }

    public void printOrder(){
        System.out.println(solution.order);
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.BLACK);
        int i = 0;
        int maxX = getMaxX();
        int maxY = getMaxY();
        while(i<solution.pointList.size()){
            if(i == solution.order.get(0)-1 ){
                g.setColor(Color.BLUE);
            }
            else{
                g.setColor(Color.BLACK);
            }
            g.fillOval((int) (solution.pointList.get(i).getX()*800/maxX)-5, (int) (solution.pointList.get(i).getY()*800/maxY)-5,10,10);
            i++;
        }
        i = 0;
        while(i<solution.size){
            if(i == solution.size-1) {
                g.drawLine((int) ((solution.pointList.get((solution.order.get(i)) - 1).getX()) * 800 / maxX),
                        (int) ((solution.pointList.get((solution.order.get(i)) - 1).getY()) * 800 / maxY), (int) ((solution.pointList.get((solution.order.get(0)) - 1).getX()) * 800 / maxX),
                        (int) ((solution.pointList.get((solution.order.get(0)) - 1).getY()) * 800 / maxY));
                i++;
            } else{
                g.drawLine((int) ((solution.pointList.get((solution.order.get(i)) - 1).getX()) * 800 / maxX),
                        (int) ((solution.pointList.get((solution.order.get(i)) - 1).getY()) * 800 / maxY), (int) ((solution.pointList.get((solution.order.get(i + 1)) - 1).getX()) * 800 / maxX),
                        (int) ((solution.pointList.get((solution.order.get(i + 1)) - 1).getY()) * 800 / maxY));
                i++;
            }
        }
        //System.out.println(" -------------------- ");
    }


}
