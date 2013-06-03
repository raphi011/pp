import java.util.Properties;
import java.util.ArrayList;

public class AsciiImage
{
	// [rows][columns]
	private char[][] image;

	private String charset;

	public AsciiImage(int width, int height, String charset)
	{
		image = new char[height][width];
		
		this.charset = charset;
	}

	public AsciiImage(AsciiImage img)
	{
		image = new char[img.getHeight()][img.getWidth()];
		
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
	
	private bool coordinatesAreInBounds(int x,int y)
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
			throw new IndexOutOfBoundsException();

		return image[y][x];
	}
	public char getPixel(AsciiPoint p) throws IndexOutOfBoundsException
	{
		if (!coordinatesAreInBounds(x,y))
			throw new IndexOutOfBoundsException();
		
		return getPixel(p.getX(), p.getY());
	}

	public void setPixel(AsciiPoint p, char c) throws IndexOutOfBoundsException
	{
		if (!coordinatesAreInBounds(x,y))
			throw new IndexOutOfBoundsException();
		
		setPixel(p.getX(), p.getY(), c);
	}

	public void setPixel(int x, int y, char c) throws IndexOutOfBoundsException
	{
		if (!coordinatesAreInBounds(x,y))
			throw new IndexOutOfBoundsException();
		
		image[y][x] = c;
	}
}
