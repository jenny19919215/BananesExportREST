package com.mkyong.error;

public class DaysDiffException extends RuntimeException {

    public DaysDiffException() {
        super("Days diff should not be less than 7 days.");
    }

}
