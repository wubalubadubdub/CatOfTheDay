package com.example.bearg.catoftheday;

import java.util.Random;

/**
 * Created by bearg on 5/3/2016.
 */
public class RandomCat {

    private static String DRAWABLE_NAME;

    public static int getOutcome() {

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2); // will be 0 or 1
        return randomInt;

    }


}
