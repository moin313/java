package practice_quetions;

public class SumOfMatrix {
    public static void main(String[] args) throws InterruptedException {
        int [][]arr = {{1, 2, 3},{3, 4, 5},{7,5,7}};
        int [][]arr1 = {{2, 7, 6},{5, 4, 3},{3,5,2}};
        CustomThread obj = new CustomThread(arr);
        CustomThread obj1 = new CustomThread(arr1);
        obj.start();
        obj1.start();
        obj.join();
        obj1.join();
        CustomThread.getSum();
    }
}


class CustomThread extends Thread{
    private static long sum = 0;
    private int arr[][];
    CustomThread(int [][]_arr){
        arr = _arr;
    }

    public void run(){
        long temp=0;
        for (int[] first : arr) {
            for (int second : first) {
                temp+=second;
            }
        }
        sum += temp;
    }

    public static void getSum(){
        System.out.println(sum);
    }
}
