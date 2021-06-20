package main;
import java.util.HashMap;
import java.util.Map;

// 基本情報 H28 秋季 午後 Java

// [プログラム 1]
import java.util.Stack;

/*public*/ interface Key {
    public void operateOn(Stack<Integer> stack);
}


// [プログラム 2]
//import java.util.Stack;
enum DigitKey implements Key { // enum DigitKey /* (a) */ key
    DIGIT0, DIGIT1, DIGIT2, DIGIT3, DIGIT4,
    DIGIT5, DIGIT6, DIGIT7, DIGIT8, DIGIT9;
    public void operateOn(Stack<Integer> stack) {
        stack.push(stack.pop() * 10 + ordinal()); // stack.push( /* (b) */ * 10 + /* (c) */ );
    }
}


// [プログラム 3]
//import java.util.Stack;
enum OperateionKey implements Key { // enum OperateionKey /* (a) */ Key
    ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUAL, CLEAR;

    public void operateOn(Stack<Integer> stack) {
        if (this == EQUAL || this == CLEAR) {
            return;
        }
        int val2 = stack.pop();
        int val1 = stack.pop();
        stack.push(calculate(val1, val2));
    }

    private int calculate(int val1, int val2) {
        switch(this) { // switch( /* (d) */ )
            case ADD:
                return val1 + val2;
            case SUBTRACT:
                return val1 - val2;
            case MULTIPLY:
                return val1 * val2;
            case DIVIDE:
                return val1 / val2;
            default:
                throw new AssertionError(toString());
        }
    }
}


// [プログラム 4]
//import java.util.Stack;
/* public */ class Calculator {
    private final Stack<Integer> stack = new Stack<Integer>();

    private Key pendingKey;

    public Calculator() {
        stack.push(0);
    }

    public void onKeyPressed(Key key) {
        System.out.println(key);
        if (key instanceof DigitKey) {
            if (pendingKey == OperateionKey.EQUAL) {
                reset();
            }
            key.operateOn(stack);
            System.out.println(stack.peek());
        } else {
            try {
                if (pendingKey != null) {
                    pendingKey.operateOn(stack);
                }
                System.out.println(stack.peek());
                pendingKey = key;
                if (key != OperateionKey.EQUAL) {
                    stack.push(0);

                }
            } catch (ArithmeticException e) {
                System.out.println("Eror");
                reset();
            }
        }
    }

    private void reset() {
        stack.clear();
        stack.push(0);
        pendingKey = null;
    }
}


// [プログラム 5]
//import java.util.HashMap; // 先頭に記述
//import java.util.Map; // 先頭に記述

/* public */ class CalculatorTest {
    public static void main(String args[]) {
        Map<Character, Key> map = new HashMap<Character, Key>(); // Map<Character, /* (e) */> map = new HashMap<Character, /* (e) */>();
        // 文字と列挙OperationKeyの定数の対応をmapに格納する
        for (OperateionKey key : OperateionKey.values())
            map.put("+-*/=C".charAt(key.ordinal()), key);
        // 数字と列挙DigitKeyの定数の対応をmapに格納する
        for (DigitKey key : DigitKey.values())
            map.put("0123456789".charAt(key.ordinal()), key);

        Calculator calc = new Calculator();
        String chars = args[0];
        // charsの各文字をキーの定数に変換し、メソッドonLeyPressedを呼び出す
        for (int i = 0; i < chars.length(); i++) {
            calc.onKeyPressed(map.get(chars.charAt(i)));
        }
    }
}


public class Main {

}

