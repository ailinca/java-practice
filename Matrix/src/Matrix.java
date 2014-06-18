import java.util.ArrayList;

public class Matrix {
	 int height;
	 int width;
	ArrayList<ArrayList<Integer> > matrix;
	
	//constructorul default
	public Matrix()
		{
		  this.height = 0;
		  this.width = 0;
		}
	
	//constructorul de baza
	public Matrix(int h,int w) 
		{ 
		  this.height = h; 
		  this.width = w; 
		  this.matrix = new ArrayList<ArrayList<Integer>> ();
		}
	//setarea pt inaltimea matricei
	public void setHeight(int h)
	{
		this.height = h;
	}
	
	//setarea pt returnarea inaltimii matricei
	public int getHeight()
	{
		return this.height;
	}
	
	//setarea pt latimea matricei
	public void setWidth(int w)
	{
		this.width = w;
	}
	
	//setarea pt returnarea latimii matricei
	public int getWidth()
	{
		return this.width;
	}
	
	//setarea pt elementele matricei
	public void setElements(ArrayList<ArrayList<Integer> > b) 
	{
		this.matrix = b;
	}
	
	//setarea pt returnarea elementelor matricei
	public ArrayList<ArrayList<Integer> > getElements() 
	{
		return this.matrix;
	}
	
	//functie pt adunarea a 2 matrice
	public void sum(Matrix b)
		{
		  if((this.height == b.height)&&(this.width == b.width))
			  for(int i=0 ; i < this.height ; i++)
				  for(int j=0 ; j < this.width ; j++)
					  this.matrix.get(i).set(j, this.matrix.get(i).get(j).intValue() + b.matrix.get(i).get(j).intValue());
		  else System.out.println("Cannot sum 2 matrices with diferent dimensions");
		  
		}

	//descrierea modului de afisare a informatiei clasei 
	public String toString()
	{
		for(int i = 0; i < this.matrix.size(); i++ ){ 
			for(int j=0; j < this.matrix.get(i).size(); j++)
				return this.matrix.get(i).get(j) + " " ;
			
		}

	}
	
}