
public class ComplexCaller{
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Complex z, t;
			z=new Complex();
			t=new Complex(2 , 3);
			z.setReal (4);
			z.setImg(9);
			z.multiply(t);
			System.out.println(z);
			
		}

}
