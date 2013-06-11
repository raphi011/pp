public class LineOperation implements Operation
{
	private int x0, y0, x1, y1;
	private char c;

	public LineOperation(int x0, int y0, int x1, int y1, char c)
	{
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);

		// 1)
		int dreX = x1 - x0;
		int dreY = y1 - y0;
		
		boolean axisInverted = false;
		
		// Swaps p1 and p2 if |y1 - y0| > |x1 - x0|
		if (Math.abs(dreY) > Math.abs(dreX))
		{
			// 3)
			int tmp = x0;
			
			x0 = y0;
			y0 = tmp;

			tmp = x1;
			x1 = y1;
			y1 = tmp;
			
			tmp = dreX;
			dreX = dreY;
			dreY = tmp;
			
			axisInverted = true;
		}
		
		// 4) Swaps end with startpoint if x1 < x0
		if (x1 < x0)
		{
			int tmp = x1;
			x1 = x0;
			x0 = tmp;
			
			tmp = y1;
			y1 = y0;
			y0 = tmp;
		}
	
		// 5) 
		int x = x0;
		double y = y0;
		
		dreX = x1 - x0;
		dreY = y1 - y0;
		
		// calculates steady incline
		double incline = (double)dreY /  (double)dreX;

		while (x <= x1)
		{
			// 5.1)
			if (axisInverted)
				imgCopy.setPixel((int)Math.round(y),x,c);
			else 
				imgCopy.setPixel(x,(int)Math.round(y),c);

			// 5.2)
			x++;
			
			// 5.3)
			y+= incline;
		}

		return imgCopy;
	}
}
