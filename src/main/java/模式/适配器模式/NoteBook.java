package ģʽ.������ģʽ;

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
