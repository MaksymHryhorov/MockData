package knubisoft;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Main {
    static Map<Class<?>, Supplier<Object>> generator = new LinkedHashMap<>();
    static List<String> list = new ArrayList<>();

    static {
        generator.put(Integer.class, () -> 1);
        generator.put(Boolean.class, () -> true);
        generator.put(String.class, () -> "Hello");
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Integer integer = 1;

        populate(integer);
        populate(list);
    }

    @SneakyThrows
    private static <T> T populate(T type) {
        Class<T> clazz = (Class<T>) type.getClass();

        if (isSimpleType(clazz)) {
            return buildSimpleClass(clazz);
        }

        if (isCollection(clazz)) {
            return buildCustomClass(clazz);
        }

        return null;
    }

    private static <T> T buildSimpleClass(Class<T> clazz) {

        return null;
    }

    private static <T> T buildCustomClass(Class<T> clazz) {

        return null;
    }

    private static boolean isCollection(Class<?> x) {
        return List.class.isAssignableFrom(x) || Map.class.isAssignableFrom(x);
    }

    private static boolean isSimpleType(Object x) {
        return generator.containsKey(x);
    }

}
