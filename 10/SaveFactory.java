import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class SaveFactory implements Factory {

    MetricSet<AsciiImage> saved;

    public SaveFactory(MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    public Operation create(Scanner scanner) throws FactoryException  {

        return new SaveOperation(saved);
    }
}
