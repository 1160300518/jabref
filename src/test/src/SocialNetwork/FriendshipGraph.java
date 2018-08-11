package SocialNetwork;

import java.util.ArrayList;

public class FriendshipGraph {
	private class VNode{
		String name;
		int orderNumber;
		ArcNode firstArc;
		public VNode(String name, int orderNumber, ArcNode firstArc) {
			this.name = name;
			this.orderNumber = orderNumber;
			this.firstArc = firstArc;
		}
		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}
		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}
		public int getOrder() {
			return orderNumber;
		}
		public ArcNode getFirstArc() {  
            return firstArc;  
        }  
        public void setFirstArc(ArcNode firstArc) {  
            this.firstArc = firstArc;  
        }  
	}
	private class ArcNode{
		int number;
		ArcNode next;
		public ArcNode(int number, ArcNode next) {
			this.number = number;
			this.next = next;
		}
		public int getAdjvex() {  
            return number;  
        }  
  
        @SuppressWarnings("unused")
		public void setAdjvex(int number) {  
            this.number = number;  
        }  
  
        public ArcNode getNext() {  
            return next;  
        }  
  
        public void setNext(ArcNode next) {  
            this.next = next;  
        }
	}
	ArrayList<VNode> vnodes;
	
	
	public FriendshipGraph(){
		vnodes = new ArrayList<VNode>();
	}
	public void addVertex(Person person) {
		if(findNode(person)!=null)
		{
			System.out.println("Each person should have a unique name");
			System.exit(0);
		}
		VNode vNode = new VNode(person.name,vnodes.size(),null);
		vnodes.add(vNode);
	}
	public VNode findNode(Person person) {
		for(int i=0;i<vnodes.size();i++)
		{
			if(vnodes.get(i).name.compareTo(person.name)==0)
				return vnodes.get(i);
		}
		return null;
	}
	public void addEdge(Person person_a, Person person_b) {
		VNode vNode_b = findNode(person_b);
		VNode vNode_a = findNode(person_a);
		ArcNode arcNode = new ArcNode(vNode_b.getOrder(),null);
		arcNode.setNext(vNode_a.getFirstArc());
		vNode_a.setFirstArc(arcNode);
	}
	public int getDistance(Person person_a, Person person_b) {
		int number = vnodes.size();
		int queue[] = new int[number];
		int visited[] = new int[number];
		int head = 0;
		int rear = 0;
		int distance[] = new int[number];
		int order_a = findNode(person_a).getOrder();
		int order_b = findNode(person_b).getOrder();
		queue[rear++] = order_a;
		for(int i=0;i<number;i++) {
			distance[i] = -1;
		}
		distance[order_a] = 0;
		while(head != rear) {
			int vertex = queue[head++];
			visited[vertex] = 1;
			ArcNode arcNode = vnodes.get(vertex).getFirstArc();
			while(arcNode != null) {
				if(visited[arcNode.getAdjvex()] != 1) {
					queue[rear++] = arcNode.getAdjvex();
					distance[arcNode.getAdjvex()] = distance[vertex]+1;
				}
				arcNode = arcNode.getNext();
			}
		}
		return distance[order_b];
	}
	public static void main(String argc[]) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));
		System.out.println(graph.getDistance(rachel, kramer));
	}
}
