package �㷨.����;
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��16��
 *
 * ����:ð��������������˼��:��Ҫ�����һ������,�Ե�ǰ��δ�ź���ķ�Χ�ڵ�ȫ����,���϶��¶����ڵ����������ν��бȽϺ͵���,�ýϴ�������³�,
 *     ��С������ð.��:ÿ�������ڵ����ȽϺ������ǵ�����������Ҫ���෴ʱ,�ͽ����ǻ���.
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
