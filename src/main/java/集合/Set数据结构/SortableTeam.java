package 集合.Set数据结构;


import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortableTeam {
	public static void main(String[] args) {
		SortedSet<Player> ageTeam=new TreeSet<>();
		ageTeam.add(new Player("John", 32));
		ageTeam.add(new Player("Tom", 16));
		ageTeam.add(new Player("Bill", 17));
		ageTeam.add(new Player("Ann", 18));
		ageTeam.add(new Player("Jack",21));
		printSet(ageTeam);
		System.out.println("----------------------------------");
		/**
		 * 自定义比较器2,实现Comparator
		 */
		SortedSet<Player> nameSortSet=new TreeSet<>(new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				// TODO Auto-generated method stub
				System.out.println(o1.getName()+"---"+o2.getName()+"----"+o1.getName().compareTo(o2.getName()));
				return o1.getName().compareTo(o2.getName());
			}
		});
		nameSortSet.addAll(ageTeam);
		printSet(nameSortSet);
	}

	private static void printSet(Set set) {
		// TODO Auto-generated method stub
		Iterator iterator=set.iterator();
		while(iterator.hasNext()){
			Player player=(Player) iterator.next();
			System.out.println("Player name:"+player.getName()+"---age:"+player.getAge());
		}
		
	}

}
