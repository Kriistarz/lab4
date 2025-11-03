package lab4;

import java.util.List;

public class BoxUtil {
    public static double maximum(List<Box<? extends Number>> boxes) {
        Number max = boxes.getFirst().get();

        for (int i = 1; i < boxes.size(); i++) {
            Number value = boxes.get(i).get();
            if (max.doubleValue() < value.doubleValue()) {
                max = value;
            }
        }

        return max.doubleValue();
    }
}
