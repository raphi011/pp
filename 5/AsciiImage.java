import java.util.Properties;

public class AsciiImage
{
	private String eof;
	
	// [rows][columns]
	private char[][] image;

	public AsciiImage(int width, int height)
	{
		image = new char[height][width];
	}

	public boolean addLine(String line)
	{
		if (width == 0)
		{
			if (line.length() < 1)
				return false;

			width = line.length();
			image = "";
		}
		else if (line.length() != width)
			return false;

		image += line;
		height++;

		return true;
	}

	public int getWidth()
	{
		return image[0];
	}

	public int getHeight()
	{
		return image.length;
	}

	public String toString()
	{
		String imageString = "";

		for (int i = 0; i < getHeight(); i++)
		{
			imageString += image[y].toString();

			if (i != getHeight() - 1)
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
			for (int j = 0; i < newWidth; j++)
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
				image[i][j] = '.';
	}

	public void load(String eof)
	{
		this.eof = eof;
	}

	public void drawLine(int x0, int y0, int x1, int y1, char c)
	{

	}

	public void replace(char oldChar, char newChar)
	{
		for (int i = 0; i < getHeight(); i++)
			for (int j = 0; j < getWidth(); j++)
				image[i][j] == oldChar ? image[i][j] = newChar : image[i][j] = oldChar;
	}

	public void fill(int x, int y, char c)
	{
		try
		{
			char charToReplace = getPixel(x,y);

			// Zeichen ersetzen!
			setPixel(x, y, c);

			//links
			if (x - 1 >= 0 && getPixel(x-1,y) == charToReplace)
				fill(x - 1, y, c);
			// oben
			if (y - 1 >= 0 && getPixel(x, y -1) == charToReplace)
				fill(x, y - 1, c);
			// rechts
			if (x + 1 < width && getPixel(x +1, y) == charToReplace)
				fill(x + 1, y, c);
			// unten
			if (y + 1 < height && getPixel(x, y+1) == charToReplace)
				fill(x, y + 1, c);
		}
		catch (Exception ex)
		{
			System.out.println();
		}
	}
}
