package SocialNetwork;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {

	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    /**
     * Tests calculateRegularPolygonAngle.
     */
    @Test
    public void calculateRegularPolygonAngleTest() {
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
        assertEquals(1, graph.getDistance(rachel, ross), 1);
        assertEquals(2, graph.getDistance(rachel, ben), 1);
        assertEquals(0, graph.getDistance(rachel, rachel), 1);
        assertEquals(0, graph.getDistance(rachel, kramer), 1);
    }
}
