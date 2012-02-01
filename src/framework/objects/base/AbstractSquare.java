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
 * File: Square.java
 * Type: framework.objects.Square
 * 
 * Documentation created: 22.01.2012 - 18:22:55 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects.base;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import framework.core.Application;
import framework.core.Canvas;
import framework.core.Time;

import framework.events.MouseControl;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 1.0 (15 July 2000)
 */

public abstract class AbstractSquare extends CanvasObject implements MouseControl {

	/** The size of the square. */
	private int size;

	/**
	 * Create a new square at default position with default color.
	 */
	public AbstractSquare() {
		super(60, 50);
		size = 30;
		color = Color.red;
		Application.getInstance().addMouseControl(this);
	}

	/**
	 * Create a new square at a given position and size with given color.
	 * 
	 * @param xPos
	 *            the initial x position
	 * @param yPos
	 *            the initial y position
	 * @param size
	 *            the initial size
	 * @param color
	 *            the color
	 */
	public AbstractSquare(int xPos, int yPos, int size, Color color) {
		super(xPos, yPos);
		this.size = size;
		this.color = color;
		Application.getInstance().addMouseControl(this);
	}

	/**
	 * Change the size to the new size (in pixels). Size must be >= 0.
	 * 
	 * @param newSize
	 *            the new size
	 */
	public void changeSize(int newSize) {
		erase();
		size = newSize;
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
	 * Draw the square with current specifications on screen.
	 */
	@Override
	public void draw() {
		if (isVisible()) {
			Canvas canvas = Canvas.getCanvas();
			canvas.draw(this, color, new java.awt.Rectangle(xPosition,
					yPosition, size, size));
		}
	}

	/**
	 * Erase the square from screen.
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
		Point topLeft = new Point(xPosition, yPosition);
		Point bottomRight = new Point(xPosition + size, yPosition + size);
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
