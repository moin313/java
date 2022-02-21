package Day6;

import java.util.*;

public class ReturnTopK {
    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        System.out.println("Please enter the length");
        int length = input.nextInt();
        List<Integer> list = new ArrayList<>();
        System.out.println("Please enter the list data");
        while(length > 0){
            list.add(input.nextInt());
            length--;
        }
        System.out.println("Please enter the range");
        int k = input.nextInt();

        System.out.println(getTopKMax(list, k));
    }
    public static List<Integer>  getTopKMax(List<Integer> list, int k ){
        SortedSet<Integer> set = new TreeSet<>(list);
        List<Integer> result = new ArrayList<>();
        while(k > 0) {
            result.add(set.last());
            set.remove(set.last());
            k--;
        }
        return result;
    }
}
