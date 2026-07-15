
// Fibonacci
// https://leetcode.com/problems/fibonacci-number/
// Pattern: Recursion | Difficulty: Easy | Time: O(n^2) | Space: O(n)


public class Fibonacci {
    
    public static int fib(int n) {
     
       if( n <= 1) {
            return n;
       }

        return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        
         System.out.println(fib(6));
        
    }
}
