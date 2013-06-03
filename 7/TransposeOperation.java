public class TransposeOperation implements Operation
{
	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		AsciiImage imgCopy = new AsciiImage(img);

		int newHeight = imgCopy.getWidth();
		int newWidth = imgCopy.getHeight();
		
		char[][] transposedImage = new char[newHeight][newWidth];
		
		
		for (int i = 0; i < newHeight ; i++)
			for (int j = 0; j < newWidth; j++)
			{
				char c = img.getPixel(j,i);
				imgCopy.setPixel(i,j,c);
			}

		return imgCopy;
	}
}
