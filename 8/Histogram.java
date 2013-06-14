public class Histogram 
{
	public static AsciiImage getHistogram(AsciiImage img)
	{	
		int height = 16;
		int width = img.getCharset().length();
		int charset = createCharset(img);

		AsciiImage histogram = new AsciiImage(width,height, charset);
		
		
		
		
		
		
		
		return imgCopy;	
	}
	
	private void drawHistogram(AsciiImage histogram, AsciiImage img)
	{
		
	
	
	
	}

	private String createCharset(AsciiImage img);
	{
		String imgCharset = img.getCharset();
		String charset = "1234567890.#";

		for (int i = 0; i < imgCharset.length(); i++)
		{
			if (charset.indexOf(imgCharset.charAt(i)) >= 0)
				charset += imgCharset.charAt(i);
		}

		return charset;
	}

}
