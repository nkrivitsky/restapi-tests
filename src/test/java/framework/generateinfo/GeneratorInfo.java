package framework.generateinfo;

import com.github.javafaker.Faker;

import java.util.Random;

public class GeneratorInfo {

    private GeneratorInfo() {
    }

    public static String generateRandomCountryName() {
        return new Faker().cityPrefix();
    }

    public static String generateRandomShortCountryName() {
        return new Faker().citySuffix();
    }

    public static int generateRandomInt() {
        return new Random().nextInt(10000);
    }

    public static int generateRandomInt(int value) {
        return new Random().nextInt(value);
    }

    public static double generateRandomCoordinate() {
        return new Random().nextInt(179) + ((double) new Random().nextInt(99)) / (new Random().nextInt(99) + 1);
    }
}
