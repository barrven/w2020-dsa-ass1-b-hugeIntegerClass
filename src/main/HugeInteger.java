package main;

public class HugeInteger {
    public boolean isPositive;
    public Node head;
    public Node tail;
    public int length;

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
        StringBuilder sb = new StringBuilder(str);
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
        if (head == null)
            return "0";
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
        /*
        Returns a new HugeInteger containing the result of adding num2 to the
        stored number. You MUST assume num2 and the number being added to are
        BOTH positive.
         */

        //find the shortest list
        Node shortest;
        Node longest;
        if (num2.length < length){
            shortest = num2.head;
            longest = head;
        }
        else {
            shortest = head;
            longest = num2.head;
        }
        //traverse through the shortest list starting at head
        int buffer = 0; // buffer is used to hold carry values
        int sum = 0;
        HugeInteger total = new HugeInteger();
        while (shortest != null){
            sum = shortest.data + longest.data + buffer;
            //determine if carrying needs to happen
            if (sum > 9){
                buffer = 1;
                sum -= 10;
            }
            else{
                buffer = 0; //clear buffer if no carrying
            }

            //add the result to the return object
            System.out.println(sum);
            total.concatenate(sum);
            shortest = shortest.next;
            longest = longest.next;
        }
        while (longest != null){
            sum = longest.data + buffer;
            if (sum > 9){
                buffer = 1;
                sum -= 10;
            }
            else{
                buffer = 0; //clear buffer if no carrying
            }
            System.out.println(sum);
            total.concatenate(sum);
            longest = longest.next;
        }
        if (buffer > 0)
            total.concatenate(buffer);

        return total;
    }

    public void addLast(int digit){
        /*
        Adds a digit to the end of the number (at the front of the list).
        Note: if the list is empty leading zeros should not be added.
         */

        //list is empty
        if (head == null && digit != 0){
            head = new Node(digit);
            tail = head;
            return;
        }
        //cover case where head == tail and need to create tail
        if (head == tail){
            head = new Node(digit);
            head.next = tail;
            tail.prev = head;
            return;
        }
        Node temp = head;
        head = new Node(digit);
        head.next = temp;
        temp.prev = head;
    }

    public void concatenate(int digit){
        /*
        Adds a digit to the front of the number (at the end of the list).
        This can be used in the addPositive method
         */
        if (tail == null){
            tail = new Node(digit);
            head = tail;
            return;
        }
        //cover case where head == tail and need to create tail
        if (head == tail){
            tail = new Node(digit);
            head.next = tail;
            tail.prev = head;
            return;
        }
        Node temp = tail;
        tail = new Node(digit);
        tail.prev = temp;
        temp.next = tail;
    }

    public int compareTo(HugeInteger num2){
        /*
        Returns -1 if the number stored is less than num2
        Returns 0 if the number stored is equal to num2
        Returns 1 if the number stored is greater than num2
         */
        return 0;
    }
}
