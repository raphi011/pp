public class Test {
	
	public static void main(String[] args)
	{
		new B();
	}
}

class A {

	public A() {
		System.out.println("Klasse A");
	}

	public A(int i) {
		System.out.println("Klasse A Constructor int i");
	}
}

class B extends A {

	public B() {
		super(3);
		System.out.println("Klasse B");
	}
}

