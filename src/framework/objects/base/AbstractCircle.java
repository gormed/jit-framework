/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JIT Framework Project (c) 2011 - 2012 by Hans Ferchland
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * JIT Framework is a framework for simple graphics-displaying on a JFrame in Java. 
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
 * Project: JIT Framework
 * File: AbstractCircle.java
 * Type: framework.objects.base.AbstractCircle
 * 
 * Documentation created: 13.02.2012 - 13:16:21 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects.base;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.*;

import framework.core.Application;
import framework.core.Canvas;
import framework.core.Time;

import framework.events.MouseControl;

/**
 * An abstract circle class, that can be manipulated and that draws itself on a
 * canvas.
 * 
 * <p>
 * Using this class provides an easy way to implement the methods
 * <code>public abstract void update(Time time)</code>,
 * <code>public abstract void
 * onClick(MouseEvent event)</code> and
 * <code>public abstract void onRelease(MouseEvent event)</code>.
 * </p>
 * <p>
 * e.g.:
 * </p>
 * <code>
 * <pre>
 * 		
 * 	// creates a new inner class with base-class from 
 * 	// AbstractCircle and implement the abstract methods
 * 	AbstractCircle circle = new AbstractCircle(10, 10, 5, Color.RED) {
 * 		public void update(Time time) {
 * 			// do nothing continuously
 * 		}
 * 		public void onRelease(MouseEvent event) {
 * 			// on releasing the object with mouse, change diameter back to 5
 * 			changeSize(5);
 * 		}
 * 		public void onClick(MouseEvent event) {
 * 			// on clicking the object with mouse, change diameter to 10
 * 			changeSize(10);
 * 		}
 * 	};
 * 	// show the circle
 * 	circle.makeVisible();
 * </pre>
 * </code>
 * 
 * 
 * @author Michael Kölling and David J. Barnes
 * @author Hans Ferchland (enhanced)
 * @version 1.1 (01.02.2012)
 */

public abstract class AbstractCircle extends CanvasObject implements
		MouseControl {
	/** The diameter of the circle. */
	protected int diameter;

	/**
	 * Create a new circle at default position with default color.
	 */
	public AbstractCircle() {
		super(20, 60);
		diameter = 30;
		color = Color.BLUE;
		addMouseControl();
	}

	/**
	 * Create a new circle at a given position, with color and diameter.
	 * 
	 * @param xPos
	 *            the initial x position
	 * @param yPos
	 *            the initial y position
	 * @param diameter
	 *            the initial diameter
	 * @param color
	 *            the initial color
	 */
	public AbstractCircle(int xPos, int yPos, int diameter, Color color) {
		super(xPos, yPos);
		this.diameter = diameter;
		this.color = color;
		addMouseControl();
	}

	/**
	 * Change the size to the new size (in pixels). Size must be >= 0.
	 * 
	 * @param newDiameter
	 *            the new diameter
	 */
	public void changeSize(int newDiameter) {
		erase();
		diameter = newDiameter;
		draw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CanvasObject#changeColor(java.awt.Color)
	 */
	@Override
	public void changeColor(Color newColor) {
		super.changeColor(newColor);
		draw();
	}

	/**
	 * Draw the circle with current specifications on screen.
	 */
	@Override
	public void draw() {
		if (isVisible()) {
			Canvas canvas = Canvas.getCanvas();
			canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition,
					diameter, diameter));
		}
	}

	/**
	 * Erase the circle on screen.
	 */
	protected void erase() {
		if (isVisible()) {
			Canvas canvas = Canvas.getCanvas();
			canvas.erase(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#dispose()
	 */
	@Override
	public boolean dispose() {

		Application.getInstance().removeMouseControl(this);
		return super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.CanvasObject#containsPoint(java.awt.Point)
	 */
	@Override
	protected boolean containsPoint(Point point) {
		Point center = new Point((int) (xPosition + diameter / 2),
				(int) (yPosition + diameter / 2));

		double distance = point.distance(center);
		
		if (distance <= diameter/2)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		if (containsPoint(new Point(event.getX(), event.getY()))) {
			clickStarted = true;
			onClick(event);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		if (containsPoint(new Point(event.getX(), event.getY()))
				&& clickStarted) {
			onRelease(event);
		}
		clickStarted = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see events.MouseControl#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent event) {

	}
	
	/* (non-Javadoc)
	 * @see framework.events.MouseControl#addMouseControl()
	 */
	@Override
	public void addMouseControl() {
		Application.getInstance().addMouseControl(this);
	}
	
	/* (non-Javadoc)
	 * @see framework.events.MouseControl#removeMouseControl()
	 */
	@Override
	public void removeMouseControl() {
		Application.getInstance().removeMouseControl(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#update(framework.core.Time)
	 */
	@Override
	public abstract void update(Time time);

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public abstract void onClick(MouseEvent event);

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public abstract void onRelease(MouseEvent event);
}
