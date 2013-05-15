import java.util.Properties;
import java.util.ArrayList;

public class AsciiImage
{
	private String eof;

	// help variable for adding lines
	private int currentHeight;
	
	// [rows][columns]
	private char[][] image;

	private String charSet;

	public AsciiImage(int width, int height, String charset)
	{
		image = new char[height][width];
		
		clear();
		
		this.currentHeight = 0;
		this.charset = charset;
	}

	public AsciiImage(AsciiImage img)
	{
		image = new char[img.getHeight()][img.getWidth()];
		
		// copies the image content
		for (int i = 0; i < img.image.length; i++)
			System.arraycopy(img.image[i], 0, this.image[i], 0, img.getWidth());

		currentHeight = img.currentHeight;
		eof = img.eof;
	}

	public String getCharSet()
	{
		return charSet;
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

	public ArrayList<AsciiPoint> getPointList(char c)
	{
		ArrayList<AsciiPoint> pointList = new ArrayList<AsciiPoint>();

		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++)
			{
				if (getPixel(x,y) == c)
					pointList.add(new AsciiPoint(x,y));
			}

		return pointList.size() == 0 ? null : pointList;
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
				imageString += new String(image[y]);
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

 	public AsciiPoint getCentroid(char c)
	{
		double x = 0;
		double y = 0;

		ArrayList<AsciiPoint> pointList = getPointList(c);

		if (pointList == null)
			return null;

		int pointListCount = pointList.size();

		for (AsciiPoint p : pointList)
		{
			x += p.getX();
			y += p.getY();
		}
	
		// round to int	
		x = Math.round(x / pointListCount);
		y = Math.round(y / pointListCount);

		return new AsciiPoint((int)x, (int)y);	
	}

	public void growRegion(char c)
	{
		for (AsciiPoint p :  getPointList(c))
		{
			int x = p.getX();
			int y = p.getY();

			// left
			if (x - 1 >= 0 && getPixel(x-1,y) == '.')
				setPixel(x -1, y, c);
		
			// top
			if (y - 1 >= 0 && getPixel(x, y -1) == '.')
				setPixel(x, y - 1, c);
			
			// right
			if (x + 1 < getWidth() && getPixel(x +1, y) == '.')
				setPixel(x + 1, y, c);
			// bottom
			if (y + 1 < getHeight() && getPixel(x, y+1) == '.')
				setPixel(x, y + 1, c);
		}
	}

	public void straightenRegion(char c)
	{
		for (AsciiPoint p : getPointList(c))
		{
			int x = p.getX();
			int y = p.getY();

			int neighbourCount = 0;

			if (x - 1 >= 0 && getPixel(x-1,y) == c)
				neighbourCount++;	

			if (y - 1 >= 0 && getPixel(x, y -1) == c) 
				neighbourCount++;	
			
			if (x + 1 < getWidth() && getPixel(x +1, y) == c)
				neighbourCount++;

			if (y + 1 < getHeight() && getPixel(x, y+1) == c)
				neighbourCount++;

			if (neighbourCount < 2)
				setPixel(p,'.');
		}
	}

	private char getPixel(AsciiPoint p)
	{
		return getPixel(p.getX(), p.getY());
	}

	private void setPixel(AsciiPoint p, char c)
	{
		setPixel(p.getX(), p.getY(), c);
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

	// Floodfill algorythm
	public void fill(int x, int y, char c)
	{
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
