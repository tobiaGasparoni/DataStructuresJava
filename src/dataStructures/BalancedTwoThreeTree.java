package dataStructures;

public class BalancedTwoThreeTree<K extends Comparable<K>, V>
{
	private BalancedTwoThreeNode<K, V> root;
	
	public BalancedTwoThreeTree()
	{
		root = null;
	}
	
	public V search(K key) throws Exception
	{
		return root.search(key);
	}
	
	public void insert(K key, V value) throws Exception
	{
		if(root == null)
		{
			root = new BalancedTwoThreeNode<K, V>(key, value, null, null, null);
		}
		else
		{
			BalancedTwoThreeNode<K, V> sRoot = root.insert(key, value);
			if(sRoot != null)
				root = sRoot;
//			System.out.println("NEW TREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		}
	}
	
	public int height()
	{
		int height = 0;
		BalancedTwoThreeNode<K, V> node = root;
		
		while(node.getChild(1) != null)
		{
			node = node.getChild(1);
			height++;
		}
		
		return height;
	}
	
	public void report()
	{
		root.report();
	}
}
