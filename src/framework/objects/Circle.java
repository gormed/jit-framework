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
 * File: Circle.java
 * Type: framework.objects.Circle
 * 
 * Documentation created: 21.12.2011 - 01:56:42 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects;

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
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 1.0 (15 July 2000)
 */

public class Circle extends CanvasObject implements MouseControl {
	/** The diameter of the circle. */
	protected int diameter;

	/**
	 * Create a new circle at default position with default color.
	 */
	public Circle() {
		super(20, 60);
		diameter = 30;
		color = Color.BLUE;
		Application.getInstance().addMouseControl(this);
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
	public Circle(int xPos, int yPos, int diameter, Color color) {
		super(xPos, yPos);
		this.diameter = diameter;
		this.color = color;
		Application.getInstance().addMouseControl(this);
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

	/* (non-Javadoc)
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
		Point topLeft = new Point(xPosition, yPosition);
		Point bottomRight = new Point(xPosition + diameter, yPosition
				+ diameter);
		if (point.x < topLeft.x || point.x > bottomRight.x) {
			return false;
		}
		if (point.y < topLeft.y || point.y > bottomRight.y) {
			return false;
		}
		return true;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#update(framework.core.Time)
	 */
	@Override
	public void update(Time time) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public void onClick(MouseEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public void onRelease(MouseEvent event) {

	}
}
