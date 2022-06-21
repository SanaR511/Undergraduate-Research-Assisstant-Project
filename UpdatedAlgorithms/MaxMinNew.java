import java.util.Arrays;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Integer;

public class MaxMinNew {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        ArrayList<Integer> cloudlets = new ArrayList<Integer>(Arrays.asList(18000,42000,21000, 32000, 20000, 15000, 10000,30000,40000,50000));
        ArrayList<Integer> mipps = new ArrayList<Integer>(Arrays.asList(300,600,400));

        int readyTime[] = {300,600,400};
        int [][] matrix = new int[cloudlets.size()][mipps.size()];
        int tempMatrix[][] = matrix;
        int row = matrix.length;
        int column = matrix[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                matrix[i][j] = cloudlets.get(i)/mipps.get(j);
                tempMatrix[i][j] = matrix[i][j];
            }
        }



//adding all the values into the matrix
        for(int i = 0; i < column; i++){
            for(int j = 0; j < row; j++){
                matrix[j][i] = matrix[j][i] + readyTime[i];
            }
        }



        //printing out matrix with added values first time

        System.out.println(Arrays.deepToString(matrix));



        int machine = 0;
        int task = -1;
        HashMap <Integer, Integer> map = new HashMap<Integer, Integer>();

        while(map.size() != matrix.length){
            //  int readyTime[] = {300,600,400};

            int resultMin[] = new int[matrix.length];
            HashMap<Integer, Integer> mini = new HashMap<Integer, Integer>();
            //Hashmap to store the machines already used in each iteration
            HashMap<Integer, Integer> machines = new HashMap<Integer, Integer>();

            int sufferage = 0;
            int sufferageList[] = new int[matrix.length];
            int index = 0;


            //calculate minimum run time for each task and store in HashMap with corresponding VM

            for (int i = 0; i < matrix.length; i++) {
                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;
                for (int j = 0; j < matrix[i].length; j++) {
                    //if map already has task skip this row by breaking out of for loop
                    if (map.containsKey(i)) {
                        sufferageList[i] = Integer.MIN_VALUE;
                        break;
                    } else {
                        //Storing minimum and second minimum
                        if (matrix[i][j] < min1) {
                            if(min1 != Integer.MAX_VALUE) {
                                min2 = min1;
                                min1 = matrix[i][j];
                                task = i;
                                machine = j;
                                mini.put(task, machine);
                                resultMin[i] = min1;
                            }else{
                                min1 = matrix[i][j];
                                task = i;
                                machine = j;
                                mini.put(task, machine);
                                resultMin[i] = min1;
                            }

                            //System.out.println(min1);
                        } else if (min1 != Integer.MAX_VALUE && matrix[i][j] != min1 && matrix[i][j] < min2) {
                            min2 = matrix[i][j];
                        }

                        if (min2 != Integer.MAX_VALUE) sufferage = min1 - min2;
                        if (sufferageList[i] != Integer.MIN_VALUE && min2 != Integer.MAX_VALUE)
                            sufferageList[i] = sufferage;

                    }
                }
            }




            //finding maximum of array of sufferage times
            int maxValue = Integer.MIN_VALUE;

            for (int m = 0; m < sufferageList.length; m++) {
                if (sufferageList[m] > maxValue) {
                    maxValue = sufferageList[m];
                    index = m;
                }
            }
            //putting machine and task with minimum runtime into hashmap
            task = index;
            int newMach = mini.get(task);
            machines.put(task, newMach);

            map.putAll(machines);




            for(int k = 0; k < column; k++){
                for(int p = 0; p < row; p++){
                    if(map.containsKey(k)){
                        break;
                    }else{
                        tempMatrix[p][k] = tempMatrix[p][k] + readyTime[k];
                    }

                }
            }

            matrix = tempMatrix;

        }
        for (int i : map.keySet()) {
            System.out.println("Task: " + i + " Machine: " + map.get(i));
        }


    }
}
 
