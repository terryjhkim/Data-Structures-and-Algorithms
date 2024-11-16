/**
 * Your implementation of a LinkedDeque.
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
public class LinkedDeque<T> {

    // Do not add new instance variables or modify existing ones.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the front of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null!");
        }
        if (size == 0) {
            LinkedNode<T> newNode = new LinkedNode<T>(data);
            head = newNode;
            tail = newNode;
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data);
            LinkedNode<T> newNext = head;
            newNode.setNext(newNext);
            newNext.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    /**
     * Adds the element to the back of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null!");
        }
        if (size == 0) {
            LinkedNode<T> newNode = new LinkedNode<T>(data);
            tail = newNode;
            head = newNode;
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data);
            LinkedNode<T> lastNext = tail;
            newNode.setPrevious(lastNext);
            lastNext.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the first element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        T temp = head.getData();
        if (size == 0) {
            throw new java.util.NoSuchElementException("The deque is empty!");
        } else if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedNode<T> newHead = head.getNext();
            head = newHead;
            newHead.setPrevious(null);
        }
        size--;
        return temp;
    }

    /**
     * Removes and returns the last element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        T temp = tail.getData();
        if (size == 0) {
            throw new java.util.NoSuchElementException("The deque is empty!");
        } else if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedNode<T> newTail = tail.getPrevious();
            tail = newTail;
            newTail.setNext(null);
        }
        size--;
        return temp;
    }

    /**
     * Returns the first data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The deque is empty!");
        }
        T temp = head.getData();
        return temp;
    }

    /**
     * Returns the last data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The deque is empty!");
        }
        T temp = tail.getData();
        return temp;
    }

    /**
     * Returns the head node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the deque
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
