import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionSortVisualizer extends JPanel {

    private int[] numbers;
    private final int size = 200;
    private final int gap = 4;

    public SelectionSortVisualizer() {
        // Initialize the array
        numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }

        // Shuffle the array
        shuffleArray(numbers);
    }

    // Function to shuffle the array
    private void shuffleArray(int[] arr) {
        Random random = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Simple swap
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    // Override paintComponent to draw the lines
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color to black
        this.setBackground(Color.BLACK);

        // Initial plot of numbers in graph
        g.setColor(Color.WHITE);
        for (int i = 0; i < size; i++) {
            g.drawLine(gap * i, size, gap * i, size - numbers[i]);
        }
    }

    // Function to swap and repaint for visualization
    private void swapAndRepaint(int i, int j) {
        // Pause to see the visualization
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Swap the elements in the array
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;

        // Repaint the panel
        repaint();
    }

    // Selection sort with visualization
    public void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int min_idx = i;

            // Find the minimum element in unsorted array
            for (int j = i + 1; j < size; j++) {
                if (numbers[j] < numbers[min_idx]) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first element
            swapAndRepaint(i, min_idx);
        }
    }

    // Main method to initialize the frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Selection Sort Visualization");
        SelectionSortVisualizer visualizer = new SelectionSortVisualizer();
        frame.add(visualizer);
        frame.setSize(800, 600); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Delay a little before starting sorting
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start selection sort with visualization
        visualizer.selectionSort();
    }
}
