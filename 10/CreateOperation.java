/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateOperation implements Operation  {

    int width,height;
    String charset;

    public CreateOperation (int width, int height, String charset) {
        this.width = width;
        this.height = height;
        this.charset = charset;
    }

    public AsciiImage execute(AsciiImage img) throws OperationException {

        return new ClearOperation().execute(new AsciiImage(width, height,charset));
    }
}
