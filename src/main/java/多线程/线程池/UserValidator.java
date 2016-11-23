package 多线程.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 处理多个任务返回第一个结果的案例:这
 * 个示例有两个返回随机Boolean值的UserValidator对象。每个UserValidator对象被一个实现TaskValidator类的Callable对象使用。
 * 如果UserValidator类的validate()方法返回false，TaskValidator类将抛出异常。否则，它将返回true值。
 * 
 * 所以，我们有两个任务，可以返回true值或抛出异常。有以下4种情况：
 * 
 * 两个任务都返回ture。invokeAny()方法的结果是第一个完成任务的名称。
 * 第一个任务返回true，第二个任务抛出异常。invokeAny()方法的结果是第一个任务的名称。
 * 第一个任务抛出异常，第二个任务返回true。invokeAny()方法的结果是第二个任务的名称。
 * 两个任务都抛出异常。在本例中，invokeAny()方法抛出一个ExecutionException异常。
 * 如果你多次运行这个示例，你可以获取以上这4种情况。理
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月22日
 */
public class UserValidator {
	private String name;

	public UserValidator(String name) {
		super();
		this.name = name;
	}

	public boolean validate(String name, String password) {
		Random random = new Random();
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		}
		catch (InterruptedException e) {
			return false;
		}
		return random.nextBoolean();
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = "test";
		String password = "test";
		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");
		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);
		List<TaskValidator> taskList = new ArrayList<>();// 任务集合
		taskList.add(ldapTask);
		taskList.add(dbTask);
		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();//创建线程池对象
		String result;
		try {
			result = executor.invokeAny(taskList);//提交任务
			System.out.printf("Main: Result: %s\n", result);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");

	}

}

class TaskValidator implements Callable<String> {
	private UserValidator validator;

	private String user;

	private String password;

	public TaskValidator(UserValidator validator, String user, String password) {
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	@Override
	public String call() throws Exception {
		if (!validator.validate(user, password)) {
			System.out.printf("%s: The user has not been found\n", validator.getName());
			throw new Exception("Error validating user");
		}
		System.out.printf("%s: The user has been found\n", validator.getName());
		return validator.getName();
	}
}
