public class LoadOperation implements Operation
{
	private String data;

	public LoadOperation(String data)
	{
		this.data = data;
	}
	
	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);
	
		String[] lines = data.split("\n"); 
		
		if (lines.length > imgCopy.getHeight())
			throw new OperationException();

		try
		{
			for (int y = 0; y<lines.length; y++)
			{
				for (int x = 0; x< lines[y].length(); x++)
					imgCopy.setPixel(x,y, lines[y].charAt(x));
			}
		}
		catch (IndexOutOfBoundsException ex)
		{
			throw new OperationException();
		}

		return imgCopy;
	}
}
