package �㷨.����;
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��20��
 *
 * ����:���ַ�����(���������ܴ����˲��ø÷���.���ö��ַ�����ʱ,�������������ظ���. ����˼��:���������ǰ����������,���ڸ���ֵ x,
 *     �����е��м�λ�ÿ�ʼ�Ƚ�,�����ǰλ��ֵ���� x,����ҳɹ�;�� x С�ڵ�ǰλ��ֵ,�������е�ǰ����в���;
 *     �� x���ڵ�ǰλ��ֵ�������еĺ����м�������,ֱ���ҵ�Ϊֹ)
 */
public class DichotomySearch {
	public static void main(String[] args) {
	       int[] arr = new int[] { 12, 23, 34, 45, 56, 67, 77, 89, 90 };
	       System.out.println(search(arr, 12));
	       System.out.println(search(arr, 45));
	       System.out.println(search(arr, 67));
	       System.out.println(search(arr, 89));
	       System.out.println(search(arr, 99));
	   }
        /**\
         * ���ַ�����
         * @param arr:����
         * @param key:��������
         * @return
         */
	   public static int search(int[] arr, int key) {
 		   int start = 0;//��ʼλ��
	       int end = arr.length - 1;//����λ��
	       while (start <= end) {
	           int middle = (start + end) / 2;
	           if (key < arr[middle]) {
	               end = middle - 1;
	           } else if (key > arr[middle]) {
	               start = middle + 1;
	           } else {
	               return middle;
	           }
	       }
	       return -1;//�Ҳ�������
	   }
}
