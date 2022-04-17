package com.mfk.demo.matcher.input;

/**
 * Defines contract to read input data.Input data includes
 * data about buyer csv file path, supplier csv file path,
 * threshold details and no of columns in each csv file.
 */
public interface IInputHandler {

    /**
     * Reads and returns input data.
     * @return {@link InputData}
     */
    public InputData getInput();
}
