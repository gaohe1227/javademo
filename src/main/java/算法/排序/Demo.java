package À„∑®.≈≈–Ú;

public class Demo {
   /**
    * √∞≈›≈≈–Ú
    * @param a
    */
	public static void  sort1(int[] a){
		for (int i=0;i<a.length-1;i++){
			int temp=0;
			for(int j=0;j<a.length-1-i;j++){
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
				
			}
			
		}
		
	}
	/**
	 * ≤Â»Î≈≈–Ú
	 * @param a
	 */
	public static void  sort2(int[] a){
	for(int i=1;i<a.length;i++){
	    int temp=a[i];
		for(int j=i-1;j>=0;j--){
			if(a[j]>temp){ 
				a[j+1]=a[j]; 
				a[j]=temp;
			}
			
		}
		
	}
		
	}
	
	/**
	 * —°‘Ò≈≈–Ú
	 * @param a
	 */
	public static void sort3(int[] a) {
		int position=0;
		for(int i=0;i<a.length-1;i++){
			int j=i+1;
			int temp=a[i];
			for(;j<a.length;j++){
				if(a[j]<temp){
					temp=a[j];
					position=j;
				}
				
			}
			a[position]=a[i];
			a[i]=temp;
		}
		

	}
	
	public static void main(String[] args) {
		int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		//int a[]={49,38};
		//sort1(a);
		//sort2(a);
		sort3(a);
		for (int i : a) {
			System.out.print(i+" " );
		}
	 
	}
	
	
}
