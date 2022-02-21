package Day6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindSum {
    public static void main(String[] args) {
        int sum = 12, num = 0;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        Collections.sort(list);
        for(int i=list.size()-1 ; i >= 0; i-- ) {
            if (sum - list.get(i) > 0) {
                num = BinarySearch.binarySearch(list, (sum - list.get(i)));
                if (num > -1) {
                    System.out.println("The two numbers are : " + num + " " + (list.get(i)));
                    return;
                }
            }
        }
        System.out.println("Not found");
    }
} class BinarySearch {
    public static int binarySearch(List<Integer> arr, int number){
        int low = 0;
        int high = arr.size() - 1;
        while(high>=low){
            int mid = (low+high)/2;
            if(number<arr.get(mid))
                high=mid-1;
            else if(number == arr.get(mid))
                return arr.get(mid);
            else low = mid+1;
        }
        return -1;
    }
}
