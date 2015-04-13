package Statistics;

import java.util.NoSuchElementException;


public class DoublyLinkedList<E> implements Iterable<E>{
       
        private Node<E> head;
        private Node<E> tail;
        private int size;
       
        //create empty DLL
        public DoublyLinkedList(){
                size = 0;
        }
       
        //returns true if the DLL is empty
        public boolean isEmpty(){
                return size == 0;
        }
       
        //returns current number of nodes in the DLL
        public int size(){
                return size;
        }
       
        //returns first element of DLL
        public E getFirst() {
                if(isEmpty())  {
                        throw new NoSuchElementException("The list is empty");
                } else {
                        return this.head.getElement();
                }
        }
       
        //returns last element of DLL
                public E getLast() {
                        if(isEmpty())  {
                                throw new NoSuchElementException("The list is empty");
                        } else {
                                return this.tail.getElement();
                        }
                }
       
        //adds node with specified element to beginning of list
        public void addFirst(E element){
                //create node with element
                Node<E> newNode = new Node<E>(element, null, null);
               
                if(head == null){
                        head = newNode;
                        tail = head;
                } else {
                        newNode.setNext(head);
                        head.setPrev(newNode);
                        head = newNode;
                }
 
                size++;        
        }
       
        //adds node with specified element to end of list
        public void addLast(E element){
                //create node with element
                Node<E> newNode = new Node<E>(element, null, null);
               
                if (tail == null){
                        tail = newNode;
                        head = tail;
                } else {
                        newNode.setPrev(tail);
                        tail.setNext(newNode);
                        tail = newNode;
                }
               
                size++;
 
        }
       
        //removes first node of list
        //if list is empty throws exception
        public void removeFirst(){
                if(isEmpty()) {
                        throw new NoSuchElementException("The list is empty");
                }
       
                head = head.getNext();
                head.setPrev(null);
                size--;
               
        }
       
        //removes last node of list
        //if list is empty throws exception
        public void removeLast(){
                if(isEmpty()){
                        throw new NoSuchElementException("The list is empty");
                }
               
                tail = tail.getPrev();
                tail.setNext(null);
                size--;
        }
       
       
        public void print(){
                Node<E> temp = head;
               
                while (temp != null){
                        System.out.println(temp.getElement());
                        temp = temp.getNext();
                }
        }
 
        //calls new instance of linked list iterator with current position at the head
        @Override
        public LinkedListIterator<E> iterator() {
                return new LinkedListIterator<E>(head);
        }
       
 
}