package hw2;

public class test {

	
	static int sum(int n)
	{
		int sum = 0;
		for(int i = 0; i < n; i++)
			sum+=i;
		return sum;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 1; i <= 10; i++)
			System.out.println(sum(i));
	}

}
