package mamarantearaujo_hw2;

public class test {

	
	static int sum(int n)
	{
		int sum = 0;
		for(int i = 0; i < n; i++)
			sum+=i;
		return sum;
	}
	
	
	
	public static void main(String[] args) {
		String[] strs = new String[]{"ab", "ba"};
		System.out.println(strs[0].compareTo(strs[1])<0);
	}

}
