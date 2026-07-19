// Pow(x, n)
// https://leetcode.com/problems/powx-n/
// Pattern: Binary Exponentiation (Fast Power) | Difficulty: Medium | Time: O(log n) | Space: O(1)

class PowerOfN {

    private static double myPow(double x, int n) {

        // Convert exponent to long because:
        // Integer.MIN_VALUE = -2^31 cannot be converted to a positive int.
        // Example:
        // -(-2147483648) = 2147483648 (out of int range)
        long exp = n;

        // If the exponent is negative:
        // x^(-n) = 1 / x^n
        // Convert the base and make the exponent positive.
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        // Stores the final answer.
        double res = 1;

        // Binary Exponentiation:
        // At every step:
        // 1. If the current exponent is odd, include the current base.
        // 2. Square the base to move to the next power.
        // 3. Divide the exponent by 2.
        while (exp > 0) {

            // If the least significant bit is 1,
            // the exponent is odd, so include this power.
            if ((exp & 1) == 1) {
                res *= x;
            }

            // Move to the next power:
            // x -> x² -> x⁴ -> x⁸ -> ...
            x *= x;

            // Right shift by 1 is equivalent to dividing by 2.
            // Also moves to the next bit in the binary representation.
            exp >>= 1;
        }

        return res;
    }

    public static void main(String[] args) {

        double x = 2.00000;
        int n = -3;

        System.out.println(myPow(x, n));
    }
}



/*
 * Solution Explanation:
 *
 * The brute force approach would multiply x by itself n times.
 *
 * Example:
 *      x = 2, n = 13
 *
 *      2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2
 *
 * This requires O(n) multiplications.
 *
 * ------------------------------------------------------------
 *
 * Observation:
 *
 * Instead of multiplying x repeatedly, notice that we can
 * express the exponent as a sum of powers of 2.
 *
 * Example:
 *
 *      13 = 8 + 4 + 1
 *
 * Therefore,
 *
 *      2¹³ = 2⁸ × 2⁴ × 2¹
 *
 * So instead of performing 13 multiplications, we only need
 * the powers corresponding to the set bits of the exponent.
 *
 * ------------------------------------------------------------
 *
 * How do we generate these powers?
 *
 * Start with x:
 *
 *      x¹
 *
 * Square it:
 *
 *      x²
 *
 * Square again:
 *
 *      x⁴
 *
 * Square again:
 *
 *      x⁸
 *
 * Square again:
 *
 *      x¹⁶
 *
 * Every squaring doubles the exponent.
 *
 *      x
 *       ↓
 *      x²
 *       ↓
 *      x⁴
 *       ↓
 *      x⁸
 *       ↓
 *      x¹⁶
 *
 * ------------------------------------------------------------
 *
 * We now process the exponent one binary digit at a time.
 *
 * Example:
 *
 *      n = 13
 *
 * Binary:
 *
 *      1101
 *
 * Reading from right to left:
 *
 *      Bit = 1  -> Include x¹
 *      Bit = 0  -> Skip x²
 *      Bit = 1  -> Include x⁴
 *      Bit = 1  -> Include x⁸
 *
 * Final answer:
 *
 *      x¹ × x⁴ × x⁸ = x¹³
 *
 * ------------------------------------------------------------
 *
 * Algorithm:
 *
 * Initialize:
 *
 *      result = 1
 *
 * While exponent > 0:
 *
 *      If exponent is odd
 *          result *= current base
 *
 *      Square the current base
 *
 *      Divide exponent by 2
 *
 * ------------------------------------------------------------
 *
 * Example:
 *
 *      x = 2
 *      n = 13
 *
 * ------------------------------------------------------------
 * exp    base      result
 * ------------------------------------------------------------
 * 13      2          1
 * odd -> result = 2
 * base = 4
 * exp = 6
 *
 * 6       4          2
 * even -> skip
 * base = 16
 * exp = 3
 *
 * 3      16          2
 * odd -> result = 32
 * base = 256
 * exp = 1
 *
 * 1     256         32
 * odd -> result = 8192
 * base = 65536
 * exp = 0
 *
 * Answer = 8192
 *
 * ------------------------------------------------------------
 *
 * Handling Negative Exponents:
 *
 * Mathematical property:
 *
 *      x⁻ⁿ = 1 / xⁿ
 *
 * Therefore, before starting the algorithm:
 *
 *      if exponent < 0
 *          x = 1 / x
 *          exponent = -exponent
 *
 * ------------------------------------------------------------
 *
 * Why convert int to long?
 *
 * Integer.MIN_VALUE = -2147483648
 *
 * Negating it gives:
 *
 *      2147483648
 *
 * which cannot fit inside an int.
 *
 * Therefore, store the exponent in a long before negating it.
 *
 * ------------------------------------------------------------
 *
 * Time Complexity:
 *
 * Every iteration divides the exponent by 2.
 *
 *      n
 *      n/2
 *      n/4
 *      n/8
 *      ...
 *      1
 *
 * Number of iterations = O(log n)
 *
 * Time = O(log n)
 *
 * ------------------------------------------------------------
 *
 * Space Complexity:
 *
 * Only a few variables are used.
 * No recursion or extra data structures.
 *
 * Space = O(1)
 */