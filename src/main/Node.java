package main;

public class Node {
    public int data;
    public Node prev;
    public Node next;

    public Node(int data){
        this.data = data;
        prev = null;
        next = null;
    }
}
