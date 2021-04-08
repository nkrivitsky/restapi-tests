package framework.generateinfo;

import com.github.javafaker.Faker;
import models.Category;
import models.Pet;
import models.Tag;
import models.enums.PetStatusEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorInfo {
    private static Faker faker = new Faker();

    private GeneratorInfo() {
    }

    public static String generateRandomCountryName() {
        return faker.cityPrefix();
    }

    public static String generateRandomShortCountryName() {
        return faker.citySuffix();
    }

    public static int randomInt() {
        return new Random().nextInt(10000);
    }

    public static long randomID() {
        return (long) new Random().nextInt(10000000);
    }

    public static int randomInt(int value) {
        return new Random().nextInt(value);
    }

    public static String petName() {
        return faker.firstName();
    }

    public static Category newCategory() {
        return Category.builder().id(randomID()).name(faker.words(1).get(0)).build();
    }

    public static List<Tag> newTagList() {
        List<Tag> tagList = new ArrayList<>();
        int count = randomInt(5);
        for (int i = 0; i < count; i++) {
            tagList.add(Tag.builder().id(randomID()).name(faker.words(1).get(0)).build());
        }

        return tagList;
    }

    public static Pet generateNewPet() {
        return Pet.builder()
                .name(petName())
                .category(newCategory())
                .status(PetStatusEnum.AVAILABLE)
                .tags(newTagList())
                .photoUrls(new ArrayList<>())
                .build();
    }
}
