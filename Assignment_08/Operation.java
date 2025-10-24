public class Operation {
    private double numOne;
    private double numTwo;
    private String operator;
    private double result;

    public Operation(double numOne, double numTwo, String operator) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.operator = operator;
        this.result = calculate();
    }

    private double calculate() {
        switch (operator) {
            case "+": return numOne + numTwo;
            case "-": return numOne - numTwo;
            case "*": return numOne * numTwo;
            case "/":
                if (numTwo != 0) return numOne / numTwo;
                else {
                    System.err.println("⚠️ Division by zero detected: " + numOne + " / " + numTwo);
                    return Double.NaN;
                }
            default:
                System.err.println("⚠️ Invalid operator: " + operator);
                return Double.NaN;
        }
    }

    // Getters
    public double getNumOne() { return numOne; }
    public double getNumTwo() { return numTwo; }
    public String getOperator() { return operator; }
    public double getResult() { return result; }

    @Override
    public String toString() {
        return numOne + " " + operator + " " + numTwo + " = " + result;
    }
}
