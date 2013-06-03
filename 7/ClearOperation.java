public class ClearOperation implements Operation
{
	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);

		String charset = imgCopy.getCharset();

		char c = charset.charAt(charset.length() - 1);

		for (int i = 0; i < imgCopy.getHeight() ; i++)
			for (int j = 0; j < imgCopy.getWidth(); j++)
				imgCopy.setPixel(j,i,c);

		return imgCopy;
	}
}
