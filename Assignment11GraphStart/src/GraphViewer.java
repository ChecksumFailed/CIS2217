import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GraphViewer extends JFrame {
	private static final long serialVersionUID = 1L;
	HashMap<String,Graph> userVertexMap = new HashMap<>();

	public GraphViewer(final FacebookUser user) {

		Graph<FacebookUser, String> g = new SparseMultigraph<>();

		g.addVertex(user);
		
		for (FacebookUser friend : user.getFriends()) {
			g.addVertex(friend);
			g.addEdge(user.toString() + "-" + friend.toString(), user, friend, EdgeType.DIRECTED);
		}

		Layout<FacebookUser, String> layout = new ISOMLayout<>(g);
		layout.setSize(new Dimension(500, 400));

		BasicVisualizationServer<FacebookUser, String> vv = new BasicVisualizationServer<>(layout);
		vv.setPreferredSize(new Dimension(550, 450));

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<FacebookUser>());
		
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		Transformer<FacebookUser, Shape> vertexSize = new Transformer<FacebookUser, Shape>() {
			public Shape transform(FacebookUser u) {
				Graphics graphics = getGraphics();
				FontMetrics fontMetrics = graphics.getFontMetrics();
				int radius = (int) (fontMetrics.stringWidth(u.toString()) * 1.1);
				return new Ellipse2D.Double(-15, -15, radius, radius);
			}

		};
		
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
}
