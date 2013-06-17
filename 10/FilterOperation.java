public abstract class FilterOperation implements Operation
{
	protected String charset;
	protected final BlockGenerator generator;

  	public FilterOperation(BlockGenerator generator)
	{
	        this.generator = generator;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);
		String charset = imgCopy.getCharset();

		/* calculate and set median for each coordinate in img */
		for (int x = 0; x < imgCopy.getWidth(); x++)
			for (int y = 0; y < imgCopy.getHeight(); y++)
			{
				int pixelIndex = filter(generator.getBlock(img, x, y));
				char pixel = charset.charAt(pixelIndex);

				imgCopy.setPixel(x,y, pixel);
			}	
		
		return imgCopy;
	}

	public abstract int filter(int[] values);
}
