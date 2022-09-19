package knubisoft;

import knubisoft.Generator.GenericClass;
import knubisoft.Generator.MockData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer integer = null;
        String str = null;
        Character character = null;
        Long longValue = null;
        Boolean bool = null;
        Float floatVal = null;
        Double doubleVal = null;

        List<List<Integer>> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        MockData mockData = new MockData(3);
        System.out.println(mockData.populate(mockData.unpackGenericClass(new GenericClass<>(map) {}.getType())));
    }
}
