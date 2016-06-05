package mypi.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import mypi.pojo.Events;

/**
 *
 * @author trav custom linked list that holds Events.java
 *
 */
public class EventLinkedList implements Iterable<Events>, Serializable {

    private class Node implements Serializable {

        Events event;
        Node next;

        public Node(Events event) {
            this(event, null);
        }

        public Node(Events event, Node next) {
            this.event = event;
            this.next = next;
        }
    }//end node class

    /**
     * Allows enhacned for loop to be used on this List
     *
     * @return
     */
    @Override
    public Iterator<Events> iterator() {
        if (start == null) {
            return Collections.<Events>emptyList().iterator();
        }
        return new Iterator<Events>() {
            private Node currentNode = start;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Events next() {
                if (currentNode == null) {

                }
                Events toReturn = currentNode.event;
                currentNode = currentNode.next;
                return toReturn;
            }

        };
    }

    private Node start;

    public EventLinkedList() {
        start = null;
    }

    /**
     * removes specified event object by its toString
     *
     * @param eventToString
     */
    public void remove(String eventToString) {
        Node currentNode = start;
        if (currentNode.event.toString().equals(eventToString)) {
            start = start.next;
            return;
        }
        Node previousNode = null;

        while (currentNode != null && !currentNode.event.toString().equals(eventToString)) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }//ends while when currentNode equals eventToString 

        if (currentNode == null) {//no node was found
            return;
        }
        //erasing the event in the list
        previousNode.next = currentNode.next;
    }

    /**
     *
     * method will keep event object sorted during the add process
     *
     * @param event
     *
     *
     */
    public void add(Events event) {
        if (start == null) {
            start = new Node(event);
        } else if (event.compareTo(start.event) <= -1) {//seeing if new event is before the current sorted event
            start = new Node(event, start);

        } else {
            Node nodePointer = start;
            //finding the location where @param event fits in sorted order
            while (nodePointer.next != null && event.compareTo(nodePointer.next.event) > 0) {
                nodePointer = nodePointer.next;
            }//end while   
            nodePointer.next = new Node(event, nodePointer.next);
        }
    }

    public String toString() {
        StringBuilder formListString = new StringBuilder();
        if (start == null) {
            return "List is empty";
        } else {
            Node currentNode = start;
            while (currentNode != null) {
                formListString.append(currentNode.event.toString());
                currentNode = currentNode.next;
                if (currentNode != null) {//handles the last element in the list
                    formListString.append(", ");
                }
            }

        }
        return formListString.toString();
    }

}//end event linked list
