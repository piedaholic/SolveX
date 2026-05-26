import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KadaneAlgorithmTest {
    // Method to test Kadane's Algorithm
    public static int kadane(int[] arr) {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for (int i = 0; i < arr.length; i++) {
            maxEndingHere += arr[i];  
            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
            }
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
            }
        }
        return maxSoFar;
    }

    @Test
    public void testKadane() {
        // Test with positive numbers
        int[] arr1 = {1, 2, 3, -2, 5};
        Assertions.assertEquals(9, kadane(arr1));

        // Test with negative numbers
        int[] arr2 = {-1, -2, -3, -4};
        Assertions.assertEquals(-1, kadane(arr2));

        // Test with mixed numbers
        int[] arr3 = {34, -50, 42, 14, -5, 86};
        Assertions.assertEquals(137, kadane(arr3));

        // Test with an array of one element
        int[] arr4 = {-5};
        Assertions.assertEquals(-5, kadane(arr4));

        // Test with all positive numbers
        int[] arr5 = {8, 7, 9};
        Assertions.assertEquals(24, kadane(arr5));

        // Test with all negative numbers
        int[] arr6 = {-2, -3, -1};
        Assertions.assertEquals(-1, kadane(arr6));
    }
}