public class GrowRegionOperation implements Operation
{
	private char c;
	
	public GrowRegionOperation(char c)
	{
		this.c = c;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);

		for (AsciiPoint p :  imgCopy.getPointList(c))
		{
			int x = p.getX();
			int y = p.getY();

			// left
			if (x - 1 >= 0 && imgCopy.getPixel(x-1,y) == '.')
				imgCopy.setPixel(x -1, y, c);
		
			// top
			if (y - 1 >= 0 && imgCopy.getPixel(x, y -1) == '.')
				imgCopy.setPixel(x, y - 1, c);
			
			// right
			if (x + 1 < imgCopy.getWidth() && imgCopy.getPixel(x +1, y) == '.')
				imgCopy.setPixel(x + 1, y, c);
			// bottom
			if (y + 1 < imgCopy.getHeight() && imgCopy.getPixel(x, y+1) == '.')
				imgCopy.setPixel(x, y + 1, c);
		}
	
		return imgCopy;	
	}
}
