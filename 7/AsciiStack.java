public class AsciiStack
{

	private AsciiImage[] stack;
	private int size = 0;
	private int increment = 0;

	public AsciiStack(int increment)
	{
		this.increment = increment;
		stack = new AsciiImage[increment];
	}	

	public int capacity()
	{
		return stack.length;
	}	

	public boolean empty()
	{
		return size == 0;
	}

	public AsciiImage pop()
	{ 
		if (empty())
			return null;
	
		AsciiImage img = stack[--size];
		stack[size] = null;

		if (capacity() - increment > size)
			decreaseSize();

		return img;
	}

	public AsciiImage peek()
	{
		return empty() ? null : stack[size - 1];
	}

	public void push(AsciiImage img)
	{
		if (size == capacity())
			increaseSize();

		stack[size++] = img;
	}

	private void increaseSize()
	{
		changeSize(true);		
	}

	private void decreaseSize()
	{
		changeSize(false);
	}

	private void changeSize(boolean more)
	{
		// adds capacity if more = true, otherwise reduces it
		int newSize = more ? capacity() + increment : capacity() - increment;
		
		AsciiImage[] newStack = new AsciiImage[newSize];
		
		for (int i = 0; i < size; i++)
			newStack[i] = stack[i];

		stack = newStack;
	}

	public int size()
	{
		return size;
	}
}
