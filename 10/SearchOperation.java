/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchOperation implements Operation {

    MetricSet<AsciiImage> saved;
    Metric<AsciiImage> m;

    public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> m) {
        this.saved = saved;
        this.m = m;
    }

    public AsciiImage execute(AsciiImage img) throws OperationException {
        if (saved.isEmpty())
            throw new OperationException();

        return new AsciiImage(saved.search(img,m).iterator().next());
    }
}
