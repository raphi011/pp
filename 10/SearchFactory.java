import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchFactory implements Factory {

    MetricSet<AsciiImage> saved;

    public SearchFactory (MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    public Operation create(Scanner scanner) throws FactoryException {
        if (!scanner.hasNext())
            throw new FactoryException("No metric operation given");

        String metric = scanner.next();
        Operation operation;

        if ("pixelcount".equals(metric))
            operation = new SearchOperation(saved, new PixelCountMetric());
        else if ("uniquechars".equals(metric))
            operation = new SearchOperation(saved, new UniqueCharsMetric());
        else
            throw new FactoryException("Unknown metric");

        return operation;
    }
}