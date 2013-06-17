/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class UniqueCharsMetric implements Metric<AsciiImage> {

    public int distance (AsciiImage i1, AsciiImage i2) {
        return Math.abs(i1.getUniqueChars() - i2.getUniqueChars());
    }

}
