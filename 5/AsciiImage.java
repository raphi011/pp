import java.util.Properties;

public class AsciiImage
{
	private String eof;

	// help variable for adding lines
	private int currentHeight;
	
	// [rows][columns]
	private char[][] image;

	public AsciiImage(int width, int height)
	{
		image = new char[height][width];
		clear();
		currentHeight = 0;
	}

	public boolean addLine(String line) throws InputMismatchException
	{
		if (line.trim().equals(eof))
		{
			if (currentHeight == getHeight())
			{
				currentHeight = 0;
				return false;	
			}
			else 
				throw new InputMismatchException();
			
		}
		
		if (line.length() != getWidth())
			throw new InputMismatchException();
		
		for(int i = 0; i < line.length(); i++)
			image[currentHeight][i] = line.charAt(i);
		
		
		
		currentHeight++;

		return true;
	}

	public int getWidth()
	{
		return image[0].length;
	}

	public int getHeight()
	{
		return image.length;
	}

	public String toString()
	{
		String imageString = "";

		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth(); x++)
				imageString += image[y][x];

		      	imageString += System.getProperty("line.separator");
		}

		return imageString;
	}

	public void transpose()
	{
		int newHeight = getWidth();
		int newWidth = getHeight();
		
		char[][] transposedImage = new char[newHeight][newWidth];
		
		
		for (int i = 0; i < newHeight ; i++)
			for (int j = 0; j < newWidth; j++)
				transposedImage[i][j] = image[j][i];

		image = transposedImage;
	}

	private char getPixel(int x, int y)
	{
		return image[y][x];
	}

	private void setPixel(int x, int y, char c)
	{
		image[y][x] = c;
	}

	public void clear()
	{
		for (int i = 0; i < getHeight() ; i++)
			for (int j = 0; j < getWidth(); j++)
				image[i][j] = '.';
	}

	public void load(String eof)
	{
		this.eof = eof;
	}
	
	public void drawLine(int x0, int y0, int x1, int y1, char c)
	{
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
				setPixel((int)Math.round(y),x,c);
			else 
				setPixel(x,(int)Math.round(y),c);

			// 5.2)
			x++;
			
			// 5.3)
			y+= incline;
		}		
	}

	public void replace(char oldChar, char newChar)
	{
		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth(); x++)
			{
				if (image[y][x] == oldChar)
					image[y][x] = newChar;
			}
		}
	}

	public void fill(int x, int y, char c)
	{
			//floodfill algorythm
			char charToReplace = getPixel(x,y);

			// Replace character
			setPixel(x, y, c);

			// left
			if (x - 1 >= 0 && getPixel(x-1,y) == charToReplace)
				fill(x - 1, y, c);
			// top
			if (y - 1 >= 0 && getPixel(x, y -1) == charToReplace)
				fill(x, y - 1, c);
			// right
			if (x + 1 < getWidth() && getPixel(x +1, y) == charToReplace)
				fill(x + 1, y, c);
			// bottom
			if (y + 1 < getHeight() && getPixel(x, y+1) == charToReplace)
				fill(x, y + 1, c);
	}
}
