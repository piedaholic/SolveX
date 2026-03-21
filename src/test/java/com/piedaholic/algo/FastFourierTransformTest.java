import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FastFourierTransformTest {

    @Test
    public void testTransform() {
        // Example input
        double[] input = {1.0, 0.0, 0.0, 0.0}; // Should correspond to a single frequency
        double[] expectedReal = {1.0, 0.0, 0.0, 0.0}; // Expected real results
        double[] expectedImag = {0.0, 0.0, 0.0, 0.0}; // Expected imaginary results
        FastFourierTransform fft = new FastFourierTransform();

        // Perform FFT
        Complex[] output = fft.transform(input);

        // Validate the results
        for (int i = 0; i < output.length; i++) {
            assertEquals(expectedReal[i], output[i].getReal(), 0.001);
            assertEquals(expectedImag[i], output[i].getImaginary(), 0.001);
        }
    }

    @Test
    public void testInverseTransform() {
        // Example input - result of previous FFT
        Complex[] input = {new Complex(1.0, 0.0), new Complex(0.0, 0.0), new Complex(0.0, 0.0), new Complex(0.0, 0.0)};
        double[] expected = {1.0, 0.0, 0.0, 0.0}; // Expected time-domain results
        FastFourierTransform fft = new FastFourierTransform();

        // Perform Inverse FFT
        double[] output = fft.inverseTransform(input);

        // Validate the results
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], output[i], 0.001);
        }
    }

    @Test
    public void testTransformSymmetry() {
        // Test that the FFT and inverse return to original signal
        double[] input = {1.0, 2.0, 3.0, 4.0};
        FastFourierTransform fft = new FastFourierTransform();

        Complex[] transformed = fft.transform(input);
        double[] recovered = fft.inverseTransform(transformed);

        for (int i = 0; i < input.length; i++) {
            assertEquals(input[i], recovered[i], 0.001);
        }
    }

}