package knubisoft.Generator;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MockData {
    private static final Map<Class<?>, Supplier<Object>> generator = new LinkedHashMap<>();

    private final int capacity;

    public MockData(int capacity) {
        this.capacity = capacity;
        putDataToGenerator();
    }

    private void putDataToGenerator() {
        RandomValue randomValue = new RandomValue();

        generator.put(Integer.class, randomValue::getRandomIntegerValue);
        generator.put(Boolean.class, randomValue::getRandomBooleanValue);
        generator.put(Float.class, randomValue::getRandomFloatValue);
        generator.put(Double.class, randomValue::getRandomDoubleValue);
        generator.put(Character.class, randomValue::getRandomCharacterValue);
        generator.put(Long.class, randomValue::getRandomLongValue);
        generator.put(String.class, randomValue::getRandomStringValue);
    }

    @SneakyThrows
    public void populate(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();

            for (Type t : types) {
                populate(t);
            }

            return;
        }

        if (isSimpleType(type)) {
            System.out.println(generator.get(type).get());

            generator.get(type).get();
        } else {
            Field[] fields = Class.forName(((Class<?>) type).getTypeName()).getDeclaredFields();

            for (Field field : fields) {
                populate(field.getGenericType());
            }

        }

    }

    public Type unpackGenericClass(Type type) {
        ParameterizedType params = (ParameterizedType) type;
        return params.getRawType().equals(GenericClass.class) ? params.getActualTypeArguments()[0] : type;
    }

    private static boolean isSimpleType(Object x) {
        return generator.containsKey(x);
    }
}
