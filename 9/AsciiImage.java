import java.util.Properties;
import java.util.ArrayList;

public class AsciiImage
{
	// [rows][columns]
	private char[][] image;

	private String charset;

	public AsciiImage(int width, int height, String charset)
	{
		this.image = new char[height][width];
		this.charset = charset;
	}

	public AsciiImage(AsciiImage img)
	{
		this.image = new char[img.getHeight()][img.getWidth()];
		this.charset = img.charset;

		// copies the image content
		for (int i = 0; i < img.image.length; i++)
			System.arraycopy(img.image[i], 0, this.image[i], 0, img.getWidth());
	}

	public String getCharset()
	{
		return charset;
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
	
	public boolean isInBounds(int x, int y)
	{
		return !(y < 0 || y >= getHeight() || x < 0 || x >= getWidth());
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
	
	private boolean coordinatesAreInBounds(int x,int y)
	{
		if (x < 0 || x >= getWidth())
			return false;
		if (y < 0 || y >= getHeight())
			return false;

		return true;
	}

	public char getPixel(int x, int y) throws IndexOutOfBoundsException
	{
		if (!coordinatesAreInBounds(x,y))
			throw new IndexOutOfBoundsException("x: " + x + " y: " + y);

		return image[y][x];
	}
	
	public char getPixel(AsciiPoint p) throws IndexOutOfBoundsException
	{
		return getPixel(p.getX(), p.getY());
	}

	public void setPixel(AsciiPoint p, char c) throws IndexOutOfBoundsException
	{
		setPixel(p.getX(), p.getY(), c);
	}

	public void setPixel(int x, int y, char c) throws IndexOutOfBoundsException
	{
		if (!coordinatesAreInBounds(x,y))
			throw new IndexOutOfBoundsException("x: " + x + " y: " + y);
		
		image[y][x] = c;
	}
	
/*	public ArrayList<AsciiPoint> getNeighborList(int x, int y)
	{
		ArrayList<AsciiPoint> list = new ArrayList<AsciiPoint>();
		int currentX, currentY;

		for (int dx = -1; dx < 2; dx++)
		{
			currentX = x + dx;	
			
			if (currentX < 0 || currentX >= getWidth())
				continue;
			
			for (int dy = -1; dy < 2; dy++)
			{
				currentY = y + dy;

				if (currentY  < 0 || currentY >= getHeight())
					continue;
				
				list.add(new AsciiPoint(currentX, currentY));
			}
		}
		
		while (int i = list.size(); i < 9; i++)
			list.add(new 

		return list;
	} */

	public int[] getNeighbors(int x, int y)
	{
		int[] neighbors = new int[9]; 

		int currentX, currentY;
		
		int counter = 0;

		for (int dx = -1; dx < 2; dx++)
		{
			currentX = x + dx;	
			
			for (int dy = -1; dy < 2; dy++)
			{
				currentY = y + dy;

				if (isInBounds(currentX, currentY))
					neighbors[counter++] = charset.length() - 1;
				else
					neighbors[counter++] = charset.indexOf(getPixel(currentX, currentY));
			}
		}

		return neighbors;	
	}
}
