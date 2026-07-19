// Reverse String
// https://leetcode.com/problems/reverse-string/
// Pattern: Two Pointers | Difficulty: Easy | Time: O(n) | Space: O(1)


public class ReverseString {
    
    private static void reverseString(char[] s) {
        int ptr1 = 0; // one pointer will start from start
        int ptr2 = s.length-1; // another will start from end

        while(ptr1 <  ptr2) {
            swap(s, ptr1, ptr2);

            ptr1 += 1;
            ptr2 -= 1;
        }

    }

    private static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        char[] str = {'h','e','l','l','o'};

        reverseString(str);

        System.out.println(str);
    }


}
