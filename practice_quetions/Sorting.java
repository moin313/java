package practice_quetions;

public class Sorting {
    public static void main(String[] args) {
        int []num = {82, 8,7,98 ,2, 10, 99,3};
        int temp=0, j=0;

        for (int i = 0; i < num.length; i++){
            temp = num[i];
            j = i - 1;
            while(j >= 0 && num[j] > temp){
                num[j+1] = num[j];
                j--;
            }
            num[j+1] = temp;
        }
        for (int element: num) {
            System.out.print(element+" ");
        }
    }
}