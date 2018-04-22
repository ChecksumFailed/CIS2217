import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.Vertex;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GraphViewer extends JFrame {
	private static final long serialVersionUID = 1L;
	HashMap<String, FacebookUser> userVertexMap = new HashMap<>(); //hashmap to store graph vertex

	public GraphViewer(final FacebookUser user, int maxDepth) {

		Graph<FacebookUser, String> g = new SparseMultigraph<>();
		// Graph<FacebookUser, String> g = new DirectedSparseGraph<>();
		g.addVertex(user); // add Base User
		addLevel(g,user,maxDepth,1);

		Layout<FacebookUser, String> layout = new ISOMLayout<>(g);
		layout.setSize(new Dimension(500, 400));

		BasicVisualizationServer<FacebookUser, String> vv = new BasicVisualizationServer<>(layout);
		vv.setPreferredSize(new Dimension(550, 450));

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<FacebookUser>());

		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		// Set vertex label to white. See
		// https://stackoverflow.com/questions/35300510/vertex-label-color-in-jung-visualization
		final Color vertexLabelColor = Color.WHITE;
		DefaultVertexLabelRenderer vertexLabelRenderer = new DefaultVertexLabelRenderer(vertexLabelColor) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4653313129783805374L;

			@Override
			public <V> Component getVertexLabelRendererComponent(JComponent vv, Object value, Font font,
					boolean isSelected, V vertex) {
				super.getVertexLabelRendererComponent(vv, value, font, isSelected, vertex);
				setForeground(vertexLabelColor);
				return this;
			}
		};
		vv.getRenderContext().setVertexLabelRenderer(vertexLabelRenderer);

		//
		Transformer<FacebookUser, Shape> vertexSize = new Transformer<FacebookUser, Shape>() {
			public Shape transform(FacebookUser u) {
				Graphics graphics = getGraphics();
				FontMetrics fontMetrics = graphics.getFontMetrics();
				int radius = (int) (fontMetrics.stringWidth(u.toString()) * 1.1);
				return new Ellipse2D.Double(-15, -15, radius, radius);
			}

		};

		// Set vertex color fill to blue if it is the base user.
		Transformer<FacebookUser, Paint> vertexPaint = new Transformer<FacebookUser, Paint>() {
			public Paint transform(FacebookUser u) {
				if (u == user)
					return Color.RED;
				else
					return Color.blue;
			}
		};

		vv.getRenderContext().setVertexShapeTransformer(vertexSize);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);

		setTitle(user.toString() + "'s Neighborhood");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().add(vv);
		pack();
		setVisible(true);
	}

	
	//Recursively populate graph 
	private void addLevel(Graph<FacebookUser, String> g, FacebookUser user, int maxDepth, int depth) {

		for (FacebookUser friend : user.getFriends()) {
			if (this.userVertexMap.get(friend.toString()) == null) { //Check if vertex already exists, add if not.
				this.userVertexMap.put(friend.toString(), friend);
				g.addVertex(friend);
			}
			g.addEdge(user.toString() + "-" + friend.toString(), user, friend, EdgeType.DIRECTED); //add directed edge
			if (depth <= maxDepth)//if depth is less than maxdepth, continue recursion
				addLevel(g, friend, maxDepth, ++depth);

		}
	}
}
