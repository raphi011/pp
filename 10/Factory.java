import java.util.Scanner;

public interface Factory
{
	Operation create(Scanner scanner) throws FactoryException;

}
