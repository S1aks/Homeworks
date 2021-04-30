package com.hw.homework02;

public class Calc {
    enum Operation {NONE, PLUS, MINUS, MULTIPLY, DIVIDE}
    private Double firstNumber;
    private Double secondNumber;
    private Operation currentOperation;
    private Double result;
    public static String bufferString;
    public static boolean pointed;

    public Calc() {
        this.firstNumber = null;
        this.secondNumber = null;
        this.currentOperation = Operation.NONE;
        this.result = null;
        bufferString = "";
        pointed = false;
    }

    public void clear() {
        this.firstNumber = null;
        this.secondNumber = null;
        this.currentOperation = Operation.NONE;
        this.result = null;
        bufferString = "";
        pointed = false;
    }

    public Double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Operation getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(Operation currentOperation) {
        this.currentOperation = currentOperation;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Double calcResult() {
        if (firstNumber == null || secondNumber == null) {
            return null;
        }
        switch (currentOperation) {
            case PLUS:
                result = firstNumber + secondNumber;
                return result;
            case MINUS:
                result = firstNumber - secondNumber;
                return result;
            case MULTIPLY:
                result = firstNumber * secondNumber;
                return result;
            case DIVIDE:
                result = firstNumber / secondNumber;
                return result;
        }
        return null;
    }
}
