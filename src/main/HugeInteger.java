package main;

public class HugeInteger {
    private boolean isPositive;
    private Node head;
    private Node tail;
    private int length;

    public HugeInteger(){
        isPositive = true;
        head = null;
        tail = null;
        length = 0;
    }

    public HugeInteger(String number){
        if (number.charAt(0) == '-'){
            isPositive = false;
            //provide correct starting index for remove function to work
            number = removeLeadingZeros(number, 1);
        }
        else{
            isPositive = true;
            number = removeLeadingZeros(number, 0);
        }
        length = number.length();
        populate(number);
    }

    private String removeLeadingZeros(String str, int start){
        // Count leading zeros
        int i = start;
        while (i < str.length() && str.charAt(i) == '0')
            i++;
        StringBuffer sb = new StringBuffer(str);
        // The  StringBuffer replace i number of zeros
        sb.replace(0, i, "");
        return sb.toString();  // return in String
    }

    private void populate(String number){
        head = new Node(Integer.parseInt(String.valueOf(number.charAt(length-1))));
        Node n, p, c = head;
        for (int i = length-2; i > -1; i--){
            p = c;
            int data = Integer.parseInt(String.valueOf(number.charAt(i)));
            if (i == 0){
                tail = new Node(data);
                tail.prev = c;
                p.next = tail;
            }
            else{
                c = new Node(data);
                c.prev = p;
                p.next = c;
            }
        }
    }

    public void traverseTailFirst(){
        Node current = tail;
        while (current != null){
            System.out.print(current.data);
            current = current.prev;
        }
    }

    public void traverseHeadFirst(){
        Node current = head;
        while (current != null){
            System.out.print(current.data);
            current = current.next;
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        if (!isPositive)
            s.append("-");
        Node c = tail;
        while (c != null){
            s.append(c.data);
            c = c.prev;
        }
        return s.toString();
    }

    public HugeInteger addPositive(HugeInteger num2){
        return null;
    }
}
