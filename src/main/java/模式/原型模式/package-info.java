/**
 * 当我们需要构建一个与已经存在对象相似时，再通过new去产生一个新对象需要非常繁琐的数据准备或访问权限，而这时就可以用到原型模式，原型模式实现就是Java中的克隆技术，以某个对象为原型，复制出新的对象，显然新的对象具备原型对象相同的特点。原型模式优点是，效率高，可以直接复制，避免了重新执行构造方法过程步骤。
 * 
 * 原型模式主要用于对象的复制，它的核心是就是类图中的原型类Prototype。Prototype类需要具备以下两个条件：
 * 
 * 实现Cloneable接口。在java语言有一个Cloneable接口，它的作用只有一个，就是在运行时通知虚拟机可以安全地在实现了此接口的类上使用clone方法。在java虚拟机中，只有实现了这个接口的类才可以被拷贝，否则在运行时会抛出CloneNotSupportedException异常。
 * 重写Object类中的clone方法。Java中，所有类的父类都是Object类，Object类中有一个clone方法，作用是返回对象的一个拷贝，但是其作用域protected类型的，一般的类无法调用，因此，Prototype类需要将clone方法的作用域修改为public类型。
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
package 模式.原型模式;