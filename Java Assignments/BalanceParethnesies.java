package Day6;

import java.util.Stack;

public class BalanceParethnesies {
    public static void main(String[] args) {
        String expression = "{}({())}";
        System.out.println(isBalanced(expression));
    }
    public static boolean isBalanced(String exp){
        boolean flag = false;
        int i = exp.length()-1;
        Stack<Character> stack = new Stack<>();
        while(i >= 0){
            if(stack.isEmpty() && (exp.charAt(i) == '(' || exp.charAt(i) == '{' || exp.charAt(i) == '[')) {
                return false;
            }
            else {
                if (exp.charAt(i) == '(' && stack.peek() == ')') {
                    stack.pop();
                } else if (exp.charAt(i) == '{' && stack.peek() == '}') {
                    stack.pop();
                } else if (exp.charAt(i) == '[' && stack.peek() == ']') {
                    stack.pop();
                }
                else{
                    stack.push(exp.charAt(i));
                }
            }
            i--;
        }
        if(stack.isEmpty())
            flag = true;

        else
            flag = false;

        return flag;
    }
}
