import java.util.ArrayList;

/**
 * Your implementation of a MinHeap.
 *
 * @author Terry Kim
 * @version 1.0
 * @userid tkim661
 * @GTID 903871637
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MinHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     * To initialize the backing array, create a Comparable array and then cast
     * it to a T array.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * size of the passed in ArrayList (not INITIAL_CAPACITY). Index 0 should
     * remain empty, indices 1 to n should contain the data in proper order, and
     * the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null!");
        }
        backingArray = (T[]) new Comparable[(data.size() * 2) + 1];
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new java.lang.IllegalArgumentException("The element in data is null!");
            }
            backingArray[i + 1] = data.get(i);
        }
        size = data.size();
        int parent = size / 2;
        for (int i = parent; i > 0; i--) {
            downHeap(i);
        }
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * The order property of the heap must be maintained after adding. You can
     * assume that no duplicate data will be passed in.
     * 
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null!");
        }
        if (size == backingArray.length - 1) {
            T[] temp = (T[]) new Comparable[INITIAL_CAPACITY * 2];
            for (int i = 1; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size + 1] = data;
        size++;
        upHeap(size);
    }

    /**
     * Helper upHeap method for add() algorithm.
     * @param index takes in the index where the data should be added
     */
    private void upHeap(int index) {
        int curr = index;
        while (curr / 2 >= 1) {
            int parentIndex = curr / 2;
            if (backingArray[curr].compareTo(backingArray[parentIndex]) < 0) {
                T temp = backingArray[curr];
                backingArray[curr] = backingArray[parentIndex];
                backingArray[parentIndex] = temp;
            }
            curr = parentIndex;
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * The order property of the heap must be maintained after removing.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The heap is empty!");
        }
        T min = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return min;
    }

    /**
     * Helper downHeap method for remove() algorithm.
     * @param index the index of the element to down-heap
     */
    private void downHeap(int index) {
        int curr = index;
        while (curr * 2 <= size) {
            int left = curr * 2;
            int right = curr * 2 + 1;
            int small = left;
            if (right <= size) {
                if (backingArray[right].compareTo(backingArray[left]) < 0) {
                    small = right;
                }
            }
            if (backingArray[small].compareTo(backingArray[curr]) < 0) {
                T temp = backingArray[curr];
                backingArray[curr] = backingArray[small];
                backingArray[small] = temp;
                curr = small;
            } else {
                break;
            }
        }
    }

    /**
     * Returns the minimum element in the heap.
     *
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The heap is empty!");
        }
        T temp = backingArray[1];
        return temp;
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

