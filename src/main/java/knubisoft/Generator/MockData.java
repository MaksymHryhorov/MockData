package knubisoft.Generator;

import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class MockData {
    private static final Map<Class<?>, Supplier<Object>> classSupplierMap = new LinkedHashMap<>();

    private final int capacity;

    public MockData(int capacity) {
        this.capacity = capacity;
        putDataToGenerator();
    }

    public MockData() {
        capacity = 0;
    }

    private void putDataToGenerator() {
        RandomValue randomValue = new RandomValue();

        classSupplierMap.put(Integer.class, randomValue::getRandomIntegerValue);
        classSupplierMap.put(Boolean.class, randomValue::getRandomBooleanValue);
        classSupplierMap.put(Float.class, randomValue::getRandomFloatValue);
        classSupplierMap.put(Double.class, randomValue::getRandomDoubleValue);
        classSupplierMap.put(Character.class, randomValue::getRandomCharacterValue);
        classSupplierMap.put(Long.class, randomValue::getRandomLongValue);
        classSupplierMap.put(String.class, randomValue::getRandomStringValue);
    }

    @SneakyThrows
    public Object populate(Type type) {
        Generator generator = new Generator();

        if (type instanceof ParameterizedType parameterizedType) {
            Type incomeRawType = parameterizedType.getRawType();
            if (List.class.isAssignableFrom((Class<?>) incomeRawType)) {
                return generator.generateList(parameterizedType, capacity);
            }
            if (Map.class.isAssignableFrom((Class<?>) incomeRawType)) {
                return generator.generateMap(parameterizedType, capacity);
            }
        }
        if (isSimpleType(type)) {
            return classSupplierMap.get(type).get();
        } else {
            return generator.generateCustomClassInstance((Class) type);
        }

    }

    public Type unpackGenericClass(Type type) {
        ParameterizedType params = (ParameterizedType) type;
        return params.getRawType().equals(GenericClass.class) ? params.getActualTypeArguments()[0] : type;
    }

    private static boolean isSimpleType(Object x) {
        return classSupplierMap.containsKey(x);
    }
}
