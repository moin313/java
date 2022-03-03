package practice_quetions;

import java.util.ArrayList;
import java.util.List;

class Queue<Type>{
    private int tail;
    private List<Type> list;
    Queue() {
        tail = -1;
        list = new ArrayList<>();
    }

    public void insert(Type dataToadd){
        list.add(dataToadd);
        tail++;
    }

    public void delete(){
        if(!list.isEmpty()) {
            list.remove(0);
        }
        else{
            System.out.println("No item to remove");
        }
    }

    @Override
    public String toString() {
        return "Item Left in the queue=" + list;
    }
}

public class QueueDriver {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();

        queue.insert(2);
        queue.insert(1);
        queue.insert(5);
        System.out.println(queue);
        queue.delete();
        System.out.println(queue);
        queue.delete();
        System.out.println(queue);

        //QUEUE USING STRING

        Queue<String> queue1 = new Queue<String>();

        queue1.insert("a");
        queue1.insert("b");
        queue1.insert("c");
        System.out.println(queue1);
        queue1.delete();
        System.out.println(queue1);
        queue1.delete();
        System.out.println(queue1);
    }
}