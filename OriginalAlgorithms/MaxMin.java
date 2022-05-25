import java.util.Arrays;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Integer;

public class MaxMin {
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
            int resultTask [] = new int[matrix.length];
            int resultMachine[] = new int[matrix.length];
            int resultMin[] = new int[matrix.length];

            //calculate minimum run time for each task and store in HashMap with corresponding VM

            for(int i =0;i<matrix.length;i++) {
                int min = Integer.MAX_VALUE;
                for(int j= 0;j<matrix[i].length;j++) {
                    if(map.containsKey(i)){
                        resultMin[i] = Integer.MIN_VALUE;
                        break;
                    }
                    if (matrix[i][j]< min){
                        min = matrix[i][j];
                        task = i;
                        machine = j;


                    }
                    if(resultMin[i]!= Integer.MIN_VALUE) resultTask[i] = task;
                    if(resultMin[i]!= Integer.MIN_VALUE) resultMachine[i] = machine;
                    resultMin[i] = min;

                }

            }



            //finding maximum of array of minimum times
            int minValue = Integer.MIN_VALUE;
            int index = 0;
            int maxMachine = 0;
            int minTask = 0;
            for(int i = 0; i < resultMin.length; i++){
                if(resultMin[i] > minValue) {
                    minValue = resultMin[i];
                    index = i;
                }
            }

            //
            minTask = resultTask[index];
            maxMachine = resultMachine[index];
            //Changing the machine array to new machine runtime value
            readyTime[maxMachine] = minValue;



            map.put(minTask, maxMachine);




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
 