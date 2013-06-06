public class FillOperation implements Operation
{
	private int x;
	private int y;
	private char c;

	public FillOperation(int x, int y, char c)
	{
		this.x = x;
		this.y = y;
		this.c = c;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);

		if (y >= imgCopy.getHeight() || y < 0 || x >= imgCopy.getWidth() || x < 0)
				throw new OperationException();
	
		fill(imgCopy, x,y,c);

		return imgCopy; 	
	}

	private void fill(AsciiImage img, int x, int y, char c)
	{	
		char charToReplace = img.getPixel(x,y);

		// Replace character
		img.setPixel(x, y, c);

		// left
		if (x - 1 >= 0 && img.getPixel(x-1,y) == charToReplace)
			fill(img, x - 1, y, c);
		// top
		if (y - 1 >= 0 && img.getPixel(x, y -1) == charToReplace)
			fill(img, x, y - 1, c);
		// right   
		if (x + 1 < img.getWidth() && img.getPixel(x + 1, y) == charToReplace)
			fill(img, x + 1, y, c);
		// bottom
		if (y + 1 < img.getHeight() && img.getPixel(x, y + 1) == charToReplace)
			fill(img, x, y + 1, c);
	}
}
