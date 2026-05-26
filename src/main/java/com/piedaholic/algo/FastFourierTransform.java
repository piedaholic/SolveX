package com.piedaholic.algo;

import java.util.Arrays;

/**
 * Fast Fourier Transform (FFT) Implementation
 * 
 * This class provides an efficient implementation of the Cooley-Tukey
 * Fast Fourier Transform algorithm for computing the Discrete Fourier Transform (DFT).
 * 
 * Time Complexity: O(n log n) where n is the input size (must be a power of 2)
 * Space Complexity: O(n)
 */
public class FastFourierTransform {

    /**
     * Complex number class for FFT computation
     */
    public static class Complex {
        public double real;
        public double imag;

        public Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        public Complex(double real) {
            this(real, 0);
        }

        public Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imag + other.imag);
        }

        public Complex subtract(Complex other) {
            return new Complex(this.real - other.real, this.imag - other.imag);
        }

        public Complex multiply(Complex other) {
            double realPart = this.real * other.real - this.imag * other.imag;
            double imagPart = this.real * other.imag + this.imag * other.real;
            return new Complex(realPart, imagPart);
        }

        public Complex conjugate() {
            return new Complex(this.real, -this.imag);
        }

        public double magnitude() {
            return Math.sqrt(this.real * this.real + this.imag * this.imag);
        }

        public double phase() {
            return Math.atan2(this.imag, this.real);
        }

        @Override
        public String toString() {
            if (imag >= 0) {
                return String.format("%.6f + %.6fi", real, imag);
            } else {
                return String.format("%.6f - %.6fi", real, -imag);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Complex complex = (Complex) obj;
            return Math.abs(real - complex.real) < 1e-9 &&
                   Math.abs(imag - complex.imag) < 1e-9;
        }
    }

    /**
     * Performs the Cooley-Tukey Fast Fourier Transform on input array
     * 
     * @param input Array of Complex numbers (size must be a power of 2)
     * @return Array of Complex numbers representing the DFT
     * @throws IllegalArgumentException if input size is not a power of 2
     */
    public static Complex[] fft(Complex[] input) {
        int n = input.length;
        
        if (n == 0) return new Complex[0];
        if (!isPowerOfTwo(n)) {
            throw new IllegalArgumentException("Input size must be a power of 2");
        }
        
        if (n == 1) {
            return new Complex[]{input[0]};
        }

        // Divide: split into even and odd indices
        Complex[] even = new Complex[n / 2];
        Complex[] odd = new Complex[n / 2];

        for (int i = 0; i < n / 2; i++) {
            even[i] = input[2 * i];
            odd[i] = input[2 * i + 1];
        }

        // Conquer: recursively compute FFT for even and odd parts
        Complex[] evenFFT = fft(even);
        Complex[] oddFFT = fft(odd);

        // Combine: merge results
        Complex[] result = new Complex[n];
        for (int k = 0; k < n / 2; k++) {
            // W_n^k = e^(-2πik/n)
            double angle = -2 * Math.PI * k / n;
            Complex wn = new Complex(Math.cos(angle), Math.sin(angle));
            
            Complex oddTerm = wn.multiply(oddFFT[k]);
            result[k] = evenFFT[k].add(oddTerm);
            result[k + n / 2] = evenFFT[k].subtract(oddTerm);
        }

        return result;
    }

    /**
     * Performs the Inverse Fast Fourier Transform
     * 
     * @param input Array of Complex numbers (DFT result)
     * @return Array of Complex numbers representing the IDFT
     */
    public static Complex[] ifft(Complex[] input) {
        int n = input.length;
        
        if (n == 0) return new Complex[0];
        if (!isPowerOfTwo(n)) {
            throw new IllegalArgumentException("Input size must be a power of 2");
        }

        // Conjugate the input
        Complex[] conjugated = new Complex[n];
        for (int i = 0; i < n; i++) {
            conjugated[i] = input[i].conjugate();
        }

        // Apply FFT
        Complex[] result = fft(conjugated);

        // Conjugate and scale the result
        for (int i = 0; i < n; i++) {
            result[i] = result[i].conjugate();
            result[i] = new Complex(result[i].real / n, result[i].imag / n);
        }

        return result;
    }

    /**
     * Checks if a number is a power of 2
     * 
     * @param n The number to check
     * @return true if n is a power of 2, false otherwise
     */
    private static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}