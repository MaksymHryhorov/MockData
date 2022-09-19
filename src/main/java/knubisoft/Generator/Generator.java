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

    public Map<Object, Object> generateMap(ParameterizedType parameterizedType, int capacity) {
        Map<Object, Object> resultMap = new LinkedHashMap();
        Type[] nestedMapTypes = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < capacity; i++) {
            resultMap.put(mockData.populate(nestedMapTypes[0]), mockData.populate(nestedMapTypes[1]));
        }
        return resultMap;
    }

    public List<Object> generateList(ParameterizedType parameterizedType, int capacity) {
        Type nestedListType = parameterizedType.getActualTypeArguments()[0];
        List<Object> resultList = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            resultList.add(mockData.populate(nestedListType));
        }
        return resultList;
    }

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
