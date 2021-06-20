package main;

// 基本情報 H28 秋季 午後 Java

// [プログラム 1]
import java.util.Stack;
/*public*/ interface Key {
    public void operateOn(Stack<Integer> stack);
}


// [プログラム 2]
//import java.util.Stack;
enum DigitKey Key { // enum DigitKey /* (a) */ key
    DIGIT0, DIGIT1, DIGIT2, DIGIT3, DIGIT4,
    DIGIT5, DIGIT6, DIGIT7, DIGIT8, DIGIT9;
    public void operateOn(Stack<Integer> stack) {
        stack.push(); // stack.push( /* (b) */ * 10 + /* (c) */ );
    }
}


// [プログラム 3]
//import java.util.Stack;
enum OperateionKey Key { // enum OperateionKey /* (a) */ Key
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
        switch() { // switch( /* (d) */ )
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



public class Main {

}

