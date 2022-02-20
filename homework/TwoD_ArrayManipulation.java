package homework;

import java.util.Scanner;

public class TwoD {
    public static void main(String[] args) {
        String [][]grads = new String[4][6];
        Scanner inputObj = new Scanner(System.in);
        grads[0] = new String[]{"Name", "course1", "course2", "course3", "course4", "course5"};
        grads[1][0] = new String("Student1");
        grads[2][0] = new String("Student2");
        grads[3][0] = new String("Student3");
        for(int i=1; i < 4; ++i){
            for(int j=1; j < 6; j++){
                grads[i][j] = inputObj.next();
            }
        }

        int flag=1, first=4, second=4;
        String change = "D";
        while(true){
            System.out.println("If you want to make changes then press 1 else any NUMBER");
            flag = inputObj.nextInt();
            if(flag != 1)
                break;
            System.out.println("PLease enter course number and student number and then changes");
            first = inputObj.nextInt();
            second = inputObj.nextInt();
            change = inputObj.next();
            if(first < 6 && first > -1 && second < 4 && second > -1 && change.charAt(0) >= 'A' && change.charAt(0) < 'D' ){
                grads[second][first] = change;
            }

            System.out.println("After change");
            for(int i=0; i < 4; ++i){
                for(int j=0; j < 6; j++){
                    System.out.print(grads[i][j]+" ");
                }
                System.out.println();
            }
        }
    }
}
