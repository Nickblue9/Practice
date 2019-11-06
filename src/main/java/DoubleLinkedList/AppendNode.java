package DoubleLinkedList;

import java.util.Scanner;

public class AppendNode {
    public static void main(String[] args){
        Scanner in  = new Scanner(System.in);
        Node head = new Node(in.nextLine());
        Node current = head;
        while(true){
            String s = in.nextLine();
            if(s.compareTo("END") == 0) break;
            else{
                current.next = new Node(s);
                current.next.prev = current;
                current = current.next;
            }
        }
        Node end = current;
        current = end;
        while(current != null){
            System.out.print(current.val +" ");
            current = current.prev;
        }
        System.out.println();
        current = head;
        while(current != null){
            System.out.print(current.val+" ");
            current = current.next;
        }
        System.out.println();
    }
}
