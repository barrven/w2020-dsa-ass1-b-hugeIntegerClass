package main;

public class Main {

    public static void main(String[] args) {
        HugeInteger num1 = new HugeInteger("223");
        HugeInteger num2 = new HugeInteger("123");
//        HugeInteger res = num1.addPositive(num2);
//        System.out.println(res.toString());
        System.out.println(num1.compareTo(num2));



//        HugeInteger test = new HugeInteger();
//
//        for (int i = 0; i < 10; i++){
//            test.addLast(i);
//        }
//
//        System.out.println(test.toString());

    }
}
