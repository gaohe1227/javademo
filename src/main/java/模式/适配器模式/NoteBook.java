package 模式.适配器模式;

public class NoteBook {
	private ThreePlugif plug;

	public NoteBook(ThreePlugif plug) {
		super();
		this.plug = plug;
	}
	public void charge(){
		plug.powerWithThree();
	}
}
