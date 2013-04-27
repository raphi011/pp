import java.util.Properties;

public class AsciiImage
{
	private String eof;
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

		//	if (y != getHeight() - 1)
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
	
	/*
	1. x und y berechnen
	2. Feststellen ob die Achsen invertiert sind (sprich |y|>|x|)
	3. Falls die Achsen vertauscht sind, x0 mit y0, x1 mit y1 und x mit y vertauschen
	4. Überprüfen ob x1³x0, ansonsten Anfangs und Endpunkt vertauschen
	5. x mit x0 initialisieren und in einer Schleife bis x1 in 1-Pixel-Schritten iterieren, y mit y0 initialisieren
	Den Punkt an der Stelle (x,y) oder, wenn die Achsen in Schritt 3 vertauscht wurden, an der Stelle (y,x) zeichnen
	x um 1 erhöhen
	y um y/x erhöhen
	*/

	public void drawLine(int x0, int y0, int x1, int y1, char c)
	{
		System.out.println("Drawing line ... ");

		// 1)
		int dreX = x1 - x0;
		int dreY = y1 - y0;
		
		boolean axisInverted = false;
		
		// 2)
		if (dreY > dreX)
		{
			// 3)
			x0 = y0;
			x1 = y1;
			
			int tmp = dreX;
			dreX = dreY;
			dreY = tmp;
			
			axisInverted = true;
		}
		
		// 4)
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
		
		dreX = x1 - x;
		dreY = y1 - (int)y;

		while (x <= x1)
		{
			// 5.1)


			if (axisInverted)
{
				System.out.println("y: " + x + "x: " + (int)Math.round(y));
				
				setPixel((int)Math.round(y),x,c);
}			else {
				System.out.println("x: " + x + "y: " + (int)Math.round(y));
				setPixel(x,(int)Math.round(y),c);
}
			// 5.2)
			x++;
			
			// 5.3)
			//dreX = x1 - x;
			//dreY = y1 - (int)y;
						
			y+= (double)dreY / (double)dreX;
		}		
	}

	public void replace(char oldChar, char newChar)
	{
		for (int i = 0; i < getHeight(); i++)
			for (int j = 0; j < getWidth(); j++)
			{
				if (image[i][j] == oldChar)
					image[i][j] = newChar;
			}
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
			if (x + 1 < getWidth() && getPixel(x +1, y) == charToReplace)
				fill(x + 1, y, c);
			// unten
			if (y + 1 < getHeight() && getPixel(x, y+1) == charToReplace)
				fill(x, y + 1, c);
		}
		catch (Exception ex)
		{
			System.out.println();
		}
	}
}
