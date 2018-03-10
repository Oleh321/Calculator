package balychev.oleh.nk.ua.blch.calculator;


public class Calculator {

    private double mValue;
    private char mOperation;

    public Calculator() {
        reset();
    }

    public double calculate(double next){
        double res = 0;
        switch(mOperation){
            case '+':
                res = mValue + next;
                break;
            case '-':
                res = mValue - next;
                break;
            case '*':
                res = mValue * next;
                break;
            case '/':
                if(next == 0)
                    throw new ArithmeticException();
                res = mValue / next;
                break;
        }
        return res;
    }

    public void setOperation(char operation) {
        this.mOperation = operation;
    }

    public void setValue(double value) {
        this.mValue = value;
    }

    public void reset() {
        mValue = 0;
        mOperation = '+';
    }
}
