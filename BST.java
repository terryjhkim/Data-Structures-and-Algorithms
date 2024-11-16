import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null!");
        }
        for (T dataItem : data) {
            if (dataItem == null) {
                throw new java.lang.IllegalArgumentException("An element in data is null!");
            }
            add(dataItem);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null!");
        }
        root = helpAdd(data, root);
        size++;
    }

    /** Recursive helper method for the add(T data) method.
     *
     * @param data the data to add
     * @param node the node to add
     * @return Node that was added
     */
    private BSTNode<T> helpAdd(T data, BSTNode<T> node) {
        if (node == null) {
            return new BSTNode<T>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(helpAdd(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(helpAdd(data, node.getRight()));
        }
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null!");
        }
        BSTNode<T> removedNode = new BSTNode<>(data);
        root = helpRemove(data, root, removedNode);
        return removedNode.getData();
    }

    /** Recursive method to help remove(T data) method.
     *
     * @param data the data to remove
     * @param node the node to remove
     * @param removedNode dummy node that holds the node to be removed
     * @return Node that was removed
     */
    private BSTNode<T> helpRemove(T data, BSTNode<T> node, BSTNode<T> removedNode) {
        if (node == null) {
            throw new NoSuchElementException("The data is not in the tree!");
        }
        int temp = node.getData().compareTo(data);
        if (temp < 0) {
            node.setRight(helpRemove(data, node.getRight(), removedNode));
        } else if (temp > 0) {
            node.setLeft(helpRemove(data, node.getLeft(), removedNode));
        } else {
            removedNode.setData(node.getData());
            size--;
            // there are three types of removals
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            if (node.getRight() != null && node.getLeft() != null) {
                BSTNode<T> replaceNode = new BSTNode<>(null);
                node.setRight(secondHelpRemove(data, node.getRight(), replaceNode));
                node.setData(replaceNode.getData());
            }
        }
        return node;
    }

    /**
     * Recursive helper for removing the successors.
     *
     * @param data the data to be removed
     * @param node the current node
     * @param replaceNode dummy node
     * @return Node that is successor
     */
    private BSTNode<T> secondHelpRemove(T data, BSTNode<T> node, BSTNode<T> replaceNode) {
        if (node.getLeft() == null) {
            replaceNode.setData(node.getData());
            return node.getRight();
        }
        node.setLeft(secondHelpRemove(data, node.getLeft(), replaceNode));
        return node;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null!");
        }
        T temp = helperContains(data, root);
        if (temp == null) {
            throw new java.util.NoSuchElementException("The data is not in the tree!");
        }
        return temp;
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null!");
        }
        return helperContains(data, root) != null;
    }

    /** Recursive helper for the contains(T data) method.
     *
     * @param data the data we are looking for
     * @param node the current node
     * @return T the node's data
     */
    private T helperContains(T data, BSTNode<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) == 0) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            return helperContains(data, node.getLeft());
        } else {
            return helperContains(data, node.getRight());
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        helperPreorder(root, list);
        return list;
    }

    /** Recursive helper method for the preorder() method.
     *
     * @param node the passed in node
     * @param list the updated list after using preorder method
     */
    private void helperPreorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            helperPreorder(node.getLeft(), list);
            helperPreorder(node.getRight(), list);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        helperInorder(root, list);
        return list;
    }

    /** Recursive helper for the inorder() method.
     *
     * @param node passed in node
     * @param list updated list after using the inorder method
     *
     */
    private void helperInorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            helperInorder(node.getLeft(), list);
            list.add(node.getData());
            helperInorder(node.getRight(), list);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        helperPostorder(root, list);
        return list;
    }

    /** Recursive helper method for the postorder() method.
     *
     * @param node passed in node
     * @param list updated list after using the postorder method
     *
     */
    private void helperPostorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            helperPostorder(node.getLeft(), list);
            helperPostorder(node.getRight(), list);
            list.add(node.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> queued = new LinkedList<>();
        queued.add(root);
        ArrayList<T> arr = new ArrayList<>();
        if (root == null) {
            return arr;
        }
        while (queued.size() > 0) {
            BSTNode<T> result = queued.remove();
            if (result.getLeft() != null) {
                queued.add(result.getLeft());
            }
            if (result.getRight() != null) {
                queued.add(result.getRight());
            }
            arr.add(result.getData());
        }
        return arr;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return helperHeight(root);
    }

    /** Recursive helper method for height() method.
     *
     * @param node the current node
     * @return height
     */
    private int helperHeight(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftNode = helperHeight(node.getLeft());
        int rightNode = helperHeight(node.getRight());
        return Math.max(leftNode, rightNode) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     *
     * This must be done recursively.
     *
     * A good way to start is by finding the deepest common ancestor (DCA) of both data
     * and add it to the list. You will most likely have to split off and
     * traverse the tree for each piece of data adding to the list in such a
     * way that it will return the path in the correct order without requiring any
     * list manipulation later. One way to accomplish this (after adding the DCA
     * to the list) is to then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list.
     *
     * Please note that there is no relationship between the data parameters
     * in that they may not belong to the same branch.
     *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use considering the Big-O efficiency of the list
     * operations.
     *
     * This method only needs to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     *
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     *
     * Ex:
     * Given the following BST composed of Integers
     *              50
     *          /        \
     *        25         75
     *      /    \
     *     12    37
     *    /  \    \
     *   11   15   40
     *  /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new java.lang.IllegalArgumentException("Data 1 or Data 2 is null!");
        }
        List<T> newList = new LinkedList<>();
        if (data1 == data2) {
            newList.add(data1);
        }
        BSTNode<T> dCA = deepestCommonAncestor(root, data1, data2);
        findPathHelper(dCA, data1, newList);
        newList.remove(newList.size() - 1);
        findPathHelper2(dCA, data2, newList);
        return newList;
    }

    /**
     * Recursive helper method to find the Deepest Common Ancestor.
     * @param root the root node
     * @param data1 the first data passed in
     * @param data2 the second data passed in
     * @return a node that is the DCA
     */
    private BSTNode<T> deepestCommonAncestor(BSTNode<T> root, T data1, T data2) {
        if (data1.compareTo(root.getData()) < 0 && data2.compareTo(root.getData()) < 0) {
            root = root.getLeft();
            return deepestCommonAncestor(root, data1, data2);
        } else if (data1.compareTo(root.getData()) > 0 && data2.compareTo(root.getData()) > 0) {
            root = root.getRight();
            return deepestCommonAncestor(root, data1, data2);
        } else {
            return root;
        }
    }

    /**
     * Recursive function to help find the path for Data 1.
     * @param node takes in the DCA node
     * @param data takes in data 1
     * @param newList takes in the current list for path
     * @return a list that has the path
     */
    private List<T> findPathHelper(BSTNode<T> node, T data, List<T> newList) {
        if (node == null || data == null) {
            return newList;
        }
        if (data.compareTo(node.getData()) < 0) {
            newList.add(0, node.getData());
            node = node.getLeft();
            return findPathHelper(node, data, newList);
        } else if (data.compareTo(node.getData()) > 0) {
            newList.add(0, node.getData());
            node = node.getRight();
            return findPathHelper(node, data, newList);
        } else if (data.compareTo(node.getData()) == 0) {
            newList.add(0, node.getData());
        }
        return newList;
    }

    /**
     * Recursive function to help find the path for Data 2.
     * @param node takes in the DCA node
     * @param data takes in data 2
     * @param newList takes in the current list for path
     * @return a list that has the path
     */
    private List<T> findPathHelper2(BSTNode<T> node, T data, List<T> newList) {
        if (node == null || data == null) {
            return newList;
        }
        if (data.compareTo(node.getData()) < 0) {
            newList.add(node.getData());
            node = node.getLeft();
            return findPathHelper2(node, data, newList);
        } else if (data.compareTo(node.getData()) > 0) {
            newList.add(node.getData());
            node = node.getRight();
            return findPathHelper2(node, data, newList);
        } else if (data.compareTo(node.getData()) == 0) {
            newList.add(node.getData());
        }
        return newList;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
