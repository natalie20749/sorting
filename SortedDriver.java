package cs2720.p4;

import cs2720.p4.Sorted;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SortedDriver {
    public static void main(String args[]) {
        SortedDriver driver = new SortedDriver();

        if (args.length < 1) {
            System.out.println("No input file provided.");
            return;
        } // if

        String fn = args[0];

        try {
            int[] numbers = driver.readFile(fn);
            driver.algorithms(numbers);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
            e.printStackTrace();
        } // try/catch

    } //main

    private int[] readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);

        ArrayList<Integer> numberList = new ArrayList<>();
        while (scan.hasNextInt()) {
            int num = scan.nextInt();
            numberList.add(num);
        } //while
        scan.close();

        int[] numbers = new int[numberList.size()];
        for (int i = 0; i < numberList.size(); i++) {
            numbers[i] = numberList.get(i);
        } //for
        return numbers;
    } //readFile

    private void algorithms(int numbers[]) {
        Sorted sorter = new Sorted();

        Scanner scanner = new Scanner(System.in);
        System.out.println("selection-sort (s) merge-sort (m) heap-sort (h) quick-sort-fp (q)" +
                           " quick-sort-rp (r)");
        System.out.print("Enter the algorithm: ");
        char algorithm = scanner.next().charAt(0);

        switch (algorithm) {
        case 's':
            long selectionCount = sorter.selectionSort(numbers,numbers.length);
            System.out.println("#Selection-sort comparisons: " + selectionCount);
            break;
        case 'm':
            long mergeCount = sorter.mergeSort(numbers,0,numbers.length-1);
            sorter.print(numbers,numbers.length);
            System.out.println("#Merge-sort comparisons: " + mergeCount);
            break;
        case 'h':
            long heapCount = sorter.heapSort(numbers,numbers.length);
            sorter.print(numbers,numbers.length);
            System.out.println("#Heap-sort comparisons: " + heapCount);
            break;
        case 'q':
            sorter.quickSortFP(numbers,0,numbers.length-1);
            sorter.print(numbers,numbers.length);
            System.out.println("#Quick-sort-fp comparisons: " + sorter.getComparison());
            break;
        case 'r':
            sorter.quickSortRP(numbers,0,numbers.length-1);
            sorter.print(numbers,numbers.length);
            System.out.println("#Quick-sort-rp comparisons: " + sorter.getComparison());
            break;
        } //switch
        scanner.close();
    } //algorithms
} //SortedDriver
