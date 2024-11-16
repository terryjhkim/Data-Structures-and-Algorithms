/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author Terry Kim
 * @version 1.0
 * @userid tkim662
 * @GTID 903871637
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index is either less than 0 or greater than the size!");
        }
        if (data == null) {
            throw new IllegalArgumentException("The data is null!");
        }
        if (size == 0) {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
            head = newNode;
            tail = newNode;
            size++;
        } else if (size == index) {
            addToBack(data);
        } else if (index == 0) {
            addToFront(data);
        } else if (index > size / 2) {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
            DoublyLinkedListNode<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrevious();
            }
            newNode.setNext(current);
            newNode.setPrevious(current.getPrevious());
            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);
            size++;
        } else if (index <= size / 2) {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
            DoublyLinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null!");
        }
        if (size == 0) {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
            head = newNode;
            tail = newNode;
        } else if (size == 1) {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null!");
        }
        if (size == 0) {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
            head = newNode;
            tail = newNode;
        } else if (size == 1) {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        } else {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        T temp = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is less than 0 or greater than or equal to the size!");
        }
        if (index == 0) {
            removeFromFront();
        } else if (size == index) {
            removeFromBack();
        } else if (index > size / 2) {
            DoublyLinkedListNode<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrevious();
            }
            DoublyLinkedListNode<T> before = current.getPrevious().getPrevious();
            temp = current.getPrevious().getData();
            current.setPrevious(before);
            before.setNext(current);
            size--;
        } else if (index <= size / 2) {
            DoublyLinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            DoublyLinkedListNode<T> after = current.getNext().getNext();
            temp = current.getNext().getData();
            current.setNext(after);
            after.setPrevious(current);
            size--;
            return temp;
        }
        return temp;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        T temp = head.getData();
        if (size == 0) {
            throw new java.util.NoSuchElementException("The list is empty!");
        } else if (size == 1) {
            tail = null;
            head = head.getNext();
            size = size - 1;
        } else {
            DoublyLinkedListNode<T> after = head.getNext();
            after.getNext().setPrevious(after);
            after.setPrevious(null);
            head = after;
            size = size - 1;
        }
        return temp;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        T temp = tail.getData();
        if (size == 0) {
            throw new java.util.NoSuchElementException("The list is empty!");
        } else if (size == 1) {
            tail = null;
            head = null;
            size = size - 1;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            size = size - 1;
        }
        return temp;
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        T temp = null;
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("The index is less than 0 or greater than or equal to size");
        } else if (index > size / 2) {
            DoublyLinkedListNode<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrevious();
            }
            temp = current.getData();
        } else if (index <= size / 2) {
            DoublyLinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            temp = current.getData();
        }
        return temp;
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        T temp = null;
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null!");
        } else if (size == 0) {
            throw new java.util.NoSuchElementException("Data is not found!");
        } else if (size == 1) {
            removeFromFront();
        }
        if (data == tail.getData()) {
            removeFromBack();
        }
        if (data == head.getData()) {
            removeFromFront();
        } else {
            DoublyLinkedListNode<T> current = tail;
            for (int i = size - 2; i >= 0; i--) {
                if (current.getData().equals(data)) {
                    temp = current.getData();
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    break;
                }
                current = current.getPrevious();
            }
        }
        size--;
        return temp;
    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        if (size == 0) {
            return newArray;
        } else {
            DoublyLinkedListNode<T> current = head;
            for (int i = 0; i < size; i++) {
                newArray[i] = current.getData();
                current = current.getNext();
            }
        }
        return newArray;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}