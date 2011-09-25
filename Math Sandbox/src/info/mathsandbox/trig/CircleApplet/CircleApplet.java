package info.mathsandbox.trig.CircleApplet;

import java.applet.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import static javax.swing.SwingConstants.*;

public class CircleApplet extends Applet {
	private static final long serialVersionUID = 0L;
	public CircleAppletCanvas canvas;
	public JPanel pnlControls;
	public JLabel lblRadius;
	public JLabel lblSides;
	public JLabel lblRotation;
	public JSlider sldRadius;
	public JSlider sldSides;
	public JSlider sldRotation;
	
	public void init() {
		int width = 800;
		int height = 600;
		int pcWidth = 200;
		int lnHeight = 25;
		
		setSize(width, height);
		setBackground(Color.black);
		setLayout(null);
		
		canvas = new CircleAppletCanvas();
		canvas.setLocation(0, 0);
		canvas.setSize(width - 200, height);
		add(canvas, "Center");
		
		pnlControls = new JPanel();
		pnlControls.setBounds(width - pcWidth, 0, pcWidth, height);
		pnlControls.setLayout(null);
		add(pnlControls, "East");
		
		sldRadius = new JSlider(0, height / 2, height / 4);
		lblRadius = new JLabel("Radius: " + sldRadius.getValue() + "px", CENTER);
		lblRadius.setBounds(0, 0 * lnHeight, pcWidth, 20);
		sldRadius.setBounds(0, 1 * lnHeight, pcWidth, 20);
		sldRadius.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent evt) {
				canvas.radius = ((JSlider)evt.getSource()).getValue();
				lblRadius.setText("Radius: " + canvas.radius + "px");
				canvas.repaint();
			}
		});
		
		sldSides = new JSlider(3, 96, 3);
		lblSides = new JLabel("Sides: " + sldSides.getValue() + "", CENTER);
		lblSides.setBounds(0, 2 * lnHeight, pcWidth, 20);
		sldSides.setBounds(0, 3 * lnHeight, pcWidth, 20);
		sldSides.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent evt) {
				canvas.sides = ((JSlider)evt.getSource()).getValue();
				lblSides.setText("Sides: " + (int)canvas.sides);
				canvas.repaint();
			}
		});
		
		sldRotation = new JSlider(-720, 720, 0);
		lblRotation = new JLabel("Rotation: " + sldRotation.getValue() + "deg", CENTER);
		lblRotation.setBounds(0, 4 * lnHeight, pcWidth, 20);
		sldRotation.setBounds(0, 5 * lnHeight, pcWidth, 20);
		sldRotation.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent evt) {
				canvas.rotation = ((JSlider)evt.getSource()).getValue();
				lblRotation.setText("Rotation: " + canvas.rotation + "deg");
				canvas.repaint();
			}
		});
		
		pnlControls.add(lblRadius);
		pnlControls.add(sldRadius);
		pnlControls.add(lblSides);
		pnlControls.add(sldSides);
		pnlControls.add(lblRotation);
		pnlControls.add(sldRotation);
	}
}

class CircleAppletCanvas extends Canvas {
	private static final long serialVersionUID = 0L;
	public double radius = 250d;
	public double sides = 3d;
	public double rotation = 0d;
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		
		double dinc = 360d / sides;
		double cx = getWidth() / 2;
		double cy = getHeight() / 2;
		
		for (double theta = 0.0d - rotation; theta < 360.0d - rotation; theta += dinc) {
			double dx1 = radius * Math.sin(Math.toRadians(theta));
			double dy1 = radius * Math.cos(Math.toRadians(theta));
			
			double x1 = (getWidth() / 2) + dx1;
			double y1 = (getHeight() / 2) + dy1;
			
			double dx2 = radius * Math.sin(Math.toRadians(theta + dinc));
			double dy2 = radius * Math.cos(Math.toRadians(theta + dinc));
			
			double x2 = (getWidth() / 2) + dx2;
			double y2 = (getHeight() / 2) + dy2;
			
			g.fillRect((int)x1, (int)y1, 1, 1);
			g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
			g.drawArc((int)(cx - radius), (int)(cy - radius), (int)radius * 2, (int)radius * 2, (int)(theta + 270d), (int)(dinc));
		}
	}
}