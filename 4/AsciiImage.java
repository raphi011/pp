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


        StringBuffer transposedImage = new StringBuffer();

        for (int i = 0; i < width; i++)  {
            for (int j = 0 ; j < height; j++) {
                int pos = j * width + i;
                transposedImage.append(image.charAt(pos));
            }
        }

        int oldWidth = width;

        width = height;
        height = oldWidth;

        image = transposedImage.toString();
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
            newImage = image.substring(0, charPos);

        newImage += c;

        if (charPos + 1 != image.length())
            newImage += image.substring(charPos + 1);

        image = newImage;
    }

    public boolean isSymmetricH()
    {
        for (int i = 0; i < height; i++)
        {
            String row = image.substring(i * width, width);
            StringBuilder invertedRow = new StringBuilder(row);
            invertedRow = invertedRow.reverse();

            if (!row.equals(invertedRow.toString()))
                return false;
        }

        return true;
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
