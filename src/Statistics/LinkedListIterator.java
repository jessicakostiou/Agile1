package Statistics;

import java.util.ListIterator;

public class LinkedListIterator<E> implements ListIterator<E> {
 
        Node<E> previous;
        Node<E> current;
       
        //default constructor with current node
        public LinkedListIterator (Node<E> current) {
                this.current = current;
        }
       
        public E getCurrentElement() {
                return this.current.getElement();
        }
       
        //returns true if the node after current is not null
        @Override
        public boolean hasNext() {
                return current.getNext()!=null;
        }
 
        //returns element of node after current
        //returns null if current does not have next
        @Override
        public E next() {
                if(!hasNext()) {
                        return null;
                }
                current = current.getNext();
                return current.getElement();
        }
 
        //returns true when node before current is not null
        @Override
        public boolean hasPrevious() {
                return current.getPrev()!=null;
        }
 
        //returns element of node before current
        //returns null if current does not have previous
        @Override
        public E previous() {
                if(!hasPrevious()){
                        return null;
                }
               
                current = current.getPrev();
                return current.getElement();
        }
 
        @Override
        public int nextIndex() {
                // TODO Auto-generated method stub
                return 0;
        }
 
        @Override
        public int previousIndex() {
                // TODO Auto-generated method stub
                return 0;
        }
 
        @Override
        public void remove() {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public void set(E e) {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public void add(E e) {
                // TODO Auto-generated method stub
               
        }
 
}