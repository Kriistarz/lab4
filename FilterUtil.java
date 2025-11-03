package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterUtil {
    public static <T> List<T> filter(List<T> list, Predicate<T> condition) {
        List<T> result = new ArrayList<>();

        for (T item : list) {
            if (condition.test(item)) {
                result.add(item);
            }
        }

        return result;
    }
}
