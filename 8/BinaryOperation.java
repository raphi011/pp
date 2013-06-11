	public class BinaryOperation implements Operation
	{
		private char threshold;

		public BinaryOperation(char threshold) 
		{
			this.threshold = threshold;
		}

		public AsciiImage execute(AsciiImage img) throws OperationException
		{
			AsciiImage imgCopy = new AsciiImage(img);
			String charset = imgCopy.getCharset();

			if (charset.indexOf(threshold) < 0) throw new OperationException("color not in charset");

			for (int y = 0; y < imgCopy.getHeight(); y++)
				for (int x = 0; x < imgCopy.getWidth(); x++)
				{
					char currentColor = imgCopy.getPixel(x,y);
					char newColor = getBinaryColor(charset, currentColor);
					if (currentColor != newColor)
						imgCopy.setPixel(x,y, newColor);
				}

			return imgCopy;
		}

	private char getBinaryColor(String charset, char color) throws OperationException
	{
		int lastCharIndex = charset.length() - 1;
		
		if (threshold == color) return charset.charAt(lastCharIndex);
		
		for (int i = 0; i < charset.length(); i++)
		{
			if (charset.charAt(i) == color) return (charset.charAt(0));
			else if (charset.charAt(i) == threshold) return (charset.charAt(lastCharIndex));
		}

		throw new OperationException("color not in charset");
	}
}
