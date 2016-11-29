package 模式.builder模式;

public class TextBuilder extends Builder{
	private StringBuilder buffer=new StringBuilder();

	public TextBuilder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeTitle(String title) {
		// TODO Auto-generated method stub
		buffer.append("------------------------------------------\n");
		buffer.append("["+title+"]");
		buffer.append("\n");
		
	}

	@Override
	public void makeString(String str) {
		// TODO Auto-generated method stub
		buffer.append("|"+str+"|");
		buffer.append("\n");
	}

	@Override
	public void makeItems(String[] item) {
		// TODO Auto-generated method stub
		for (int i = 0; i < item.length; i++) {
			buffer.append("'"+item[i]+"'\n");
		}
		buffer.append("\n");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		buffer.append("==============================================\n");
		return buffer.toString();
	}
	public static void main(String args[]){
		Dicrator dicrator=new Dicrator(new TextBuilder());
		String result=dicrator.construct().toString();
		System.out.println(result);
	}

}
