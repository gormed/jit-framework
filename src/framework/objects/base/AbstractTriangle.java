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
 * File: AbstractTriangle.java
 * Type: framework.objects.base.AbstractTriangle
 * 
 * Documentation created: 31.01.2012 - 09:13:44 by Hans Ferchland
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
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 1.0 (15 July 2000)
 */

public abstract class AbstractTriangle extends CanvasObject implements MouseControl {

	/** The height. */
	private int height;

	/** The width. */
	private int width;

	/**
	 * Create a new triangle at default position with default color.
	 */
	public AbstractTriangle() {
		super(50, 15);
		height = 30;
		width = 40;
		color = Color.green;
		Application.getInstance().addMouseControl(this);
	}

	/**
	 * Create a new triangle at a given position, with given size and color.
	 * 
	 * @param xPos
	 *            the initial x position
	 * @param yPos
	 *            the initial y position
	 * @param height
	 *            the initial height
	 * @param width
	 *            the initial width
	 * @param color
	 *            the color
	 */
	public AbstractTriangle(int xPos, int yPos, int height, int width, Color color) {
		super(xPos, yPos);
		this.height = height;
		this.width = width;
		this.color = color;
		Application.getInstance().addMouseControl(this);
	}

	/**
	 * Change the size to the new size (in pixels). Size must be >= 0.
	 * 
	 * @param newHeight
	 *            the new height
	 * @param newWidth
	 *            the new width
	 */
	public void changeSize(int newHeight, int newWidth) {
		erase();
		height = newHeight;
		width = newWidth;
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
	 * Draw the triangle with current specifications on screen.
	 */
	@Override
	public void draw() {
		if (isVisible()) {
			Canvas canvas = Canvas.getCanvas();
			int[] xpoints = { xPosition, xPosition + (width / 2),
					xPosition - (width / 2) };
			int[] ypoints = { yPosition, yPosition + height, yPosition + height };
			canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
		}
	}

	/**
	 * Erase the triangle on screen.
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
		Point bottomRight = new Point(xPosition + width, yPosition + height);
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

	/* (non-Javadoc)
	 * @see framework.core.UpdateObject#update(framework.core.Time)
	 */
	@Override
	public abstract void update(Time time);


	/* (non-Javadoc)
	 * @see framework.core.UpdateObject#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public abstract void onClick(MouseEvent event);


	/* (non-Javadoc)
	 * @see framework.core.UpdateObject#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public abstract void onRelease(MouseEvent event);
}
