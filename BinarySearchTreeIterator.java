

import java.util.ArrayList;
import java.util.NoSuchElementException;
/*An iterator that iterates through each element of the binary tree. It does this by storing the values in an ArrayList based upon the TraversalType of the iterator.
  It is a generic iterator of type E that will only iterate through objects that can be put in the binary search tree ie-comparable elements.*/
public class BinarySearchTreeIterator<E extends Comparable>{
    /*A variable that is used to determine the type of iteration that is being performed. 
      It is an enum located in the BinarySearchTree class that can have the values of preOrder,postOrder, and inOrder.*/
    private BinarySearchTree.TraversalType traversalType;
    /*This is the ArrayList that stores the items that are going to be iterated through.*/
    private ArrayList<E> listItems;
    /*This is the current index that the iterator is on. It is used to get the item from the above list that is returned by the next method(below)*/
    private int index;
    /*A standard constructor object for a BinarySearchTreeIterator.
      @param: tType is an enum that denotates the type of iteration that will be done to the Arraylist 'items' parameter.*/
    BinarySearchTreeIterator(BinarySearchTree.TraversalType tType,ArrayList<E> items){
        traversalType=tType;   
        listItems=items;
        index=0;
    }   
    /*Method that determines if the current iterator has a next item.
      @return: returns true if the iterator has a next item, else returns false.*/
    public boolean hasNext(){
        return index<listItems.size();
    }
    /*Method that proceeds the iteration to the next item.
      @return: returns the next item of iteration if it exists, else throws an exception.*/
    public Object next() throws NoSuchElementException{
        if(!hasNext())
            throw new NoSuchElementException();     
        E item=listItems.get(index);
        index++;
        return item;
    }
}
