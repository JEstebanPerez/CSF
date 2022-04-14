package com.pastillasCreator.pill_box.exceptions;

public class CreatorException extends RuntimeException{
    public static final String EMPTY_VALUE="[ERROR] the field %s is empty";
    public static final String NOT_VALID_VALUE = "[ERROR] you have %s quantity than available.";

    public CreatorException(String msg, String element){
        super(String.format(msg,element));
    }

}
