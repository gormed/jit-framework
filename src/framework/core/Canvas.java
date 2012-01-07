/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * HSHLFramework BlueJ Project (c) 2011 - 2011 by Hans Ferchland
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * HSHLFramework BlueJ is a framework for simple graphics-displaying on a JFrame in Java. 
 * The project was created for educational purposes and may be used under the GNU 
 * Public license only.
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * GNU Public License
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Email me for any questions: hans.ferchland[at]gmx.de
 * 
 * Project: HSHLFramework BlueJ
 * File: Canvas.java
 * Type: framework.core.Canvas
 * 
 * Documentation created: 21.12.2011 - 01:52:09 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import javax.swing.*;

import framework.events.KeyboardControl;
import framework.events.MouseControl;
import framework.events.WindowControl;

import framework.objects.CanvasObject;

import java.awt.*;
import java.util.*;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas. This is
 * a modification of the general purpose Canvas, specially made for the BlueJ
 * "shapes" example.
 * 
 * @author Bruce Quig
 * @author Michael Kölling (mik)
 * @author Hans Ferchland (modified)
 * 
 * @version 1.6 (shapes)
 */

public class Canvas {
	// Note: The implementation of this class (specifically the handling of
	// shape identity and colors) is slightly more complex than necessary.
	// This is done on purpose to keep the interface and instance fields of the
	// shape objects in this project clean and simple for educational
	// purposes.

	/** The canvas singleton. */
	private static Canvas canvasSingleton;

	/**
	 * Factory method to get the canvas singleton object.
	 * 
	 * @return the canvas
	 */
	public static Canvas getCanvas() {
		if (canvasSingleton == null) {
			canvasSingleton = new Canvas("No Title", 300, 300, Color.white);
		}
		canvasSingleton.setVisible(true);
		return canvasSingleton;
	}

	// ----- instance part -----

	/** The frame. */
	private JFrame frame;

	/** The default window control. */
	private WindowControl windowControl;

	/** The canvas. */
	private CanvasPane canvas;

	/** The graphic. */
	private Graphics2D graphic;

	/** The background colour. */
	private Color backgroundColor;

	/** The canvas image. */
	private Image canvasImage;

	/** The objects. */
	private ArrayList<CanvasObject> objects;

	/** The shapes. */
	private HashMap<Object, ShapeDescription> shapes;

	/**
	 * Create a Canvas.
	 * 
	 * @param title
	 *            title to appear in Canvas Frame
	 * @param width
	 *            the desired width for the canvas
	 * @param height
	 *            the desired height for the canvas
	 * @param bgColour
	 *            the bg colour
	 */
	private Canvas(String title, int width, int height, Color bgColour) {
		canvas = new CanvasPane();
		canvas.setPreferredSize(new Dimension(width, height));

		backgroundColor = bgColour;

		frame = new JFrame();
		frame.setContentPane(canvas);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		objects = new ArrayList<CanvasObject>();
		shapes = new HashMap<Object, ShapeDescription>();
	}

	/**
	 * Gets the frame.
	 * 
	 * @return the frame
	 */
	protected JFrame getFrame() {
		return frame;
	}

	/**
	 * Gets the pane.
	 * 
	 * @return the pane
	 */
	protected CanvasPane getPane() {
		return canvas;
	}

	/**
	 * Adds the window control or a subclass.
	 * 
	 * @param control
	 *            the control
	 */
	protected void addWindowControl(WindowControl control) {
		frame.removeWindowListener(windowControl);
		if (windowControl == null)
			windowControl = control;
		frame.addWindowListener(control);
	}

	/**
	 * Removes the window control or a subclass.
	 * 
	 * @param control
	 *            the control
	 */
	protected void removeWindowControl(WindowControl control) {
		frame.removeWindowListener(control);
	}

	/**
	 * Adds the mouse control or a subclass.
	 * 
	 * @param control
	 *            the control
	 */
	protected void addMouseControl(MouseControl control) {
		canvas.removeMouseListener(control);
		canvas.removeMouseMotionListener(control);
		canvas.removeMouseWheelListener(control);
		canvas.addMouseListener(control);
		canvas.addMouseMotionListener(control);
		canvas.addMouseWheelListener(control);
	}

	/**
	 * Removes the mouse control or a subclass.
	 * 
	 * @param control
	 *            the control
	 */
	protected void removeMouseControl(MouseControl control) {
		canvas.removeMouseListener(control);
		canvas.removeMouseMotionListener(control);
		canvas.removeMouseWheelListener(control);
	}

	/**
	 * Adds the keyboard control or a subclass.
	 * 
	 * @param control
	 *            the control
	 */
	protected void addKeyboardControl(KeyboardControl control) {
		frame.removeKeyListener(control);
		frame.addKeyListener(control);
	}

	/**
	 * Removes the keyboard control or a subclass.
	 * 
	 * @param control
	 *            the control
	 */
	protected void removeKeyboardControl(KeyboardControl control) {
		frame.removeKeyListener(control);
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	protected void setTitle(String title) {
		frame.setTitle(title);
	}

	/**
	 * Sets the background.
	 * 
	 * @param color
	 *            the new background
	 */
	public void setBackground(Color color) {
		backgroundColor = color;
	}

	/**
	 * Gets the canvas objects iterator.
	 * 
	 * @return the canvas objects iterator
	 */
	public Iterator<CanvasObject> getCanvasObjectsIterator() {
		return objects.iterator();
	}

	/**
	 * Set the canvas visibility and brings canvas to the front of screen when
	 * made visible. This method can also be used to bring an already visible
	 * canvas to the front of other windows.
	 * 
	 * @param visible
	 *            boolean value representing the desired visibility of the
	 *            canvas (true or false)
	 */
	protected void setVisible(boolean visible) {
		if (graphic == null) {
			// first time: instantiate the offscreen image and fill it with
			// the background colour
			Dimension size = canvas.getSize();
			canvasImage = canvas.createImage(size.width, size.height);
			graphic = (Graphics2D) canvasImage.getGraphics();
			graphic.setColor(backgroundColor);
			graphic.fillRect(0, 0, size.width, size.height);
			graphic.setColor(Color.black);
		}
		frame.setVisible(visible);
	}

	/**
	 * Sets the dimension of the canvas/frame.
	 * 
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	protected void setDimensions(int width, int height) {
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvasImage = canvas.createImage(width, height);
		canvasImage.setAccelerationPriority(1);
		graphic = (Graphics2D) canvasImage.getGraphics();
		graphic.setColor(backgroundColor);
		graphic.fillRect(0, 0, width, height);
		graphic.setColor(Color.black);
		frame.pack();
	}

	/**
	 * Force the drawing of a given shape onto the canvas. The CanvasObject will
	 * be added to drawing here.
	 * 
	 * @param referenceObject
	 *            an object to define identity for this shape
	 * @param color
	 *            the color of the shape
	 * @param shape
	 *            the shape object to be drawn on the canvas
	 */
	// Note: this is a slightly backwards way of maintaining the shape
	// objects. It is carefully designed to keep the visible shape
	// interfaces in this project clean and simple for educational purposes.
	public void draw(CanvasObject referenceObject, Color color, Shape shape) {
		objects.remove(referenceObject); // just in case it was already
											// there
		objects.add(referenceObject); // add at the end
		if (shape != null) {
			shapes.put(referenceObject, new ShapeDescription(shape, color));
			shapes.get(referenceObject).draw(graphic);
		}
	}

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image
	 *            the image
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return returns boolean value representing whether the image was
	 *         completely loaded
	 */
	public boolean drawImage(Image image, int x, int y) {
		boolean result = graphic.drawImage(image, x, y, null);
		// canvas.repaint();
		return result;
	}

	/**
	 * Draws a String on the Canvas.
	 * 
	 * @param text
	 *            the text
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void drawString(String text, int x, int y) {
		graphic.drawString(text, x, y);
		// canvas.repaint();
	}

	/**
	 * Erases a String on the Canvas.
	 * 
	 * @param text
	 *            the text
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void eraseString(String text, int x, int y) {
		Color original = graphic.getColor();
		graphic.setColor(backgroundColor);
		graphic.drawString(text, x, y);
		graphic.setColor(original);
		// canvas.repaint();
	}

	/**
	 * Draws a line on the Canvas.
	 * 
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		graphic.drawLine(x1, y1, x2, y2);
		// canvas.repaint();
	}

	/**
	 * Erase a given shape's from the screen.
	 * 
	 * @param referenceObject
	 *            the shape object to be erased
	 */
	public void erase(Object referenceObject) {
		objects.remove(referenceObject); // just in case it was already
											// there
		shapes.remove(referenceObject);
		refresh();
	}

	/**
	 * Set the foreground colour of the Canvas.
	 * 
	 * @param color
	 *            the new foreground color
	 */
	public void setForegroundColor(Color color) {
		if (color != null)
			graphic.setColor(color);
	}

	/**
	 * Wait for a specified number of milliseconds before finishing. This
	 * provides an easy way to specify a small delay which can be used when
	 * producing animations.
	 * 
	 * @param milliseconds
	 *            the number
	 */
	public void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {
			// ignoring exception at the moment
		}
	}

	/**
	 * changes the current Font used on the Canvas.
	 * 
	 * @param newFont
	 *            the new font
	 */
	public void setFont(Font newFont) {
		graphic.setFont(newFont);
	}

	/**
	 * Returns the current font of the canvas.
	 * 
	 * @return the font currently in use
	 **/
	public Font getFont() {
		return graphic.getFont();
	}

	/**
	 * Redraw all shapes currently on the Canvas.
	 */
	protected void refresh() {
		erase();

		@SuppressWarnings("unchecked")
		ArrayList<CanvasObject> clone = (ArrayList<CanvasObject>) objects
				.clone();

		for (CanvasObject c : clone) {
			if (shapes.containsKey(c))
				((ShapeDescription) shapes.get(c)).draw(graphic);
		}

		canvas.repaint();
	}

	/**
	 * Redraw.
	 */
	protected void redraw() {
		erase();

		@SuppressWarnings("unchecked")
		ArrayList<CanvasObject> clone = (ArrayList<CanvasObject>) objects
				.clone();

		for (CanvasObject c : clone) {
			c.draw();
		}

		canvas.repaint();
	}

	/**
	 * Erase the whole canvas. (Does not repaint.)
	 */
	private void erase() {
		Color original = graphic.getColor();
		graphic.setColor(backgroundColor);
		Dimension size = canvas.getSize();
		graphic.fill(new Rectangle(0, 0, size.width, size.height));
		graphic.setColor(original);
	}

	/**
	 * Terminates the canvas frame to allow termination.
	 */
	protected void terminate() {
		frame.setVisible(false);
		// frame.dispose();
		frame = null;
	}

	/************************************************************************
	 * Inner class CanvasPane - the actual canvas component contained in the
	 * Canvas frame. This is essentially a JPanel with added capability to
	 * refresh the image drawn on it.
	 */
	protected class CanvasPane extends JPanel {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 7277188143012062070L;

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paint(java.awt.Graphics)
		 */
		public void paint(Graphics g) {
			g.drawImage(canvasImage, 0, 0, null);
		}
	}

	/**
	 * The Class ShapeDescription.
	 */
	private class ShapeDescription {

		/** The shape. */
		private Shape shape;

		/** The color string. */
		private Color color;

		/**
		 * Instantiates a new shape description.
		 * 
		 * @param shape
		 *            the shape
		 * @param color
		 *            the color
		 */
		public ShapeDescription(Shape shape, Color color) {
			this.shape = shape;
			this.color = color;
		}

		/**
		 * Draw.
		 * 
		 * @param graphic
		 *            the graphic
		 */
		public void draw(Graphics2D graphic) {
			setForegroundColor(color);
			if (shape != null)
				graphic.fill(shape);
		}
	}

}
