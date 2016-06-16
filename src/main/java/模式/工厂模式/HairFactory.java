package 模式.工厂模式;

public class HairFactory {
  public Hair getHair(String key){
	  if("left".equals(key)){
		  return new LeftHair();
	  }
	  if("right".equals(key)){
		  return new RightHair();
	  }
	return null;
  }
  public Hair getHairByClass(String className){
	  try {
		Hair hair=(Hair) Class.forName(className).newInstance();
		return hair;
	} catch (InstantiationException | IllegalAccessException
			| ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;  }
}
