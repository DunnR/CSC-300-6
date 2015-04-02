
public class BinaryTree 
{
	//private Node root;
	private boolean isEmpty;
	private int payload;
	private BinaryTree leftTree;
	private BinaryTree rightTree;
	private int depth;
	
	public BinaryTree()
	{
		this(0);
	}
	
	private BinaryTree(int depth)
	{
		this.isEmpty = true;
		this.leftTree = null;
		this.rightTree = null;
		this.depth = depth;
	}
	
	
	public boolean search(int value)
	{
		//return true if value is in the tree
		//return false if value is not in the tree
		if(this.isEmpty)
		{
			return false;
		}
		else
		{
			if(this.payload == value)
			{
				return true;
			}
			else
			{
				if(value < payload)
				{
					//check the left
					if(this.leftTree == null)
					{
						return false;
					}
					else
					{
						return this.leftTree.search(value);
					}
				}
				else
				{
					//check the right
					if(this.rightTree == null)
					{
						return false;
					}
					else
					{
						return this.rightTree.search(value);
					}
				}
			}
		}
	}
	
	private void visitInOrder()
	{
		if(this.leftTree != null)
		{
			this.leftTree.visitInOrder();
		}
		System.out.println(this.payload + " : " + this.depth);
		if(this.rightTree != null)
		{
			this.rightTree.visitInOrder();
		}
	}

	public void displayInOrder()
	{
		System.out.println("**** In Order ****");
		if(this.isEmpty)
		{
			System.out.println("Empty Tree");
		}
		else
		{
			this.visitInOrder();
		}
	}
	
	private void visitPreOrder()
	{
		System.out.println(this.payload);
		if(this.leftTree != null)
		{
			this.leftTree.visitPreOrder();
		}
		if(this.rightTree != null)
		{
			this.rightTree.visitPreOrder();
		}
	}
	
	public void displayPreOrder()
	{
		System.out.println("**** Pre Order ****");
		if(this.isEmpty)
		{
			System.out.println("Empty Tree");
		}
		else
		{
			this.visitPreOrder();
		}
	}
	
	private void visitPostOrder()
	{
		if(this.leftTree != null)
		{
			this.leftTree.visitPostOrder();
		}
		if(this.rightTree != null)
		{
			this.rightTree.visitPostOrder();
		}
		System.out.println(this.payload);
	}
	
	public void displayPostOrder()
	{
		System.out.println("**** Post Order ****");
		if(this.isEmpty)
		{
			System.out.println("Empty Tree");
		}
		else
		{
			this.visitPostOrder();
		}
	}
	
	private int getMaxDepth()
	{
		if(this.leftTree == null && this.rightTree == null)
		{
			return this.depth;
		}
		else if(this.leftTree == null)
		{
			return this.rightTree.getMaxDepth();
		}
		else if(this.rightTree == null)
		{
			return this.leftTree.getMaxDepth();
		}
		else
		{
			return Math.max(this.leftTree.getMaxDepth(), this.rightTree.getMaxDepth());
		}
	}
	
	public boolean isBalanced()
	{
		if(this.isEmpty)
		{
			return true;
		}
		else
		{
			//boolean-expr?true-val:false-val
			int currMaxLeftDepth = this.leftTree == null?0:this.leftTree.getMaxDepth();
			int currMaxRightDepth = this.rightTree == null?0:this.rightTree.getMaxDepth();
			System.out.println("Max Left = " + currMaxLeftDepth);
			System.out.println("Max Right = " + currMaxRightDepth);
			return Math.abs(currMaxLeftDepth - currMaxRightDepth) <= 1;
		}
	}
	
	public void add(int value)
	{
		if(this.isEmpty)
		{
			this.payload = value;
			this.isEmpty = false;
		}
		else
		{
			if(value <= this.payload)
			{
				if(this.leftTree == null)
				{
					this.leftTree = new BinaryTree(this.depth+1);	
				}
				this.leftTree.add(value);
			}
			else
			{
				if(this.rightTree == null)
				{
					this.rightTree = new BinaryTree(this.depth+1);
				}
				this.rightTree.add(value);
			}
		}
	}
	
	public BinaryTree balance(BinaryTree tree)
	{
		int leftDepth = this.leftTree.getMaxDepth();
		int rightDepth = this.rightTree.getMaxDepth();
		int rotateRightDepth = this.rightTree.getMaxDepth();
		int rotateRightLeftDepth = this.rightTree.getMaxDepth();
		int rotateLeftDepth = this.leftTree.getMaxDepth();
		int rotateLeftRightDepth = this.leftTree.getMaxDepth();
		int distance = Math.abs(this.leftTree.getMaxDepth() - this.rightTree.getMaxDepth());
		
		if(distance > 1)
		{
			if(rightDepth > leftDepth)
			{				
				if(rotateRightDepth > rotateRightLeftDepth)
				{
					return this.rotateRight(tree);
				}
				else
				{
					return this.rotateRightLeft(tree);
				}
			}
			else
				
			{
				if(rotateLeftDepth > rotateLeftRightDepth)
				{
					return this.rotateLeft(tree);
				}
				else
				{
					return this.rotateLeftRight(tree);
				}
			}
		}
		return tree;
	}
		
	
	public BinaryTree rotateRight(BinaryTree tree1)
	{
		BinaryTree tree2 = tree1.rightTree;
		tree1.rightTree = tree2.leftTree;
		tree2.leftTree = tree1;
		return tree2;
	}
	
	public BinaryTree rotateLeft(BinaryTree tree2)
	{
		BinaryTree tree1 = tree2.leftTree;
		tree2.leftTree = tree1.rightTree;
		tree1.rightTree = tree2;
		return tree1;
	}
	
	public BinaryTree rotateLeftRight(BinaryTree tree3)
	{
		tree3.leftTree = this.rotateLeft(tree3.leftTree);
		return this.rotateRight(tree3);
	}
	
	public BinaryTree rotateRightLeft(BinaryTree tree4)
	{
		tree4.rightTree = this.rotateRight(tree4.rightTree);
		return this.rotateLeft(tree4);
	}
	
}
