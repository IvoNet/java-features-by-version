import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class JEP485 {

    /**
     * Gathers elements into fixed-size groups. The last group may contain fewer
     * elements.
     * @param windowSize the maximum size of the groups
     * @return a new gatherer which groups elements into fixed-size groups
     * @param <TR> the type of elements the returned gatherer consumes and produces
     */
    static <TR> Gatherer<TR, ?, List<TR>> fixedWindow(int windowSize) {

        // Validate input
        if (windowSize < 1)
            throw new IllegalArgumentException("window size must be non-zero");

        // This gatherer is inherently order-dependent,
        // so it should not be parallelized
        return Gatherer.ofSequential(

                // The initializer creates an ArrayList which holds the current
                // open window
                () -> new ArrayList<TR>(windowSize),

                // The integrator is invoked for each element consumed
                Gatherer.Integrator.ofGreedy((window, element, downstream) -> {

                    // Add the element to the current open window
                    window.add(element);

                    // Until we reach our desired window size,
                    // return true to signal that more elements are desired
                    if (window.size() < windowSize)
                        return true;

                    // When window is full, close it by creating a copy
                    var result = new ArrayList<TR>(window);

                    // Clear the window so the next can be started
                    window.clear();

                    // Send the closed window downstream
                    return downstream.push(result);

                }),

                // The combiner is omitted since this operation is intrinsically sequential,
                // and thus cannot be parallelized

                // The finisher runs when there are no more elements to pass from the upstream
                (window, downstream) -> {
                    // If the downstream still accepts more elements and the current
                    // open window is non-empty then send a copy of it downstream
                    if(!downstream.isRejecting() && !window.isEmpty()) {
                        downstream.push(new ArrayList<TR>(window));
                        window.clear();
                    }
                }

        );
    }
    public static void main(String[] args) {
        System.out.println("$ " + IntStream.range(1, 10).boxed().gather(fixedWindow(3)).toList());
    }
}
//$ java JEP485.java
//$ [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
