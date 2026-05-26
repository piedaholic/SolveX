package com.piedaholic.algo;

public class KadaneAlgorithm {

    public static class MaxSubarrayResult {
        public long maxSum;
        public int startIndex;
        public int endIndex;

        public MaxSubarrayResult(long maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public String toString() {
            return String.format("Max Sum: %d, Start Index: %d, End Index: %d", maxSum, startIndex, endIndex);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MaxSubarrayResult result = (MaxSubarrayResult) obj;
            return maxSum == result.maxSum && startIndex == result.startIndex && endIndex == result.endIndex;
        }
    }

    public static MaxSubarrayResult findMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        long maxSum = arr[0];
        long currentSum = arr[0];
        int maxStartIndex = 0;
        int maxEndIndex = 0;
        int tempStartIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (currentSum < 0) {
                currentSum = arr[i];
                tempStartIndex = i;
            } else {
                currentSum += arr[i];
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxStartIndex = tempStartIndex;
                maxEndIndex = i;
            }
        }
        return new MaxSubarrayResult(maxSum, maxStartIndex, maxEndIndex);
    }

    public static long findMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        long maxSum = arr[0];
        long currentSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static MaxSubarrayResult findMaxSubarray(long[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        long maxSum = arr[0];
        long currentSum = arr[0];
        int maxStartIndex = 0;
        int maxEndIndex = 0;
        int tempStartIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (currentSum < 0) {
                currentSum = arr[i];
                tempStartIndex = i;
            } else {
                currentSum += arr[i];
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxStartIndex = tempStartIndex;
                maxEndIndex = i;
            }
        }
        return new MaxSubarrayResult(maxSum, maxStartIndex, maxEndIndex);
    }

    public static int[] getMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        MaxSubarrayResult result = findMaxSubarray(arr);
        int[] subarray = new int[result.endIndex - result.startIndex + 1];
        System.arraycopy(arr, result.startIndex, subarray, 0, subarray.length);
        return subarray;
    }
}