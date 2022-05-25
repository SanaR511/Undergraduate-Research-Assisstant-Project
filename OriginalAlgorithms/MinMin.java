import java.util.Arrays;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Integer;

public class MinMin {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Array List for cloudlets and VMmachines
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
            int resultTask [] = new int[matrix.length]; //stores task value with minimum time
            int resultMachine[] = new int[matrix.length]; //stores machine value with minimum time
            int resultMin[] = new int[matrix.length]; //stores minimum time

            //calculate minimum run time for each task and store in HashMap with corresponding VM

            for(int i =0;i<matrix.length;i++) {
                int min = Integer.MAX_VALUE;
                for(int j= 0;j<matrix[i].length;j++) {
                    if(map.containsKey(i)){
                        resultMin[i] = Integer.MAX_VALUE;
                        break;
                    }
                    //finding minimum of each row
                    if(matrix[i][j]< min){
                        min = matrix[i][j];
                        task = i;
                        machine = j;


                    }

                    //if the machine has not already been mapped then set the values
                    if(resultMin[i]!= Integer.MAX_VALUE) resultTask[i] = task;
                    if(resultMin[i]!= Integer.MAX_VALUE) resultMachine[i] = machine;
                    if(resultMin[i]!= Integer.MAX_VALUE) resultMin[i] = min;

                }

            }




            int minValue = Integer.MAX_VALUE;
            int index = 0;
            int minMachine = 0;
            int minTask = 0;
            //finding task with earliest completion time
            for(int i = 0; i < resultMin.length; i++){
                    if(resultMin[i]< minValue) {
                        minValue = resultMin[i];
                        index = i;
                    }
                }


            minTask = resultTask[index];

            minMachine = resultMachine[index];

            readyTime[minMachine] = minValue;




            //Store in corresponding tasks and machines
            map.put(minTask, minMachine);


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
 