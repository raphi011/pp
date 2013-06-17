import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MetricSet<E> extends LinkedHashSet<E> {

    public MetricSet() {}

    public MetricSet(Collection<? extends  E> c) {

    }

    public MetricSet<E> search(E e, Metric<? super E> m) {

        int minimumDistance = -1;

        for (E element  : this) {

            int distance = m.distance(e, element);

            if (minimumDistance < 0 || distance < minimumDistance)
                minimumDistance = distance;
        }

        MetricSet<E> metricSet = new MetricSet<E>();

        for (E element  : this) {

            if (m.distance(e, element) == minimumDistance)
                metricSet.add(element);
        }

        return metricSet;
    }
}
