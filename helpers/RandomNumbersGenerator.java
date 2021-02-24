package helpers;

import java.lang.Math;

public class RandomNumbersGenerator {
    public static int[] generate (int arraySize) {
        int[] randomNumbers = new int[arraySize];

        for(int i = 0; i < arraySize; i++) {
            randomNumbers[i] = (int) (Math.random() * (arraySize - 0 + 1) + 0);                
        } 

        return randomNumbers;
    }
}

