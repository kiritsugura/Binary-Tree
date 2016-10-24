

import java.util.ArrayList;
/*Class that contains the main functionality of a binary search tree. Mainly uses recursion in order to preform operations.
  The elements of the binary search tree are generic comparable objects.*/
class BSTNode<E extends Comparable>{
    /*The left child,right child, and parent node of the current node respectively.*/
    private BSTNode leftNode,rightNode,parentNode;
    /*The current value that is stored in this node.*/
    private E currentNode;
    /*The current height of this node object.*/
    private int currentHeight;
    /*The deepest height in the tree.*/
    private static int depth;
    /*Constructor for a BSTNode. Only called outside of the class in order to make the root node for the binary search tree.
      @param:'parent' is the parent node of this node, and 'current' is the generic comparable object that the node will contain.*/
    public BSTNode(BSTNode parent,E current){
        parentNode=parent;
        currentNode=current;
        if(parent==null){                                                       /*If the current node is the root.*/
            currentHeight=1;
            depth=1;
        }else{                                                                  /*If the current node is not the root.*/
            this.setHeight(parentNode.getHeight()+1);
        }
    }
    /*Method that recursively adds items to the binary search tree.
      @param:val is the value that is going to be added to the binary search tree.*/
    public void add(E val){
        if(currentNode.compareTo(val)>0){                                       /*If 'val' is less than the current node's currentNode value.*/
            if(leftNode==null)                                                  /*If the node has no left children.*/
                leftNode=new BSTNode(this,val);
            else                                                                /*If the node has a left child.*/
                leftNode.add(val);          
        }else{                                                                  /*If 'val' is greater than or equal to the current node's currentNode value.*/
            if(rightNode==null)                                                 /*If the node has no right child.*/
                rightNode=new BSTNode(this,val);
            else                                                                /*If the node has a right child.*/
                rightNode.add(val);
        }
    }  
    /*@Return: returns the deepest height of the binary search tree.*/
    public int getDepth(){
       return depth; 
    }
    /*Pre-order print method that is the standard 'toString' method for a Binary search tree.*/
    public String getMLR(){
        String total="";
        total+=getSpaces()+"["+currentNode+"]\n";
        if(leftNode!=null)
            total+=leftNode.getMLR();
        if(rightNode!=null)
            total+=rightNode.getMLR();
        return total;
    }
    /*Method that changes the depth if the added node increased it. Utility method only invokable in this class.*/
    private void setHeight(int hei){
        currentHeight=hei;
        if(currentHeight>depth)
            depth=currentHeight;
    }
    /*@return:returns the height of the current node in the binary search tree.*/
    public int getHeight(){
        return currentHeight;
    }
    /*Method that counts the number of times an item is the binary search tree.
      @param:'val' is the item that is going to be counted.
      @return: returns the number of times the item was found in the binary search tree.*/
    public int count(E val){
        int count=0;
        if(currentNode==val)                                                    /*If the value is found in the tree.*/
            count++;
        if(leftNode!=null && currentNode.compareTo(val)>0)                      /*If the current node has a left child and val is less than the currentNode value.*/
            count+=leftNode.count(val);
        if(rightNode!=null && currentNode.compareTo(val)<=0)                    /*If the current node has a right child and val is greater than the currentNode value.*/
            count+=rightNode.count(val);
        return count;
    }  
    /*Helper method for the getMLR method(listed above).
      @return: returns a String with the number of spaces equivalent to the height of the current node times two.*/
    private String getSpaces(){
        String spaces="";
        for(int i=1;i<currentHeight;i++)
            spaces+="  ";       
        return spaces;
    }
    /*@return: returns a boolean value of true if the current node has no left or right children(a leaf node), else returns false.*/
    public boolean isLeaf(){
        return leftNode==null && rightNode==null; 
    }
    /*@return: returns an ArrayList of the binary tree in the in-order format.*/
    public ArrayList<E> toArrayIO(){
        ArrayList<E> list=new ArrayList();
        if(leftNode!=null)                                                      /*If the current node has a left child.*/
            list.addAll(leftNode.toArrayIO());    
        list.add(currentNode);
        if(rightNode!=null)                                                     /*If the current node has a right child.*/
            list.addAll(rightNode.toArrayIO());
        return list;
    }
    /*@return: returns an Arraylist of the binary tree in the pre-order format.*/
    public ArrayList<E> toArrayPre(){
        ArrayList<E> list=new ArrayList();
        list.add(currentNode);      
        if(leftNode!=null)                                                      /*If the current node has a left child.*/        
            list.addAll(leftNode.toArrayPre());
        if(rightNode!=null)                                                     /*If the current node has a right child.*/
            list.addAll(rightNode.toArrayPre());
        return list;
    }
    /*@return: returns an ArrayList of the binary tree in the post-order format.*/
    public ArrayList<E> toArrayPost(){
        ArrayList<E> list=new ArrayList();
        if(leftNode!=null)                                                      /*If the current node has a left child.*/
            list.addAll(leftNode.toArrayPost());
        if(rightNode!=null)                                                     /*If the current node has a right child.*/
            list.addAll(rightNode.toArrayPost());
        list.add(currentNode);
        return list;
    } 
    /*@returns an int that is the deepest height of the current subtree with the current node as the root.*/
    private int getLargerDepth(){
        int val=0;
        if(rightNode==null && leftNode==null)                                   /*If the node is a leaf node.*/
            return currentHeight;      
        if(leftNode!=null && leftNode.getLargerDepth()>val)                     /*If the left node subtree has a deeper value.*/
            val=leftNode.getLargerDepth();
        else if(rightNode!=null && rightNode.getLargerDepth()>val)              /*If the right node subtree has a deeper value.*/
            val=rightNode.getLargerDepth();
        return val;
    }
    /*@return: returns the smallest BSTNode in the current subtree based on the currentNode value.*/
    private BSTNode getSmallest(){
        if(leftNode!=null)                                                      /*If this node has a left node.*/
            return leftNode.getSmallest();
        return this;
    }
    /*@return: returns the largest BSTNode in the current subtree based on the currentNode value.*/
    private BSTNode getLargest(){
        if(rightNode!=null)
            return rightNode.getLargest();
        return this;
    }
    /*Method that recursively removes an item from the binary search tree, then adjusts the tree if the remove disrupts the balance.
      @param:'val' is the value that is being searched for in order to remove it from the tree. If multiple values exist, it removes the first instance only.
      @return: returns the value that was removed from the binary search tree.*/
    public Comparable remove(E val){
        E temp=null;
        if(currentNode.compareTo(val)>0){                                       /*If the value has not been found and it is greater than the currentNode value of this node.*/
            leftNode.remove(val);
        }else if(currentNode.compareTo(val)<0){                                 /*If the value has not been found and it is less than the currentNode value of this node.*/    
            rightNode.remove(val);
        }else{                                                                  /*If the value has been found.*/
            if(leftNode==null && rightNode==null){                              /*If the value to be removed is a leaf node.*/
                if(parentNode.leftNode==this)                                   /*If the value to be removed is the left child of its parent*/
                    parentNode.leftNode=null;
                else                                                            /*If the value to be removed is the right child of its parent.*/
                    parentNode.rightNode=null;
                return currentNode;
            }
            else if(leftNode!=null && rightNode!=null){                         /*If the value to be removed is an interior node with two children.*/
                BSTNode toBeRemoved=null;
                if(leftNode.getLargerDepth()>rightNode.getLargerDepth())        /*If the removed item's left child's subtree depth is deeper than its right subtree's*/
                    toBeRemoved=leftNode.getLargest();
                else                                                            /*If the removed item's right child's subtree depth is deeper than its left subtree's*/    
                    toBeRemoved=rightNode.getSmallest();    
                temp=currentNode;
                currentNode=(E)toBeRemoved.currentNode;
                toBeRemoved.currentNode=temp;    
                if(toBeRemoved.isLeaf()){                                       /*If the substitute for the item to be removed is a leaf node.*/
                    if(toBeRemoved.parentNode.leftNode==toBeRemoved)            /*If the substitute for the removed item is its parent's left child.*/
                        toBeRemoved.parentNode.leftNode=null;
                    else                                                        /*If the substitute for the removed item is its parent's right child.*/
                        toBeRemoved.parentNode.rightNode=null;
                }else{                                                          /*If the substitute for the item to be removed is its parent's left child.*/
                    if(toBeRemoved.leftNode==null && toBeRemoved.parentNode.leftNode==toBeRemoved){          /*If the substitute for the item to be removed has no left child and is its parent's left child.*/
                        toBeRemoved.parentNode.leftNode=toBeRemoved.rightNode;
                        toBeRemoved.rightNode.parentNode=toBeRemoved.parentNode;
                    }else if(toBeRemoved.rightNode==null && toBeRemoved.parentNode.leftNode==toBeRemoved){   /*If the substitute for the item to be removed has no right child and is its parent'a left child.*/
                        toBeRemoved.parentNode.leftNode=toBeRemoved.leftNode;
                        toBeRemoved.leftNode.parentNode=toBeRemoved.parentNode;                       
                    }else if(toBeRemoved.leftNode==null && toBeRemoved.parentNode.rightNode==toBeRemoved){   /*If the substitute for the item to be removed has no left child and is its parent's right child.*/
                        toBeRemoved.parentNode.rightNode=toBeRemoved.rightNode;
                        toBeRemoved.rightNode.parentNode=toBeRemoved.parentNode;
                    }else{                                                                                   /*If the substitute for the item to be removed has no left child and is its parent's right child.*/
                        toBeRemoved.parentNode.rightNode=toBeRemoved.rightNode;
                        toBeRemoved.leftNode.parentNode=toBeRemoved.parentNode;
                    }
                }
            }else if(rightNode!=null){                                          /*If the value to be removed has only a left child.*/
                if(parentNode.leftNode==this)                                   /*If the value to be removed is the left child of its parent node.*/
                    parentNode.leftNode=leftNode;
                else                                                            /*If the value to be removed is the right child of its parent.*/
                    parentNode.rightNode=leftNode;
                temp=currentNode;
            }else{                                                              /*If the value to be remove has only a right child.*/
                if(parentNode.leftNode==this)                                   /*If the value to be removed is the left child of its parent node.*/    
                    parentNode.leftNode=rightNode;
                else                                                            /*If the value to be removed is the right child of its parent node.*/
                    parentNode.rightNode=rightNode;                                
                temp=currentNode;
            }
        }
        return temp;
    }
}
