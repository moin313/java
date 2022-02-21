package Day6;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please inter the number");
        int num = input.nextInt();
        iterativeFib(num);
        System.out.println("\nRECURSIVE");
        recursiveFib( 0 , 1 , num);
    }
    static void iterativeFib(int num){
        int first = 0, second = 1, temp = 0;
        System.out.print(first);
        while(second <= num){
            System.out.print(" "+second);
            temp = first + second;
            first = second;
            second = temp;
        }
    }

    static int recursiveFib(int a, int b, int num){
        if(a ==0)
            System.out.print(a+" ");
        if(b > num)
            return b;
        System.out.print(b+" ");
        recursiveFib(b, a + b, num);
        return b;
    }
}
