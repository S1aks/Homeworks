package com.hw.homework02;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class Calc implements Parcelable {


    protected Calc(Parcel in) {
        if (in.readByte() == 0) {
            firstNumber = null;
        } else {
            firstNumber = in.readDouble();
        }
        if (in.readByte() == 0) {
            secondNumber = null;
        } else {
            secondNumber = in.readDouble();
        }
        if (in.readByte() == 0) {
            result = null;
        } else {
            result = in.readDouble();
        }
        bufferString = in.readString();
        pointed = in.readByte() != 0;
    }

    public static final Creator<Calc> CREATOR = new Creator<Calc>() {
        @Override
        public Calc createFromParcel(Parcel in) {
            return new Calc(in);
        }

        @Override
        public Calc[] newArray(int size) {
            return new Calc[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (firstNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(firstNumber);
        }
        if (secondNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(secondNumber);
        }
        if (result == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(result);
        }
        dest.writeString(bufferString);
        dest.writeByte((byte) (pointed ? 1 : 0));
    }

    enum Operation {
        NONE,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE}

    private Double firstNumber;
    private Double secondNumber;
    private Operation currentOperation;
    private Double result;
    public String bufferString;
    public boolean pointed;

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

    public String addDigit(String digit) {
        if (getCurrentOperation() == Operation.NONE) {
            if (getFirstNumber() == null && !pointed) {
                if (!digit.equals("0")) {
                    bufferString = digit;
                } else return bufferString;
            } else {
                bufferString += digit;
            }
            setFirstNumber(Double.parseDouble(bufferString));
        } else {
            if (getSecondNumber() == null && !pointed) {
                if (!digit.equals("0")) {
                    bufferString = digit;
                } else return bufferString;
            } else {
                bufferString += digit;
            }
            setSecondNumber(Double.parseDouble(bufferString));
        }
        return bufferString;
    }

    public String addPoint() {
        if (!bufferString.contains(".")) {
            if (getCurrentOperation() == Operation.NONE) {
                if (getFirstNumber() == null) {
                    bufferString = "0.";
                } else {
                    bufferString += ".";
                }
            } else {
                if (getSecondNumber() == null) {
                    bufferString = "0.";
                } else {
                    bufferString += ".";
                }
            }
            pointed = true;
        }
        return bufferString;
    }

    public String calculate() {
        switch (getCurrentOperation()) {
            case NONE:
                break;
            case PLUS:
                setResult(getFirstNumber() + getSecondNumber());
                break;
            case MINUS:
                setResult(getFirstNumber() - getSecondNumber());
                break;
            case MULTIPLY:
                setResult(getFirstNumber() * getSecondNumber());
                break;
            case DIVIDE:
                if (Math.abs(getSecondNumber()) < 0.0000000000001) {
                    return "Error";
                } else {
                    setResult(getFirstNumber() / getSecondNumber());
                }
                break;
        }
        DecimalFormat df = new DecimalFormat("#.#########");
        bufferString = df.format(getResult());
        setFirstNumber(getResult());
        setResult(null);
        return bufferString;
    }
}
