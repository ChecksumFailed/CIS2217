import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;


public class Test {

	public static void main(String[] args) {

		Graph<Integer, String> g = new DirectedSparseMultigraph<>();

		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3); 

		g.addEdge("Edge-A", 1, 2);
		g.addEdge("Edge-B", 2, 3);  

		Layout<Integer, String> layout = new CircleLayout<>(g);
		layout.setSize(new Dimension(300,300));

		BasicVisualizationServer<Integer,String> vv = 
				new BasicVisualizationServer<>(layout);
		vv.setPreferredSize(new Dimension(350,350));

		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
