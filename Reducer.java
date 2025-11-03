package lab4;

import java.util.List;
import java.util.function.BinaryOperator;

public class Reducer {
    // Обобщённый метод
    public static <T> T reduce(List<T> list, T identity, BinaryOperator<T> reducer) {
        T result = identity;
        if (list != null) {
            for (T element : list) {
                result = reducer.apply(result, element);
            }
        }
        return result;
    }

}
