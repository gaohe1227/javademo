package �㷨.����;

/**
 * 
 * @author �ߺ�
 *
 * 2016��6��16��
 *
 * ����:���������� :����˼��:��Ҫ�����һ������,����ǰ��(n-1) [n>=2]�����Ѿ����ź�˳���,����Ҫ�ѵ�n�����嵽ǰ�����������,ʹ����n����Ҳ���ź�˳���. ��˷���ѭ��,ֱ��ȫ���ź�˳��.
 */
public class InsertSort {
	public InsertSort() {

		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
				25, 53, 51 };

		int temp = 0;

		for (int i = 1; i < a.length; i++) {

			int j = i - 1;

			temp = a[i];

			for (; j >= 0 && temp < a[j]; j--) {

				a[j + 1] = a[j]; // ������temp��ֵ�������һ����λ

			}

			a[j + 1] = temp;

		}

		for (int i = 0; i < a.length; i++)

			System.out.print(a[i]+" ");

	}
	public static void main(String[] args) {
		new InsertSort();
	}
}
