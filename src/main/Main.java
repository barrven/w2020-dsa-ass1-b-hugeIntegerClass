package main;

public class Main {

    public static void main(String[] args) {
        HugeInteger num1 = new HugeInteger("999");
        HugeInteger num2 = new HugeInteger("999999");
        HugeInteger res = num1.addPositive(num2);
        System.out.println(res.toString());


//        HugeInteger test = new HugeInteger();
//
//        for (int i = 0; i < 10; i++){
//            test.addLast(0);
//        }
//
//        System.out.println(test.toString());

    }
}
