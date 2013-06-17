/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SaveOperation implements Operation {

    private MetricSet<AsciiImage> saved;

    public SaveOperation(MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    public AsciiImage execute(AsciiImage img) throws OperationException {
        AsciiImage imgCopy = new AsciiImage(img);

        saved.add(imgCopy);

        return imgCopy;
    }

    public MetricSet<AsciiImage> getSaved() {
        return saved;
    }
}

