package cs2720.p4;

import java.util.Random;

public class Sorted {
    private long comparison;

    public Sorted() {
        this.comparison = 0;
    } //Sorted

    /**
     * Uses selection sort algorithm to sort data.
     * Divides the array into two parts: already sorted, and not yet sorted.
     * On each pass, finds the smallest of the unsorted elements, and
     * swaps it into its correct place,thereby increasing the number of sorted elements by one.
     *
     * @param values[] - array of integers
     * @param numValues - size of int array
     *
     * @returns count - the number of comparisons made
     */
    public long selectionSort(int values[], int numValues) {
        long count = 0;
        int endIndex = numValues - 1; //index of last element in the array

        for (int current = 0; current < endIndex; current++) {
            int minIndex = current;
            for (int index = current+1; index < numValues; index++) {
                count++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                } //if
            } //for
            if (minIndex != current) {
                swap(values, current, minIndex);
            } //if
        } //for
        print(values,numValues);
        return count;
    } //selectionSort

    /**
     * Swaps two data elements in an array.
     *
     * @param values[] - array of integers
     * @param index1 - the index of the first element to swap
     * @param index2 - the index of the second element to swap
     */
    private void swap(int values[], int index1, int index2) {
        int temp = values[index1]; //store the value of first element in temp variable
        values[index1] = values[index2]; //Assign the value of the second element to first element
        values[index2] = temp; //assign temp value to second element
    } //swap

    /**
     * Prints the elements of an integer array.
     *
     * @param values[] - array of integers
     * @param numValues - the length of the array
     */
    public void print(int values[], int numValues) {
        for (int i = 0; i < numValues; i++) {
            System.out.print(values[i] + " ");
        } //for
        System.out.println();
    } //print

    /**
     * Uses the merge sort algorithm to sort data.
     * Cuts the array in half, then sorts the left half and then sorts the right half.
     * Once the two halves are sorted, merge the two sorted halves into one sorted array.
     *
     * @param values[] - array of integers
     * @param first - index of the first element in the array
     * @param last - index of the last element in the array
     *
     * @returns count - the number of comparisons made
     */
    public long mergeSort(int values[], int first, int last) {
        long count = 0;
        if (first < last) {
            int middle = (first + last)/2;
            count += mergeSort(values, first, middle);
            count += mergeSort(values,middle+1,last);

            //merge two subarrays
            count += merge(values,first,middle,middle+1,last);
        } //if
        return count;
    } //mergeSort

    /**
     * Returns the number of comparisons.
     *
     * @returns number of comparisons that are made
     */
    public long getComparison() {
        return comparison;
    } //getCount

    /**
     * Merges the sorted arrays into one sorted array.
     *
     * @param values[] - array of integers
     * @param leftFirst - index of the first element in the left sub-array
     * @param leftLast - index of the last element in the left sub-array
     * @param rightFirst - index of the first element in the right sub-array
     * @param rightLast - index of the last element in the right sub-array
     *
     * @returns count - the number of comparisons made
     */
    public long merge(int values[], int leftFirst, int leftLast, int rightFirst, int rightLast) {
        long count = 0;
        int[] tempArray = new int[values.length];
        int index = leftFirst;
        int saveFirst = leftFirst;

        while ((leftFirst <= leftLast) && (rightFirst <= rightLast)) {
            count++;
            if (values[leftFirst] < values[rightFirst]) {
                tempArray[index] = values[leftFirst];
                leftFirst++;
            } else {
                tempArray[index] = values[rightFirst];
                rightFirst++;
            } //else
            index++;
        } //while
        while (leftFirst <= leftLast) {
            //copy remaining items from left half
            tempArray[index] = values[leftFirst];
            leftFirst++;
            index++;
        } //while
        while (rightFirst <= rightLast) {
            //copy remaining items from right half
            tempArray[index] = values[rightFirst];
            rightFirst++;
            index++;
        } //while
        for (index = saveFirst; index <= rightLast; index++) {
            values[index] = tempArray[index];
        } //for
        return count;
    } //merge

    /**
     * Uses the quick sort algorithm to sort data. Using the first element of the array
     * as the pivot, the array is initially split into two sections: the pivot and the remaining
     * array elements. All elements less than the pivot are swapped so they are on the left side
     * of the pivot. All elements greater than the pivot will be to the right of the pivot.
     *
     * @param values - array of integers
     * @param first - index of first element in the array
     * @param last - index of last element in the array
     */
    public void quickSortFP(int values[], int first, int last) {
        if (first < last) {
            int splitPoint = split(values,first,last);

            quickSortFP(values,first,splitPoint - 1);
            quickSortFP(values,splitPoint + 1,last);
        } //if
    } //quickSortFP

    /**
     * Splits the integer array based on a given pivot position. For this method, the
     * pivot position is the first element in the array.
     *
     * @param values[] - array of integers
     * @param first - lower bound
     * @param last - upper bound
     *
     * @returns end - the next value to act as a splitPoint
     */
    public int split(int values[], int first, int last) {
        int pivot = values[first];
        int start = first + 1;
        int end = last;

        while (start <= end) {
            comparison++;
            while (start <= end && values[start] <= pivot) {
                start++;
                comparison++;
            } //while
            while (start <= end && values[end] > pivot) {
                end--;
                comparison++;
            } //while
            if (start < end) {
                swap(values,start,end);
            } //if
        } //while
        swap(values,first,end);
        return end;
    } //split

    /**
     * Uses the quick sort algorithm to sort data. Using a randomly generated element of the array
     * as the pivot, the array is initially split into two sections: the pivot and the remaining
     * array elements. All elements less than the pivot are swapped so they are on the left side
     * of the pivot. All elements greater than the pivot will be to the right of the pivot.
     *
     * @param values - array of integers
     * @param first - index of first element in the array
     * @param last - index of last element in the array
     */
    public void quickSortRP(int values[], int first, int last) {
        if (first < last) {
            int splitPoint = partition(values,first,last);

            quickSortFP(values,first,splitPoint - 1);
            quickSortFP(values,splitPoint + 1,last);
        } //if
    } //quickSortRP

    /**
     * Splits the integer array using a randomly generated element of the array as the
     * pivot position.
     *
     * @param values - array of integers
     * @param first - index of first element in the array
     * @param last - index of last element in the array
     */
    public int partition(int values[], int first, int last) {
        random(values,first,last);
        int pivot = values[last];
        this.comparison = 0;

        int i = (first-1); // index of smaller element
        for (int j = first; j < last; j++) {
            if (values[j] < pivot) {
                i++;
                swap(values,i,j);
                comparison++;
            } //if
        } //for
        swap(values,i+1,last);
        return i+1;
    } //partition

    /**
     * Random integer generator used to generate a random int to use as a pivot.
     *
     * @param values - array of integers
     * @param first - index of first element in the array
     * @param last - index of last element in the array
     */
    public void random(int values[], int first, int last) {
        Random rand = new Random();
        int pivot = rand.nextInt(last - first)+first;

        swap(values,pivot,last);
    } //random

    /**
    * Uses heap sort algorithm to sort data. First, the unsorted array is made into a heap
    * by satisfying the order property. Then the root (maximum value) is taken off the heap
    * by swapping it into its correct place in the array at the end of the unsorted elements.
    * Reheap Down algorithm is then used to bring the next largest element into the root
    * position.
    *
    * @param values[] - array of integers
    * @param numValues - length of array
    *
    * @returns count - the number of comparisons made
    */
    public long heapSort(int values[], int numValues) {
        long count = 0;
        int index;

        //convert array into a heap
        for (index = numValues/2-1; index >= 0; index--) {
            count += reHeapDown(values, index, numValues-1);
        } //for

        //sort the array
        for (index = numValues-1; index >= 1; index--) {
            swap(values,0,index);
            count += reHeapDown(values,0,index-1);
        } //for
        return count;
    } //heapSort

    /**
     * Brings the next largest element into the root position.
     *
     * @param values[] - array of integers
     * @param root - max element
     * @param bottom -
     *
     * @returns count - the number of comparisons made
     */
    public long reHeapDown(int values[], int root, int bottom) {
        long count = 0;

        int maxChild;
        int rightChild;
        int leftChild;

        leftChild = root*2+1;
        rightChild = root*2+2;

        if (leftChild <= bottom) {
            count++;
            if (leftChild == bottom) {
                maxChild = leftChild;
                count++;
            } else if (values[leftChild] <= values[rightChild]) {
                maxChild = rightChild;
                count++;
            } else {
                maxChild = leftChild;
                count++;
            }
            if (values[root] < values[maxChild]) {
                swap(values,root,maxChild);
                count += reHeapDown(values,maxChild,bottom);
            } //if
        } //if
        return count;
    } //reHeapDown
} // Sorted
