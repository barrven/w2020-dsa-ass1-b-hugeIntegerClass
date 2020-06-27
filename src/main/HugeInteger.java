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
        if (number.length() == 0)
            return;
        if (number.charAt(0) == '-'){
            isPositive = false;
            //provide correct starting index for remove function to work
            number = removeLeadingZeros(number, 1);
        }
        else{
            isPositive = true;
            number = removeLeadingZeros(number, 0);
        }
        //set the length property after the trimming and sign removal
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
        //assume the length is either 1 or > 1 because already checked for zero length string
        //in constructor
        head = new Node(Integer.parseInt(String.valueOf(number.charAt(length-1))));
        if (length > 1){
            Node p, c = head;
            for (int i = length-2; i > -1; i--){
                p = c;
                int data = Integer.parseInt(String.valueOf(number.charAt(i)));
                if (i == 0){ //if i is zero then we are on the last digit so create the tail
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
        else{
            tail = head;
        }
    }

//    public void traverseTailFirst(){
//        Node current = tail;
//        while (current != null){
//            System.out.print(current.data);
//            current = current.prev;
//        }
//    }

//    public void traverseHeadFirst(){
//        Node current = head;
//        while (current != null){
//            System.out.print(current.data);
//            current = current.next;
//        }
//    }

    public String toString(){
        if (length == 0)
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
        int sum;
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
            total.addFront(sum);
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
            total.addFront(sum);
            longest = longest.next;
        }

        if (buffer > 0){
            total.addFront(buffer);
        }

        return total;
    }

    public void addLast(int digit){
        /*
        Adds a digit to the end of the number (at the front of the list).
        Note: if the list is empty leading zeros should not be added.
         */
        if (length == 0){
            if (digit != 0){
                head = new Node(digit);
                tail = head;
                length = 1;
            }
            return;
        }
        if (length == 1){
            head = new Node(digit);
            head.next = tail;
            tail.prev = head;
            length++;
            return;
        }

        Node temp = head;
        head = new Node(digit);
        head.next = temp;
        temp.prev = head;
        length++;


    }

    public void addFront(int digit){
        /*
        Adds a digit to the front of the number (at the end of the list).
        This can be used in the addPositive method
         */
        if (length == 0){
            tail = new Node(digit);
            head = tail;
            length++;
            return;
        }
        //cover case where need to create new node for tail
        if (length == 1){
            tail = new Node(digit);
            head.next = tail;
            tail.prev = head;
            length++;
            return;
        }
        Node temp = tail;
        tail = new Node(digit);
        tail.prev = temp;
        temp.next = tail;
        length++;
    }

    public int compareTo(HugeInteger num2){
        /*
        Returns -1 if the number stored is less than num2
        Returns 0 if the number stored is equal to num2
        Returns 1 if the number stored is greater than num2
         */
        //if both numbers have the same sign and the same number of digits
        if (isPositive == num2.isPositive && length == num2.length){
            //loop through number from left to right and find the difference
            Node list_a = tail;
            Node list_b = num2.tail;
            int difference;
            do {
                difference = list_a.data - list_b.data;
                list_a = list_a.prev;
                list_b = list_b.prev;
            } while (list_a != null && difference == 0);

            //returns 1 if difference is > 0, -1 if < 0, and 0 if 0
            return Integer.compare(difference, 0);

        }
        else{
            if (isPositive && !num2.isPositive){
                return 1;
            }
            if (!isPositive && num2.isPositive){
                return -1;
            }
            //returns 1 if length > num2length, 0 if they are the same, and -1 otherwise
            return Integer.compare(length, num2.length);
        }

    }
}
