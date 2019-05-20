package dataStructures;

public class BalancedTwoThreeNode<K extends Comparable<K>, V>
{
	private BalancedTwoThreeNode<K, V> father;
	
	private BalancedTwoThreeNode<K, V> firstChild;
	private BalancedTwoThreeNode<K, V> secondChild;
	private BalancedTwoThreeNode<K, V> thirdChild;
	private BalancedTwoThreeNode<K, V> fourthChild;
	
	private K firstKey;
	private K secondKey;
	private K thirdKey;
	
	private V firstValue;
	private V secondValue;
	private V thirdValue;
	
	private int typeOfNode;
	
	public BalancedTwoThreeNode(K pFirstKey, V pFirstValue, BalancedTwoThreeNode<K, V> pFistChild, BalancedTwoThreeNode<K, V> pSecondChild, BalancedTwoThreeNode<K, V> pFather)
	{
		firstKey = pFirstKey;
		firstValue = pFirstValue;
		firstChild = pFistChild;
		secondChild = pSecondChild;
		typeOfNode = 2;
		father = pFather;
		
		secondKey = null;
		thirdKey = null;
		secondValue = null;
		thirdValue = null;
		thirdChild = null;
		fourthChild = null;
	}
	
	public BalancedTwoThreeNode<K, V> getFather()
	{
		return father;
	}
	
	public BalancedTwoThreeNode<K, V> getChild(int i)
	{
		BalancedTwoThreeNode<K, V> node = null;
		
		switch(i)
		{
			case 1:
				node = firstChild;
				break;
			case 2:
				node = secondChild;
				break;
			case 3:
				node = thirdChild;
				break;
			case 4:
				node = fourthChild;
				break;
		}
		
		return node;
	}
	
	public K getKey(int i)
	{
		K key = null;
		
		switch(i)
		{
			case 1:
				key = firstKey;
				break;
			case 2:
				key = secondKey;
				break;
			case 3:
				key = thirdKey;
				break;
		}
		
		return key;
	}
	
	public V getValue(int i)
	{
		V value = null;
		
		switch(i)
		{
			case 1:
				value = firstValue;
				break;
			case 2:
				value = secondValue;
				break;
			case 3:
				value = thirdValue;
				break;
		}
		
		return value;
	}
	
	public int getTypeOfNode()
	{
		return typeOfNode;
	}
	
	public void setFather(BalancedTwoThreeNode<K, V> pFather)
	{
		father = pFather;
	}
	
	public void setChild(BalancedTwoThreeNode<K, V> pChild, int i)
	{
		switch(i)
		{
			case 1:
				firstChild = pChild;
				break;
			case 2:
				secondChild = pChild;
				break;
			case 3:
				thirdChild = pChild;
				break;
			case 4:
				fourthChild = pChild;
				break;
		}
	}
	
	public void setValue(V value, int i)
	{
		switch(i)
		{
			case 1:
				firstValue = value;
				break;
			case 2:
				secondValue = value;
				break;
			case 3:
				thirdValue = value;
				break;
		}
	}
	
	public void setKey(K key, int i)
	{
		switch(i)
		{
			case 1:
				firstKey = key;
				break;
			case 2:
				secondKey = key;
				break;
			case 3:
				thirdKey = key;
				break;
		}
	}
	
	public void setNodeType(int newType)
	{
		typeOfNode = newType;
	}
	
	public V search(K key) throws Exception
	{
		V searched = null;
		
		switch(typeOfNode)
		{
			case 2:
				if(key.compareTo(firstKey) == 0)
					searched = firstValue;
				else if(key.compareTo(firstKey) == -1)
					searched = firstChild.search(key);
				else if(key.compareTo(firstKey) == 1)
					searched = secondChild.search(key);
				break;
			case 3:
				if(key.compareTo(firstKey) == 0)
					searched = firstValue;
				else if(key.compareTo(secondKey) == 0)
					searched = secondValue;
				else if(key.compareTo(firstKey) == -1)
					searched = firstChild.search(key);
				else if(key.compareTo(firstKey) == 1 && key.compareTo(secondKey) == -1)
					searched = secondChild.search(key);
				else if(key.compareTo(secondKey) == 1)
					searched = thirdChild.search(key);
				break;
			case 4:
				throw new Exception("The node should not be type 4");
			default:
				throw new Exception("The node is not of a valid type");
		}
		
		return searched;
	}
	
	//Insert deja los nodos listos para ser reparados
	public BalancedTwoThreeNode<K, V> insert(K key, V value) throws Exception
	{
		BalancedTwoThreeNode<K, V> node = null;
		
		invariant();
		switch(typeOfNode)
		{
			case 2:
				if(key.compareTo(firstKey) == 0)
					firstValue = value;
				else if(key.compareTo(firstKey) == -1)
				{
					if(firstChild != null)
						node = firstChild.insert(key, value);
					else
					{
						K tempK = firstKey;
						V tempV = firstValue;
						firstKey = key;
						firstValue = value;
						secondKey = tempK;
						secondValue = tempV;
						typeOfNode = 3;
					}
				}
				else
				{
					if(secondChild != null)
						node = secondChild.insert(key, value);
					else
					{
						secondKey = key;
						secondValue = value;
						typeOfNode = 3;
					}
				}
				break;
			case 3:
				if(key.compareTo(firstKey) == 0)
					firstValue = value;
				else if(key.compareTo(secondKey) == 0)
					secondValue = value;
				else if(key.compareTo(firstKey) == -1)
				{
					if(firstChild != null)
						node = firstChild.insert(key, value);
					else
					{
						K tempK = firstKey;
						V tempV = firstValue;
						firstKey = key;
						firstValue = value;
						thirdKey = secondKey;
						thirdValue = secondValue;
						secondKey = tempK;
						secondValue = tempV;
						typeOfNode = 4;
						node = repare();
					}
				}
				else if(key.compareTo(firstKey) == 1 && key.compareTo(secondKey) == -1)
				{
					if(secondChild != null)
						node = secondChild.insert(key, value);
					else
					{
						K tempK = secondKey;
						V tempV = secondValue;
						secondKey = key;
						secondValue = value;
						thirdKey = tempK;
						thirdValue = tempV;
						typeOfNode = 4;
						node = repare();
					}
				}
				else if(key.compareTo(secondKey) == 1)
				{
					if(thirdChild != null)
						node = thirdChild.insert(key, value);
					else
					{
						thirdKey = key;
						thirdValue = value;
						typeOfNode = 4;
						node = repare();
					}
				}
				break;
			case 4:
				throw new Exception("The node should not be type 4");
			default:
				throw new Exception("The node is not of a valid type");
		}
		invariant();
		
		return node;
	}
	
	//Repare reapara todos los nodos desde al actual hasta el primero
	public BalancedTwoThreeNode<K, V> repare() throws Exception
	{
		BalancedTwoThreeNode<K, V> node = null;
		
		invariant();
		if(father == null)
		{
			setFather(new BalancedTwoThreeNode<K, V>(secondKey, secondValue, this, null, null));
			node = father;
			father.setChild(new BalancedTwoThreeNode<K, V>(thirdKey, thirdValue, thirdChild, fourthChild, father), 2);
			if(father.getChild(2).getChild(1) != null)
				father.getChild(2).getChild(1).setFather(father.getChild(2));
			if(father.getChild(2).getChild(2) != null)
				father.getChild(2).getChild(2).setFather(father.getChild(2));
			setToNull();
			typeOfNode = 2;
		}
		else
		{
			BalancedTwoThreeNode<K, V> pFather = father;
			node = pFather;
			while(pFather != null)
			{
				node = pFather;
				pFather = pFather.getFather();
			}
			if(father.getTypeOfNode() == 2) //is a 2 type node
			{
				if(father.getChild(1).equals(this))
				{
					father.setChild(father.getChild(2), 3);
					father.insertStrict(secondKey, secondValue);
					father.setChild(new BalancedTwoThreeNode<K, V>(thirdKey, thirdValue, thirdChild, fourthChild, father), 2);
					if(father.getChild(2).getChild(1) != null)
						father.getChild(2).getChild(1).setFather(father.getChild(2));
					if(father.getChild(2).getChild(2) != null)	
						father.getChild(2).getChild(2).setFather(father.getChild(2));
					setToNull();
					typeOfNode = 2;
					father.setNodeType(3);
				}
				else if(father.getChild(2).equals(this))
				{
					father.setChild(new BalancedTwoThreeNode<K, V>(thirdKey, thirdValue, thirdChild, fourthChild, father), 3);
					father.insertStrict(secondKey, secondValue);
					if(father.getChild(3).getChild(1) != null)
						father.getChild(3).getChild(1).setFather(father.getChild(3));
					if(father.getChild(3).getChild(2) != null)
						father.getChild(3).getChild(2).setFather(father.getChild(3));
					setToNull();
					father.setNodeType(3);
					typeOfNode = 2;
				}
			}
			else if(father.getTypeOfNode() == 3) //is a 3 type node
			{
				if(father.getChild(1).equals(this))
				{
					father.setChild(father.getChild(3),4);
					father.setChild(father.getChild(2),3);
					father.insertStrict(secondKey, secondValue);
					father.setChild(new BalancedTwoThreeNode<K, V>(thirdKey, thirdValue, thirdChild, fourthChild, father), 2);
					if(father.getChild(2).getChild(1) != null)
						father.getChild(2).getChild(1).setFather(father.getChild(2));
					if(father.getChild(2).getChild(2) != null)
						father.getChild(2).getChild(2).setFather(father.getChild(2));
					setToNull();
					father.setNodeType(4);
					typeOfNode = 2;
					node = father.repare();
				}
				else if(father.getChild(2).equals(this))
				{
					father.setChild(father.getChild(3),4);
					father.insertStrict(secondKey, secondValue);
					father.setChild(new BalancedTwoThreeNode<K, V>(thirdKey, thirdValue, thirdChild, fourthChild, father), 3);
					if(father.getChild(3).getChild(1) != null)
						father.getChild(3).getChild(1).setFather(father.getChild(3));
					if(father.getChild(3).getChild(2) != null)
						father.getChild(3).getChild(2).setFather(father.getChild(3));
					setToNull();
					father.setNodeType(4);
					typeOfNode = 2;
					node = father.repare();
				}
				else if(father.getChild(3).equals(this))
				{
					father.setChild(new BalancedTwoThreeNode<K, V>(thirdKey, thirdValue, thirdChild, fourthChild, father), 4);
					father.insertStrict(secondKey, secondValue);
					if(father.getChild(4) != null && father.getChild(4).getChild(1) != null)
						father.getChild(4).getChild(1).setFather(father.getChild(4));
					if(father.getChild(4) != null && father.getChild(4).getChild(2) != null)
						father.getChild(4).getChild(2).setFather(father.getChild(4));
					setToNull();
					father.setNodeType(4);
					typeOfNode = 2;
					node = father.repare();
				}
			}
		}
		invariant();
		return node;
	}
	
	public void setToNull()
	{
		secondKey = null;
		secondValue = null;
		thirdKey = null;
		thirdValue = null;
		thirdChild = null;
		fourthChild = null;
	}
	
	public void insertStrict(K key, V value) throws Exception
	{
		invariant();
		switch(typeOfNode)
		{
			case 2:
				if(key.compareTo(firstKey) == -1)
				{
					K tempK = firstKey;
					V tempV = firstValue;
					firstKey = key;
					firstValue = value;
					secondKey = tempK;
					secondValue = tempV;
					typeOfNode = 3;
				}
				else
				{
					secondKey = key;
					secondValue = value;
					typeOfNode = 3;
				}
				break;
			case 3:
				if(key.compareTo(firstKey) == -1)
				{
					K tempK = firstKey;
					V tempV = firstValue;
					firstKey = key;
					firstValue = value;
					thirdKey = secondKey;
					thirdValue = secondValue;
					secondKey = tempK;
					secondValue = tempV;
					typeOfNode = 4;
				}
				else if(key.compareTo(firstKey) == 1 && key.compareTo(secondKey) == -1)
				{
					K tempK = secondKey;
					V tempV = secondValue;
					secondKey = key;
					secondValue = value;
					thirdKey = tempK;
					thirdValue = tempV;
					typeOfNode = 4;
				}
				else
				{
					thirdKey = key;
					thirdValue = value;
					typeOfNode = 4;
				}
				break;
			case 4:
				throw new Exception("The node should not be type 4");
			default:
				throw new Exception("The node is not of a valid type");
		}
		invariant();
	}
	
	public void report()
	{
		System.out.println("------------------");
		switch(typeOfNode)
		{
			case 2:
				System.out.println("Type: " + typeOfNode);
				System.out.println("Invariant keys: " + firstKey);
				System.out.println("Unincluded keys: " + secondKey + ", " + thirdKey);
				
				System.out.println("Children:");
				if(firstChild != null) System.out.println("     - FirstChild's keys: " + firstChild.getKey(1) + ", " + firstChild.getKey(2) + ", " + firstChild.getKey(3));
				else System.out.println("     - FirstChild is null");
				
				if(secondChild != null) System.out.println("     - SecondChild's keys: " + secondChild.getKey(1) + ", " + secondChild.getKey(2) + ", " + secondChild.getKey(3));
				else System.out.println("     - SecondChild is null");
				
				if(thirdChild != null) System.out.println("     - ThirdChild's keys: " + thirdChild.getKey(1) + ", " + thirdChild.getKey(2) + ", " + thirdChild.getKey(3));
				else System.out.println("     - ThirdChild is null");
				
				if(fourthChild != null) System.out.println("     - FourthChild's keys: " + fourthChild.getKey(1) + ", " + fourthChild.getKey(2) + ", " + fourthChild.getKey(3));
				else System.out.println("     - FourthChild is null");
				break;
			case 3:
				System.out.println("Type: " + typeOfNode);
				System.out.println("Invariant keys: " + firstKey + ", " + secondKey);
				System.out.println("Unincluded keys: " + thirdKey);
				
				System.out.println("Children:");
				if(firstChild != null) System.out.println("     - FirstChild's keys: " + firstChild.getKey(1) + ", " + firstChild.getKey(2) + ", " + firstChild.getKey(3));
				else System.out.println("     - FirstChild is null");
				
				if(secondChild != null) System.out.println("     - SecondChild's keys: " + secondChild.getKey(1) + ", " + secondChild.getKey(2) + ", " + secondChild.getKey(3));
				else System.out.println("     - SecondChild is null");
				
				if(thirdChild != null) System.out.println("     - ThirdChild's keys: " + thirdChild.getKey(1) + ", " + thirdChild.getKey(2) + ", " + thirdChild.getKey(3));
				else System.out.println("     - ThirdChild is null");
				
				if(fourthChild != null) System.out.println("     - FourthChild's keys: " + fourthChild.getKey(1) + ", " + fourthChild.getKey(2) + ", " + fourthChild.getKey(3));
				else System.out.println("     - FourthChild is null");
				break;
			case 4:
				System.out.println("Type: " + typeOfNode);
				System.out.println("Invariant keys: " + firstKey + ", " + secondKey + ", " + thirdKey);
				
				System.out.println("Children:");
				if(firstChild != null) System.out.println("     - FirstChild's keys: " + firstChild.getKey(1) + ", " + firstChild.getKey(2) + ", " + firstChild.getKey(3));
				else System.out.println("     - FirstChild is null");
				
				if(secondChild != null) System.out.println("     - SecondChild's keys: " + secondChild.getKey(1) + ", " + secondChild.getKey(2) + ", " + secondChild.getKey(3));
				else System.out.println("     - SecondChild is null");
				
				if(thirdChild != null) System.out.println("     - ThirdChild's keys: " + thirdChild.getKey(1) + ", " + thirdChild.getKey(2) + ", " + thirdChild.getKey(3));
				else System.out.println("     - ThirdChild is null");
				
				if(fourthChild != null) System.out.println("     - FourthChild's keys: " + fourthChild.getKey(1) + ", " + fourthChild.getKey(2) + ", " + fourthChild.getKey(3));
				else System.out.println("     - FourthChild is null");
				break;
		}
		System.out.println("------------------");
		if(firstChild != null)
			firstChild.report();
		if(secondChild != null)
			secondChild.report();
		if(thirdChild != null)
			thirdChild.report();
		if(fourthChild != null)
			fourthChild.report();
	}
	
	public void invariant() throws Exception
	{
		switch(typeOfNode)
		{
			case 2:
				assert thirdChild == null;
				assert fourthChild == null;
				assert secondKey == null;
				assert thirdKey == null;
				assert secondValue == null;
				assert thirdValue == null;
				break;
			case 3:
				assert fourthChild == null;
				assert thirdKey == null;
				assert thirdValue == null;
				break;
			case 4:
				assert firstKey != null;
				assert secondKey != null;
				assert thirdKey != null;
				assert firstValue != null;
				assert secondValue != null;
				assert thirdValue != null;
				break;
			default: throw new Exception("The node is not of a valid type");
		}
	}
}
