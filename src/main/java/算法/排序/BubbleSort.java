package 算法.排序;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月16日
 *
 * 作用:冒泡排序案例：基本思想:在要排序的一组数中,对当前还未排好序的范围内的全部数,自上而下对相邻的两个数依次进行比较和调整,让较大的数往下沉,
 *     较小的往上冒.即:每当两相邻的数比较后发现它们的排序与排序要求相反时,就将它们互换.
 */
public class BubbleSort {
	public BubbleSort(){
	 int a[]={1,54,6,3,78,34,12,45};  
	  
	    int temp=0;  
	  
	    for(int i=0;i<a.length-1;i++){  
	  
	       for(int j=0;j<a.length-i-1;j++){  
	  
	       if(a[i]>a[j]){  
	  
	           temp=a[i];  
	  
	           a[i]=a[j];  
	  
	           a[j]=temp;  
	  
	       }  
	  
	       }  
	  
	    }  
	  
	    for(int i=0;i<a.length;i++)  
	  
	       System.out.println(a[i]);     
	  
	}  
}
