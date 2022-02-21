package Day6;

import java.util.Scanner;

public class isSymmetric {
    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        System.out.println("Please enter the matrix size");
        int length = input.nextInt();
        int[][] arr = new int[length][length];

        System.out.println("Please enter the matrix values");
        for(int row=0; row < 3; row++){
            for(int col=0; col < 3; col++){
                arr[row][col] = input.nextInt();
            }
        }
        System.out.println(checkSymmetry(arr, length));
    }
    public static boolean checkSymmetry(int[][] arr, int length){
        boolean flag = false;
        for(int row=0; row < length; row++){
            for(int col=0; col < length; col++){
                if(arr[row][col] == arr[col][row]){
                    flag = true;
                }else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
