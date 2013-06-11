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
	
		try
		{
			for (int y = 0; y<lines.length; y++)
			{
				for (int x = 0; x< lines[y].length(); x++)
				{
					char currentChar = lines[y].charAt(x);

					if (img.getCharset().indexOf(currentChar) < 0)
						throw new OperationException("zeichen nicht vorhanden");

					imgCopy.setPixel(x,y, currentChar);

				}
			}
		}
		catch (IndexOutOfBoundsException ex) {
			throw new OperationException();
		}
		
		return imgCopy;
	}

	private boolean hasInvalidChars(String data, String invalidChars)
	{
		System.out.println(invalidChars.length());
		
		data = data.replace("\n", "");

		for (int i = 0; i< invalidChars.length(); i++ )
			data = data.replace(invalidChars.substring(i,i+1), "");

		return data.trim().length() == 0;
	}
}
