// Contains Duplicate
// https://leetcode.com/problems/contains-duplicate
// Pattern: Hashing | Difficulty: Easy | Time: O(n) | Space: O(n)

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    
    
    private static boolean containsDuplicate(int[] nums) {
        
        Set<Integer> set = new HashSet<>();

        for(int num : nums) {

            if(set.contains(num)) {
                return true;
            } 

            /*
                we could also write 
                if (!seen.add(num)) {
                   return true;
                }
            
            */
            
            set.add(num);
            

        }
        return false;
    }

    public static void main(String[] args) {
        
        int nums[] = {1,2,3,4};

        System.out.println(containsDuplicate(nums));

    }
}

/*

Solved using set to as set contains has average o(1) time complexity 

This could be solved using hashing technique too

*/