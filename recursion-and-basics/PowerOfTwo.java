// Power of Two
// https://leetcode.com/problems/power-of-two/
// Pattern: Bit Manipulation | Difficulty: Easy | Time: O(1) | Space: O(1)

public class PowerOfTwo {

    public static boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static void main(String[] args) {
        int n = 16;
        System.out.println(isPowerOfTwo(n));
    }
}


/*
 * Explanation:
 * A power of 2 has exactly one set bit (1) in its binary representation.
 *
 * Examples:
 * 1  -> 0001
 * 2  -> 0010
 * 4  -> 0100
 * 8  -> 1000
 * 16 -> 10000
 *
 * When we subtract 1 from a power of 2:
 * - The only set bit becomes 0.
 * - All bits to its right become 1.
 *
 * Example:
 * n = 8
 * 8  = 1000
 * 7  = 0111
 * ------------
 * 8 & 7 = 0000
 *
 * Since the result is 0, 8 is a power of 2.
 *
 * Non-power of 2 example:
 * n = 10
 * 10 = 1010
 * 9  = 1001
 * ------------
 * 10 & 9 = 1000 (not 0)
 *
 * Therefore, a number is a power of 2 if:
 * (n > 0) && ((n & (n - 1)) == 0)
 *
 * Time Complexity : O(1)
 * Space Complexity: O(1)
 * Pattern         : Bit Manipulation
 */