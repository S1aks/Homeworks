package com.hw.homework02;

import android.os.Parcel;
import android.os.Parcelable;

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
