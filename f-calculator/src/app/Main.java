package app;

import app.operator.Operator;

public class Main {

    private static final Operator addOperator = (double op1, double op2) -> op1 + op2;

    public static void main(String[] args) {

        double result = addOperator.execute(2.3,5.6);

        // anonyme Klasse
        Operator subtractOperator = new Operator() {
            @Override
            public double execute(double op1, double op2) {
                return op1 - op2;
            }
        };

        // Methoden-Referenz
        Operator multiplyOperator = Main::multiply;
    }

    public static double multiply(double op1, double op2) {

        return op1 * op2;
    }
}