

import java.util.NoSuchElementException;
import java.util.ArrayList;
/*A binary search tree class that stores generic comparable objects in a tree. 
  This a a wrapper class for the BSTNode class, meaning it performs most operations through the BSTNode class and 'hides' it from the user.*/
public class BinarySearchTree<E extends Comparable>{
    /*The root/top node of the binary search tree.*/
    private BSTNode root;
    /*The height of the deepest BSTNode in the binary tree.*/
    private int height;
    /*An enum that is used to denotate what kind of traversal the BinarySearchTreeIterator will be doing. Types include inOrder,postOrder,preOrder.*/
    public enum TraversalType{
        inOrder,postOrder,preOrder;
    }
    /*Standard constructor for a binary seach tree.*/
    public BinarySearchTree(){
        height=0;
    }
    /*Method that adds a new value to the binary search tree through the root node. Calls the add method in BSTNode class that adds the value to the tree.
      @param:'val' is a generic comparable object that will be inserted into the tree via the root.*/
    public void add(E val){
        if(root==null){                                                         /*If the tree is empty.*/
            root=new BSTNode(null,val);
            height=1;
        }else{                                                                  /*If the tree is not empty.*/
            root.add(val);
            int possHeight=root.getDepth();
            if(possHeight>height){                                              /*If the added item increased the depth of the tree.*/
               height=possHeight;
            }
        }
    }
    /*Method that removes an item from a tree, if it exists in the tree.
      @param:'val' is the value of the item that is supposed to be removed by the BSTNode remove method.
      @return:returns the item if it was found then removed, else returns a null value.
      Throws an exception if the tree is empty.*/
    public Comparable<E> remove(E val) throws NoSuchElementException{
        if(root==null)
            throw new NoSuchElementException();
        else
           return root.remove(val);
    }
    /*@return:Returns the depth of the binary search tree.*/
    public int getHeight(){
        return height;
    }
    /*Wrapper method for BSTNode count method.
      @param:'val' is the item that is going to be counted in the binary search tree.
      @return: returns the number of times val was found in the binary search tree.*/
    public int count(E val){
        return root.count(val);
    }
    /*Wrapper class that returns the BSTNode toString method return value. Overrides toString method in Object superclass.
      @return: returns the value returned by the BSTNode toString method.*/
    @Override
    public String toString(){
        return root.getMLR();
    }
    /*Wrapper method for the toArrayIO in the BSTNode class.
      @return:returns the binary search tree as an Arraylist in order from least to greatest.*/
    public ArrayList<E> ArrayIO(){
        return root.toArrayIO();
    }
    /*Wrapper method for the toArrayPost in the BSTNode class.
      @return:returns the binary search tree as an ArrayList in post-order format.*/
    public ArrayList<E> ArrayPost(){
        return root.toArrayPost();
    }
    /*Wrapper method for the toArrayPre in the BSTNode class.
      @return:returns the binary search tree as an ArrayList in pre-order format.*/
    public ArrayList<E> ArrayPre(){
        return root.toArrayPre();
    }    
    /*Wrapper method foe the private method rebalanceHelp(). Rebalances the tree binary search tree optimally.*/
    public void rebalance(){
        ArrayList<E> list=root.toArrayIO();
        rebalanceHelp(list,0,list.size());
    }/*Method that rebalances the binary serach tree. Creates a new root and then recursively adds values to it.
       @param:'list' is the list of all the objects in the binary search tree in order,start is the left endpoint,while end is the right endpoint.
       the endpoints defines the order the items are placed into the binary search tree.*/
    private void rebalanceHelp(ArrayList<E> list,int start,int end){
        int mid=(start+end)/2;                                                  
        if(start==0 && end==list.size()){                                       /*Creates a new root on the first call of the method*/
            root=new BSTNode(null,list.get(mid));
            rebalanceHelp(list,start,mid);
            rebalanceHelp(list,mid,end);
            root.add(list.get(0));
        }else if(start+1!=end){                                                 /*The standard case that adds the items to the new binary search tree.*/
            root.add(list.get(mid));
            rebalanceHelp(list,start,mid);
            rebalanceHelp(list,mid,end);
        }
    }
    /*@param:'type' is an enum that is used to determine the method of iteration.
      @return:returns an iterator based on what type of iteration is input into the method via the 'type' parameter.*/
    public BinarySearchTreeIterator iterator(TraversalType type){
        if(type==TraversalType.inOrder){
            return new BinarySearchTreeIterator(type,root.toArrayIO());
        }else if(type==TraversalType.preOrder){
            return new BinarySearchTreeIterator(type,root.toArrayPre());
        }else{
            return new BinarySearchTreeIterator(type,root.toArrayPost());
        }      
    }
}
