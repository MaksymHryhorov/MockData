package knubisoft.Generator;

import java.util.List;
import java.util.Random;

public class RandomValue {
    private static final List<String> ALPHABET = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v,", "w", "x", "y", "z");
    private static final Random RANDOM_VALUE = new Random();
    private static final StringBuilder stringBuilder = new StringBuilder();

    public int getRandomIntegerValue() {

        return RANDOM_VALUE.nextInt(1000);
    }

    public String getRandomStringValue() {
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(ALPHABET.get(RANDOM_VALUE.nextInt(ALPHABET.size())));
        }

        return stringBuilder.toString();
    }

    public Character getRandomCharacterValue() {
        stringBuilder.append(ALPHABET);

        return stringBuilder.charAt(RANDOM_VALUE.nextInt(stringBuilder.length()));
    }

    public Boolean getRandomBooleanValue() {

        return RANDOM_VALUE.nextBoolean();
    }

    public Float getRandomFloatValue() {

        return RANDOM_VALUE.nextFloat();
    }

    public Double getRandomDoubleValue() {

        return RANDOM_VALUE.nextDouble();
    }

    public Long getRandomLongValue() {

        return RANDOM_VALUE.nextLong();
    }

}
