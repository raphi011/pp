public class StraightenRegionOperation implements Operation
{
	private char c;

	public StraightenRegionOperation(char c)
	{
		this.c = c;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);
	
		for (AsciiPoint p : imgCopy.getPointList(c))
		{
			int x = p.getX();
			int y = p.getY();

			int neighbourCount = 0;

			if (x - 1 >= 0 && imgCopy.getPixel(x-1,y) == c)
				neighbourCount++;	

			if (y - 1 >= 0 && imgCopy.getPixel(x, y -1) == c) 
				neighbourCount++;	
			
			if (x + 1 < imgCopy.getWidth() && imgCopy.getPixel(x +1, y) == c)
				neighbourCount++;

			if (y + 1 < imgCopy.getHeight() && imgCopy.getPixel(x, y+1) == c)
				neighbourCount++;

			if (neighbourCount < 2)
				imgCopy.setPixel(p,'.');
		}

		return imgCopy;
	}
}
