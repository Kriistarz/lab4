package lab4;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CollectorUtil {

    public static <T, P> P collect(
            List<T> source,
            Supplier<P> collectionFactory,
            BiConsumer<P, T> accumulator
    ) {
        P result = collectionFactory.get();
        for (T element : source) {
            accumulator.accept(result, element);
        }
        return result;
    }
}
