import java.util.Properties;

public class AsciiImage
{
	private int width = 0;
	private int height = 0;

	private String image;

	public AsciiImage()  {}

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
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public String toString()
	{
		String imageString = "";

		for (int i = 0; i < height; i++)
		{
			int substringStart = i * width;
			int substringEnd = substringStart + width;

			imageString += image.substring(substringStart, substringEnd) ;

			if (i != height - 1)
				imageString += System.getProperty("line.separator");
		}

		return imageString;
	}

	public int getUniqueChars()
	{
		String imageString = image;

		int count = 0;

		while (imageString.length() > 0)
		{
			count++;
			imageString = imageString.replace(imageString.substring(0,1), "");
		}

		return count;
	}

	public void flipV()
	{
		String invertedImage = "";

		for (int i = height - 1; i >= 0; i--)
		{
			int substringStart = i * width;
			int substringEnd = substringStart + width;

			invertedImage += image.substring(substringStart, substringEnd);
		}

		image = invertedImage;
	}

	public void transpose()
	{
		int newWidth = height;
		int newHeight = width;

		StringBuffer transposedImage = new StringBuffer();


	}

	private char getPixel(int x, int y)
	{
		return image.charAt(y * width + x);
	}

	private void setPixel(int x, int y, char c)
	{
		String newImage = "";

		int charPos = y* width + x;

		if (charPos != 0)
			newImage = image.substring(0, charPos - 1);

		newImage += c;

		if (charPos + 1 != image.length())
			newImage += image.substring(charPos + 1);

		image = newImage;
	}

	/* public boolean isSymmetricH()
	{

	} */

	public void fill(int x, int y, char c)
	{
		int pos = y * width + x;
		char charToReplace = image.charAt(pos);

		// Zeichen ersetzen!
		setPixel(x, y, c);

		//links
		if (x - 1 >= 0 && image.charAt(pos - 1) == charToReplace)
			fill(x - 1, y, c);
		// oben
		if (y - 1 >= 0 && image.charAt(pos - width) == charToReplace)
			fill(x, y - 1, c);
		// rechts
		if (x + 1 < width && image.charAt(pos + 1) == charToReplace)
			fill(x + 1, y, c);
		// unten
		if (y + 1 < height && image.charAt(pos + width) == charToReplace)
			fill(x, y + 1, c);
	}
}
