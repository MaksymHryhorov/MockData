package knubisoft.Generator;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Generator {
    private final MockData mockData = new MockData();

    /**
     * Generate Map
     * @param parameterizedType Actual type arguments, raw type
     * @param capacity random int value
     * @return generated map with random values
     */
    public Map<Object, Object> generateMap(ParameterizedType parameterizedType, int capacity) {
        Map<Object, Object> resultMap = new LinkedHashMap();
        Type[] nestedMapTypes = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < capacity; i++) {
            resultMap.put(mockData.populate(nestedMapTypes[0]), mockData.populate(nestedMapTypes[1]));
        }

        return resultMap;
    }

    /**
     * Generate List
     * @param parameterizedType Actual type arguments, raw type
     * @param capacity random int value
     * @return generated list with random values
     */
    public List<Object> generateList(ParameterizedType parameterizedType, int capacity) {
        Type nestedListType = parameterizedType.getActualTypeArguments()[0];
        List<Object> resultList = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            resultList.add(mockData.populate(nestedListType));
        }

        return resultList;
    }

    /**
     * Generate Class
     * @param type actual type arguments, raw type
     * @return generated class with random values
     */
    @SneakyThrows
    public Object generateCustomClassInstance(Class type) {
        Class<?> cls = Class.forName(type.getName());
        Field[] fields = cls.getDeclaredFields();
        Object instance = cls.getDeclaredConstructor().newInstance();
        for (Field f : fields) {
            f.setAccessible(true);
            f.set(instance, mockData.populate(f.getGenericType()));
        }

        return instance;
    }
}
