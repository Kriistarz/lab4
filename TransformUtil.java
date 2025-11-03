package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TransformUtil {
    public static <T, P> List<P> transform(List<T> list, Function<T, P> func) {
        List<P> result = new ArrayList<>();
        for (T item : list) {
            result.add(func.apply(item));
        }

        return result;
    }
}
