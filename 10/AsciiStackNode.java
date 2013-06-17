public class AsciiStackNode
{
	private AsciiImage image;
	private AsciiStackNode next;

	public AsciiStackNode(AsciiImage image, AsciiStackNode next)
	{
		this.image = image;
		this.next = next;
	}

	public AsciiImage getImage()
	{
		return image;
	}

	public AsciiStackNode getNext()
	{
		return next;
	}

	public int size()
	{
		int counter = 1;

		AsciiStackNode nextNode = next;

		while (next != null)
		{
			nextNode = nextNode.next;
			counter++;
		}

		return counter;
	}
}
