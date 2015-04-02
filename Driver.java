import java.util.Random;


public class Driver 
{
	public static void main(String[] args) 
	{
		Random r = new Random();
		BinaryTree bt = new BinaryTree();
		for(int i = 0; i < 5000; i++)
		{
			bt.add(r.nextInt());
		}
		System.out.print(bt.isBalanced());
		bt.balance(bt);
		System.out.print(bt.isBalanced());
	}
}

