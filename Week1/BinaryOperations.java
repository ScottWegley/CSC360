package Week1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BinaryOperations {

    private static int sum(int a, int b) {
        return a + b;
    }

    private static int product(int a, int b) {
        return a * b;
    }

    private static int difference(int a, int b) {
        return a - b;
    }

    private static int quotient(int a, int b) {
        return a / b;
    }

    private static int and(int a, int b){
        return a & b;
    }

    private static int inclOr(int a, int b){
        return a | b;
    }

    private static int exclOr(int a, int b){
        return a ^ b;
    }

    private static int lShift(int a, int b){
        return a << b;
    }

    private static int signRShift(int a, int b){
        return a >> b;
    }

    private static int unsignRShift(int a, int b){
        return a >>> b;
    }

    private static int complement(int a){
        return ~a;
    }
    
}