package Statistics;

public class Node<E> {
	E element;  //value Node will store
    Node<E> prev;
    Node<E> next;
           
    //default constructor for Node
    public Node(E element, Node<E> prev, Node<E>next){
            this.element = element;
            this.prev = prev;
            this.next = next;
    }
                   
    //getters and setters for Node variables
    public E getElement(){
            return element;
    }
                           
    public void setElement(E newElement){
            this.element = newElement;
    }
                           
    public Node<E> getPrev(){
            return prev;
    }
                           
    public void setPrev(Node<E> newPrev){
            this.prev = newPrev;
    }
                           
    public Node<E> getNext(){
            return next;
    }
                           
    public void setNext(Node<E> newNext){
            this.next = newNext;
    }                      
}
