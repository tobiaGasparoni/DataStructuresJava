package src;

import java.util.ArrayList;
import java.util.Collections;

import dataStructures.BalancedTwoThreeTree;

public class BalancedTwoThreeTreeTest
{
	public static BalancedTwoThreeTree<Integer, String> tree;
	
	public ArrayList<Integer> setup(int numero)
	{
		tree = new BalancedTwoThreeTree<Integer, String>();
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for(int i = 0; i < numero; i++)
		{
			ints.add(i);
		}
		Collections.shuffle(ints);
		return ints;
	}
	
	public void insertTest(ArrayList<Integer> ints) throws Exception
	{
		for(int i = 0; i < ints.size(); i++)
		{
			tree.insert(ints.get(i), "V: " + ints.get(i));
		}
	}
	
	public void searchTest(int numero) throws Exception
	{
		for(int i = 0; i < numero; i++)
		{
			String string = tree.search(i);
			//System.out.println(i + ", " + string);
		}
	}
	
	static int log(int x, int base)
	{
	    return (int) (Math.log(x) / Math.log(base));
	}
	
	public static void main(String[] args)
	{
		BalancedTwoThreeTreeTest t = new BalancedTwoThreeTreeTest();
		int numero = 1000000;
		ArrayList<Integer> ints = t.setup(numero);
		try
		{
			long startTime = System.currentTimeMillis();
			t.insertTest(ints);
			long endTime = System.currentTimeMillis();
			System.out.println("Se insertaron " + numero + " datos en " + (endTime - startTime) + " milisegundos");
			startTime = System.currentTimeMillis();
			t.searchTest(numero);
			endTime = System.currentTimeMillis();
			System.out.println("Se encontraron los " + numero + " datos en " + (endTime - startTime) + " milisegundos");
			//System.out.println("Estructura del arbol");
			//tree.report();
			System.out.println("Best case (log3N): " + log(numero, 3));
			System.out.println("Height of tree: " + tree.height());
			System.out.println("Worst case (log2N): " + log(numero, 2));
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
