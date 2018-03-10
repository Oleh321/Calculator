package balychev.oleh.nk.ua.blch.calculator;

public class Controller {
    private final int MAX_INPUT_LENGTH = 12;

    private StringBuilder mDisplayNumber;
    private State mPreviousState;
    private Calculator mCalculator;

    public Controller() {
        this.mDisplayNumber = new StringBuilder("0");
        mPreviousState = State.EQUALITY;
        mCalculator = new Calculator();
    }

    public String getNumber() {
        // Отображение во время набора чисел
        if (mPreviousState == State.NUMBER)
            return mDisplayNumber.toString();

        if (mPreviousState == State.EXCEPTION)
            return "Деление на 0";

        // Вывод целого числа
        double res = parseNumber();
        if(res == (int) res) {
            return String.format("%1.0f", res);
        }

        if (res >= Math.pow(10, 10)){
            return getDisplayNumberBuFormat("%1.8E");
        }

        // Вывод дробных чисел с предотвращением погрешности
        return getDisplayNumberBuFormat("%1.12f");
    }

    private void addChar(char digit){
        //Условия, при котором число или запятая не будет введено
        if((digit == '.' && mDisplayNumber.toString().indexOf('.') != -1)
                || mDisplayNumber.length() >= MAX_INPUT_LENGTH){
             return;
        }
        if(mDisplayNumber.toString().equals("0")){
            if(digit != '.'){
                mDisplayNumber.delete(0, mDisplayNumber.length());
            }
    }
        mDisplayNumber.append(digit);
    }

    public void clear() {
        mDisplayNumber.delete(0, mDisplayNumber.length());
        mDisplayNumber.append("0");
        mCalculator.reset();
    }

    private String getDisplayNumberBuFormat(String format){
        String value = String.format(format, Double.parseDouble(mDisplayNumber.toString()));
        value = Double.valueOf(value.replace(',', '.')).toString();
        return value;
    }

    private double parseNumber(){
       return Double.parseDouble(mDisplayNumber.toString());
    }

    public void number(char c){
        if(mPreviousState != State.NUMBER) {
            mDisplayNumber = new StringBuilder("0");
            if (mPreviousState == State.EQUALITY)
                mCalculator.reset();
        }
        mPreviousState = State.NUMBER;
        addChar(c);
    }

    public void operation(char c){
        if(mPreviousState == State.NUMBER){
            try {
                double res = mCalculator.calculate(parseNumber());
                mDisplayNumber = new StringBuilder(String.valueOf(res));
                mCalculator.setValue(res);
            } catch (ArithmeticException ex){
                mPreviousState = State.EXCEPTION;
                mCalculator.reset();
               return;
            }
        }
        mPreviousState = State.OPERATION;
        mCalculator.setOperation(c);
    }

    public void equality() {
        if(mPreviousState == State.NUMBER){
            try {
                double result = mCalculator.calculate(parseNumber());
                mDisplayNumber = new StringBuilder(String.valueOf(result));
                mCalculator.setValue(result);
            } catch (ArithmeticException ex){
                mPreviousState = State.EXCEPTION;
                mCalculator.reset();
                return;
            }
            mPreviousState = State.EQUALITY;
        }
    }
}
