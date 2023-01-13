package app.operator;

@FunctionalInterface
public interface Operator {

    double execute(double op1, double op2);
//    void print(); FunctionalInterface darf nur eine zu implementierende Methode haben

}
