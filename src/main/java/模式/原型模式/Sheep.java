package 模式.原型模式;

import java.util.Date;

/**
 * 原型模式最核心的便是原型类ProtoType
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class Sheep implements Cloneable{
    private String name;
    private Date birthday;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj=super.clone();//直接调用Object对象的clone方法
        return obj;
    }



    public Sheep(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public static void main(String[] args) throws Exception {
        Sheep s1=new Sheep("多利",new Date(134635126));
        Sheep s2=(Sheep) s1.clone();

        System.out.println(s1);
        System.out.println(s1.getName());
        System.out.println(s1.getBirthday());

        System.out.println(s2);
        System.out.println(s2.getName());
        System.out.println(s2.getBirthday());
    }
}