import java.util.Properties;

public class AsciiImage 
{
	int width = 0;
	int height = 0;
	
	StringBuffer image;
	
 public AsciiImage()
 {
 	 
 }
 
 public boolean addLine(String line)
 {
 	if (width == 0)
 	{
 		if (line.length() < 1)
 			return false;
 		
 		width = line.length();
 		image = new StringBuffer();	
 	}
 	else if (line.length() != width)
 		return false;
 	
 	image.append(line);
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
 	String imageString = toString();
 	  
        int count = 0;
        
        while (imageString.length() > 0) 
        {
            count++;
            imageString = imageString.replace(image.substring(0,1), "");
        }
        
        return count;
 }
 
 public void flipV()
 {
 	 StringBuffer invertedImage = new StringBuffer();
 	
 	 for (int i = height - 1; i >= 0; i--)
 	 {
 	 	 int substringStart = i * width;
 	 	 int substringEnd = substringStart + width;
 	 	 
 	 	 invertedImage.append(image.substring(substringStart, substringEnd)); 
 	 }
 	 
 	 image = invertedImage;
 }
 
 public void transpose()
 {
 	 int newWidth = height;
 	 int newHeight = width;
 	 
 	 StringBuffer transposedImage = new StringBuffer();
 	 
 	 
 }
 
 public boolean isSymmetricH()
 {
 	 
 }
 
 public void fill(int x, int y, char c)
 {
 	  char charToReplace = image[y].charAt(x);

        // Zeichen ersetzen!
        image[y] = replaceCharAt(image[y], x, c);

        //links
        if (x - 1 >= 0 && image[y].charAt(x - 1) == charToReplace)
            fill(image, x - 1, y, c);
        // oben
        if (y - 1 >= 0 && image[y - 1].charAt(x) == charToReplace)
            fill(image, x, y - 1, c);
        // rechts
        if (x + 1 < image[y].length() && image[y].charAt(x + 1) == charToReplace)
            fill(image, x + 1, y, c);
        // unten
        if (y + 1 < image.length && image[y + 1].charAt(x) == charToReplace)
            fill(image, x, y + 1, c);
 }	
	
	
}
