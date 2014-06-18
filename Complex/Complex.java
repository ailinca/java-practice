
public class Complex {
	private int real;
	private int img;
	
	public Complex()
	{
		this.real=0;
		this.img=0;
	}
	
	public Complex(int r , int i)
	{
		this.real = r;
		this.img = i;
	}
	
	public void sum(Complex z)
	{
		this.real = this.real + z.real;
		this.img = this.img + z.img;
	}
	
	public void multiply(Complex z)
	{
		this.real = (int) (Math.pow(this.real, 2) - Math.pow(z.real, 2));
		this.img = -this.real * z.img - this.img * z.real; 

	}
	public void setReal(int r) {
		this.real = r;
	}
	
	public void setImg(int i) {
		this.img = i;
	}
	public int getReal() {
		return this.real;
	}
	public String toString() {
		return "Complex number:" + this.real + " " + this.img+"i";	
	}
}

