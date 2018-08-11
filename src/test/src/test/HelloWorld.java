package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 该类用于演示最简单的柱状图生成
 * 
 */
public class HelloWorld implements Test1{
	public List<String> list = new ArrayList<>();
	private int a;
//	public HelloWorld(int a) {
//		this.a = a;
//	}
	public synchronized void add(int i) {
	  for(int j=0;j<i;j++) {
	    list.add(Integer.toString(j));
	  }
	}

	public static boolean indecision() {
	  try {
	    return true;
	  } finally {
	    System.out.println("a");
	  }
	}
	
	public void pri() {
		a = 4;
		System.out.println(this.a);
		this.a = 5;
		System.out.println(a);
		
	}
	public static void main(String[] args) {
	  B b = new B();
	  b.foo();
//	  List<String> dogsInshow = new ArrayList<>();
//	  dogsInshow.add("aaa");
//	  dogsInshow.add("bbb");
//	  List<String> dogs = Collections.unmodifiableList(dogsInshow);
//	  System.out.println(dogs == dogsInshow);
//	  dogsInshow.add("ccc");
//	  System.out.println(dogs);
//		List<String> c = new ArrayList<>();
//		Set<String> d = new HashSet<>();
//		System.out.println("\n");
//		String a = "java.Computer";
//		String b = "123";
//		c.add(a);
//		c.add(a);
//		System.out.println(Integer.valueOf("09"));
//		System.out.print(a.contains("Computer | Server"));
//		d.addAll(c);
//		System.out.println(d);
//	  HelloWorld p1 = new HelloWorld();
//	  HelloWorld p2 = new HelloWorld();
//	  new Thread(p1).start();
//	  new Thread(p1).start();
//	  new Thread(p2).start();
//	  new Thread(p2).start();
//	  try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//	  for(int i=0;i<p1.list.size();i++) {
//	    System.out.println("1 "+p1.list.get(i));
//	  }
//	  for(int i=0;i<p2.list.size();i++) {
//        System.out.println("2 "+p2.list.get(i));
//      }
	}
//  @Override
//  public void run() {
//    // TODO Auto-generated method stub
//    add(5);
//  }
  public String a() {
    // TODO Auto-generated method stub
    return null;
  }
}

class A{
  void foo() {
    this.bar();
    System.out.println(this.getClass().getSimpleName());
  }
  
  void bar() {
    System.out.println("a.bar");
  }
}
class B extends A{
  public B() {}
  
  void foo() {
    super.foo();
  }
  
  void bar() {
    System.out.println("b.bar");
  }
}
