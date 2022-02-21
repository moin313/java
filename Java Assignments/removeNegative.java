package Day6;

import java.util.ArrayList;
import java.util.List;

public class removeNegative {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(4); list.add(0); list.add(5); list.add(-34);
        System.out.println("Before : "+list);
        check(list);
        System.out.println("After : "+list);
    }
    public static List<Integer> check(List<Integer> list){

        Operation temp = (num)-> {
            return (num >= 0) ? true : false;
        };
        for (int i=0; list.size() > i; i++){
            if(!temp.check(list.get(i))){
                list.remove(i);
                i--;
            }
        }
        return list;
    }
}
