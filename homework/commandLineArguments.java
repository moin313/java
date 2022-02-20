package homework;

public class main {
    public static void main(String[] args) {
        String[] s = args[0].split(" ");
        String name = s[0];
        int num = Integer.parseInt(s[1]);
        for (int  i = 0; i < num; i++){
            System.out.println(name);
        }
    }
}
