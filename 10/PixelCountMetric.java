/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class PixelCountMetric implements Metric<AsciiImage> {

    public int distance(AsciiImage i1, AsciiImage i2) {
        return Math.abs((i1.getWidth() * i1.getHeight()) - (i2.getWidth() * i2.getHeight()));
    }
}
