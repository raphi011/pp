public class AsciiStack
{
	private int size = 0;
	private AsciiStackNode head;

	public boolean empty()
	{
		return head == null;
	}

	public AsciiImage pop()
	{ 
		if (empty())
			return null;
		
		AsciiImage img = head.getImage();
		head = head.getNext();

		return img;
	}

	public AsciiImage peek()
	{
		return empty() ? null : head.getImage();
	}

	public void push(AsciiImage img)
	{
		head = new AsciiStackNode(img, head);
	}

	public int size()
	{
		return empty() ? 0 : head.size();
	}
}
