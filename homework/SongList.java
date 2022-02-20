package Day5;

import java.util.*;

public class SongList {
    public static void main(String[] args) {
        Map<Integer, Song> map = new HashMap<>();
        Scanner input = new Scanner(System.in);
        String title;
        int repeat;
        while(true){
            System.out.println("Do you want to add SONG press 1");
            if(input.nextInt() != 1)
                break;
            System.out.println("Enter song title and repeat");
            title = input.next();
            repeat = input.nextInt();
            List<String> list = new ArrayList<>();
            while(true){
                System.out.println("Do you want to add TYPE press 1");
                if(input.nextInt() != 1)
                    break;
                list.add(input.next());
            }
            Song obj1 = new Song(title, list);
            map.put(repeat , obj1);
            System.out.println("___________________________________________________________________________");
        }

        System.out.println("Please enter the last range");
        int range = input.nextInt();
        System.out.println(map.size());
        if (range <= map.size()) {

            List<Integer> list1 = new ArrayList<>(map.keySet());
            for (int i=1; range > 0; i++, range--) {
                System.out.println(map.get(list1.get(list1.size() - i)).title+"   "+map.get(list1.get(list1.size() - i)).list);
            }
        }else{
            System.out.println("You entered the high capacity");
        }

    }

}

//Java class to hold title and list of type of song.
class Song{
    String title;
    List<String> list;

    Song(String _title, List<String> _list){
        title = _title;
        list = _list;
    }
}
