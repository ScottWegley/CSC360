package Week1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BinaryOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Input the first integer.");
            int operandA = scanner.nextInt();
            System.out.println("Input the second integer.");
            int operandB = scanner.nextInt();
            System.out.println("Input the operator. (+,-,*,/,<<,>>,>>>,&,|,~,^)");
            scanner.nextLine();
            String operator = scanner.nextLine();
            switch (operator) {
                case "+":
                    System.out.println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                            + int2binary(sum(operandA, operandB),8));
                    break;
                case "-":
                    System.out.println(
                            int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                                    + int2binary(difference(operandA, operandB),8));
                    break;
                case "*":
                    System.out
                            .println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                                    + int2binary(product(operandA, operandB),8));
                    break;
                case "/":
                    System.out
                            .println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                                    + int2binary(quotient(operandA, operandB),8));
                    break;
                case "<<":
                    System.out.println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                            + int2binary(lShift(operandA, operandB),8));
                    break;
                case ">>":
                    System.out.println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                            + int2binary(signRShift(operandA, operandB),8));
                    break;
                case ">>>":
                    System.out.println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                            + int2binary(unsignRShift(operandA, operandB),8));
                    break;
                case "&":
                    System.out.println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                            + int2binary(and(operandA, operandB),8));
                    break;
                case "|":
                    System.out.println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                            + int2binary(inclOr(operandA, operandB),8));
                    break;
                case "^":
                    System.out.println(int2binary(operandA,8) + " " + operator + " " + int2binary(operandB,8) + " = "
                            + int2binary(exclOr(operandA, operandB),8));
                    break;
                case "~":
                    System.out.println(operator + "" + int2binary(operandA,8) + " = "
                            + int2binary(complement(operandA),8));
                            break;
                default:
                    throw new IllegalArgumentException("Your operator was invalid");
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

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

    private static int and(int a, int b) {
        return a & b;
    }

    private static int inclOr(int a, int b) {
        return a | b;
    }

    private static int exclOr(int a, int b) {
        return a ^ b;
    }

    private static int lShift(int a, int b) {
        return a << b;
    }

    private static int signRShift(int a, int b) {
        return a >> b;
    }

    private static int unsignRShift(int a, int b) {
        return a >>> b;
    }

    private static int complement(int a) {
        return ~a;
    }

    private static String int2binary(int a, int bits) {
        int q = a;
        int r = 0;
        String result = "";
        for (int i = 0; i < bits; i++) {
            r = q & 0x01;
            result = r + result;
            q = q >>> 1;
        }
        return result;
    }
}