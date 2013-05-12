public class ClearOperation : Operation
{
	public AsciiImage Execute(AsciiImage img) throws OperationException
	{
		for (int i = 0; i < getHeight() ; i++)
			for (int j = 0; j < getWidth(); j++)
				image[i][j] = '.';
	}
}
