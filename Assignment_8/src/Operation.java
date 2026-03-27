public class Operation {

    private double numOne;
    private double numTwo;
    private String operator;
    private double result;

    public Operation(double numOne, double numTwo, String operator) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.operator = operator;
    }

    public void calculate() {
        switch (operator) {
            case "+":
                result = numOne + numTwo;
                break;
            case "-":
                result = numOne - numTwo;
                break;
            case "*":
                result = numOne * numTwo;
                break;
            case "/":
                if (numTwo != 0)
                    result = numOne / numTwo;
                else
                    throw new ArithmeticException("Division by zero");
                break;
        }
    }
    public double getResult() {
        return result;
    }
    public String toJson() {
        return "{ \"numOne\": " + numOne +
                ", \"numTwo\": " + numTwo +
                ", \"operator\": \"" + operator +
                "\", \"result\": " + result + " }";
    }

    @Override
    public String toString() {
        return numOne + " " + operator + " " + numTwo + " = " + result;
    }
}