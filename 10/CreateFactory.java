import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: raphaelgruber
 * Date: 6/17/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateFactory implements Factory {

    public CreateFactory () {}

    public Operation create(Scanner scanner) throws FactoryException
    {
        int width;
        int height;
        String charset;

        try {
            width = scanner.nextInt();
            height = scanner.nextInt();

            charset = scanner.next();
        }
        catch (NoSuchElementException e) {
            throw new FactoryException();
        }

        if (width < 1 || height < 1)
            throw new FactoryException();

        return new CreateOperation(width,height,charset);
    }
}
