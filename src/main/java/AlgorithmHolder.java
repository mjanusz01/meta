import javax.swing.plaf.synth.SynthToolBarUI;
import java.io.*;
import java.util.ArrayList;

public class AlgorithmHolder {

    Solution solution;
    Solution candidate;
    Solution holder;
    int distance;

    public Solution KRandomAlgorithm(Instance instance, int k) {
        int i = 0;
        solution = instance.getSolution();
        solution.randomOrder();
        Solution holder = null;
        while (i < k) {
            solution.randomOrder();
            if (i == 0) {
                distance = solution.totalDistance();
                holder = solution.copy();
            } else if(solution.totalDistance() < distance) {
                distance = solution.totalDistance();
                holder = solution.copy();
            }
            i++;
            //System.out.println("Aktualne: " + distance  + ", Wylosowane: " + solution.totalDistance());
        }

        //solution.printPoints();
        //solution.printMatrix();
        System.out.println(holder.order);
        //System.out.println(solution.order);
        Solution finalSolution = holder.copy();
        finalSolution.frameTitle = "k-Random Solution";
        return finalSolution;
    }

    public long[][] KRandomAlgorithmTest(Instance instance, long maxTime, long timeCount) {

        solution = instance.getSolution();
        solution.randomOrder();
        long[][] results = new long[2][(int) (maxTime/timeCount)];
        int i = 0;
        int k = 0;
        long sum = 0;
        long start = System.currentTimeMillis();
        int j = 0;
        while (true) {
            solution.randomOrder();
            k = solution.totalDistance();
            if (i == 0) {
                distance = solution.totalDistance();
            } else if(k < distance) {
                distance = k;
            }
            //System.out.println("Aktualne: " + distance  + ", Wylosowane: " + solution.totalDistance());
            long end = System.currentTimeMillis();
            if(end-start>=timeCount){
                sum = sum + (end-start);
                results[0][i] = distance;
                results[1][i] = sum;
                start = System.currentTimeMillis();
                i++;
            }
            j++;
            if(sum>=maxTime){
                System.out.println("Iteracje: "+j);
                return results;
            }
        }
    }



    public Solution TwoOptAlgorithm(Instance instance) throws IOException {

        holder = instance.getSolution();
        int currBestDistance = holder.totalDistance();
        int newDistance = currBestDistance;

        boolean isImproved = true;

        while (isImproved) {
            isImproved = false;

            int i = 1;
            int j;
            while(i<=holder.size && !isImproved){
                j = i + 1;
                while(j<=holder.size && !isImproved){

                    candidate = holder.copy();
                    candidate = invert(candidate,i,j);
                    newDistance = candidate.totalDistance();
                    if(newDistance<currBestDistance){
                        holder = candidate.copy();
                        currBestDistance = newDistance;
                        isImproved = true;
                    }

                    //System.out.println("i = " + i + ", j = " + j);
                    j++;
                }
                i++;
            }
        }

        holder.frameTitle = "2-OPT Solution";

        return holder;
    }

    public Solution AccelTwoOptAlgorithm(Instance instance) throws IOException {

        holder = instance.getSolution();
        int currBestDistance = holder.totalDistance();
        int newDistance = currBestDistance;

        int[] swapDistances = new int[4]; // elementy o indeksach 0 i 1 będziemy odejmować, 2 i 3 dodawać;
        boolean isImproved = true;

        while (isImproved) {
            isImproved = false;

            int i = 1;
            int j;
            while(i<=holder.size && !isImproved){
                j = i + 1;
                while(j<=holder.size && !isImproved){

                    candidate = holder.copy();
                    candidate = invert(candidate,i,j);

                    if (instance.getType().equals(Instance.type_enum.TSP) && !(i == 1 && j == holder.size)) {

                        if (i > 1) {
                            // odleglosc miedzy i-1-wszym oraz i-tym do odjecia
                            swapDistances[0] = instance.edge_weight_matrix[holder.order.get(i - 2) - 1][holder.order.get(i - 1) - 1];
                            // odleglosc miedzy i-1-wszym oraz j-tym do dodania
                            swapDistances[2] = instance.edge_weight_matrix[holder.order.get(i - 2) - 1][holder.order.get(j - 1) - 1];
                        } else {
                            swapDistances[0] = instance.edge_weight_matrix[holder.order.get(holder.size - 1) - 1][holder.order.get(i - 1) - 1];
                            swapDistances[2] = instance.edge_weight_matrix[holder.order.get(holder.size - 1) - 1][holder.order.get(j - 1) - 1];
                        }

                        if (j < holder.size) {
                            // odleglosc miedzy j-tym raz j+1-wszym do odjecia
                            swapDistances[1] = instance.edge_weight_matrix[holder.order.get(j - 1) - 1][holder.order.get(j) - 1];
                            // odleglosc miedzy i-tym raz j+1-wszym do dodania
                            swapDistances[3] = instance.edge_weight_matrix[holder.order.get(i - 1) - 1][holder.order.get(j) - 1];
                        } else {
                            swapDistances[1] = instance.edge_weight_matrix[holder.order.get(j - 1) - 1][holder.order.get(0) - 1];
                            swapDistances[3] = instance.edge_weight_matrix[holder.order.get(i - 1) - 1][holder.order.get(0) - 1];
                        }

                        newDistance = currBestDistance - swapDistances[0] - swapDistances[1] + swapDistances[2] + swapDistances[3];

                    } else if (instance.getType().equals(Instance.type_enum.ATSP)){
                        newDistance = candidate.totalDistance();
                    }

                    if(newDistance<currBestDistance){
                        holder = candidate.copy();
                        currBestDistance = newDistance;
                        isImproved = true;
                    }

                    //System.out.println("i = " + i + ", j = " + j);
                    j++;
                }
                i++;
            }
        }

        holder.frameTitle = "Accelerated 2-OPT Solution";

        return holder;
    }

    public Solution invert(Solution solution, int i, int j) {
        //System.out.println("Jestem in");
        if(i>j){
            int pomi = i;
            int pomj = j;
            j = pomi;
            i = pomj;
        }
        int[] pom =  new int[j-i+1];
        int it = 0;
        while(it<=j-i){
            pom[it] = solution.order.get(it+i-1);
            it++;
        }
        it = 0;
        while(it<=j-i){
            solution.order.set(it+i-1,pom[(j-i)-it]);
            it++;
        }
        return solution;
    }

    public Solution NearestNeighbor(Instance instance){
        holder = NearestNeighborWithStartIndex(instance, 1);
        holder.frameTitle = "Nearest Neighbor Solution";
        return holder;
    }

    public Solution ExNearestNeighbor(Instance instance){

        int currLowestDistance = Integer.MAX_VALUE;
        int distance;
        for(int i = 1; i <= instance.getDimension(); i++) {
            candidate = NearestNeighborWithStartIndex(instance, i);

            distance = candidate.totalDistance();
            if (distance < currLowestDistance) {
                currLowestDistance = distance;
                holder = candidate;
            }
        }
        holder.frameTitle = "Extended Nearest Neighbor Solution";

        return holder;
    }

    public long[][] ExNearestNeighborTest(Instance instance, long maxTime, long timeCount){

        holder = new Solution();
        holder.setFields(instance);
        int currLowestDistance = Integer.MAX_VALUE;
        int distance;
        long[][] results = new long[2][(int) (maxTime/timeCount)];
        int k = 0;
        long sum = 0;
        long start = System.currentTimeMillis();
        for(int i = 1; i <= instance.getDimension(); i++) {
            candidate = holder.copy();

            ArrayList<Integer> notVisited = candidate.order;
            candidate.order = new ArrayList<>();

            int curr = i;
            candidate.order.add(curr);
            notVisited.remove(Integer.valueOf(curr));
            int toCurrNearest;
            int currNearestIndex = 0;
            int dist;

            while (notVisited.size() > 0) {
                toCurrNearest = Integer.MAX_VALUE;

                for (int l = 0; l < notVisited.size(); l++) {
                    dist = instance.edge_weight_matrix[curr-1][notVisited.get(l)-1];
                    if(dist < toCurrNearest) {
                        toCurrNearest = dist;
                        currNearestIndex = notVisited.get(l);
                    }

                    long end = System.currentTimeMillis();
                    if(end-start>=timeCount){
                        sum = sum + (end-start);
                        results[0][k] = currLowestDistance;
                        results[1][k] = sum;
                        start = System.currentTimeMillis();
                        k++;
                    }

                    if(sum>=maxTime){
                        System.out.println("Iteracje: "+ i);
                        return results;
                    }
                }

                candidate.order.add(currNearestIndex);
                notVisited.remove(Integer.valueOf(currNearestIndex));
                curr = currNearestIndex;

            }
            distance = candidate.totalDistance();
            if (distance < currLowestDistance) {
                currLowestDistance = distance;
                holder = candidate;
            }
        }

        System.out.println("Iteracje: " + instance.getDimension());
        return results;
    }

    private Solution NearestNeighborWithStartIndex(Instance instance, int start){
        solution = new Solution();
        solution.setFields(instance);
        ArrayList<Integer> notVisited = solution.order;
        solution.order = new ArrayList<>();

        int curr = start;
        solution.order.add(curr);
        notVisited.remove(Integer.valueOf(curr));
        int toCurrNearest;
        int currNearestIndex = 0;
        int distance;

        while (notVisited.size() > 0) {
            toCurrNearest = Integer.MAX_VALUE;

            for (int i = 0; i < notVisited.size(); i++) {
                distance = instance.edge_weight_matrix[curr-1][notVisited.get(i)-1];
                if(distance < toCurrNearest) {
                    toCurrNearest = distance;
                    currNearestIndex = notVisited.get(i);
                }
            }

            solution.order.add(currNearestIndex);
            notVisited.remove(Integer.valueOf(currNearestIndex));
            curr = currNearestIndex;

        }

        return solution;
    }

    public long[][] TwoOptAlgorithmTest(Instance instance, long maxTime, long timeCount) {

        holder = instance.getSolution();
        int currLowestDistance = holder.totalDistance();
        int newDistance = currLowestDistance;

        boolean isImproved = true;

        long[][] results = new long[2][(int) (maxTime/timeCount)];
        int k = 0;
        long sum = 0;
        long start = System.currentTimeMillis();
        int n = 0;
        while (isImproved) {
            isImproved = false;

            int i = 1;
            int j;
            while(i<=holder.size && !isImproved){
                j = i + 1;
                while(j<=holder.size && !isImproved){

                    candidate = holder.copy();
                    candidate = invert(candidate,i,j);
                    newDistance = candidate.totalDistance();
                    if(newDistance<currLowestDistance){
                        holder = candidate.copy();
                        currLowestDistance = newDistance;
                        isImproved = true;
                    }

                    long end = System.currentTimeMillis();
                    if(end-start>=timeCount){
                        sum = sum + (end-start);
                        results[0][k] = currLowestDistance;
                        results[1][k] = sum;
                        start = System.currentTimeMillis();
                        k++;
                    }
                    n++;

                    if(sum>=maxTime){
                        System.out.println("Iteracje: "+ n);
                        return results;
                    }
                    //System.out.println("i = " + i + ", j = " + j);
                    j++;
                }
                i++;
            }
        }


        return results;
    }

}


