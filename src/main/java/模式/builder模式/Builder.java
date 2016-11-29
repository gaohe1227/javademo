package 模式.builder模式;

public abstract class Builder {

 public abstract void makeTitle(String title);
 public abstract void makeString(String str);
 public abstract void makeItems(String[] item);
 public abstract Object getResult();

}
