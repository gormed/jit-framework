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
 * File: CanvasObject.java
 * Type: framework.objects.CanvasObject
 * 
 * Documentation created: 22.01.2012 - 18:22:57 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects.base;

import java.awt.Color;
import java.awt.Point;

import framework.core.UpdateObject;

/**
 * The abstract class CanvasObject is a base object for all objects that will be
 * painted. If you want to create one inherit this one.
 * 
 * @author Hans Ferchland
 * @see UpdateObject
 */
public abstract class CanvasObject extends UpdateObject {

	/** The x position of the object. */
	protected int xPosition;

	/** The y position of the object. */
	protected int yPosition;

	/** The isVisible flag indicates if an object is drawn or not. */
	private boolean isVisible;

	/** The color of the object. */
	protected Color color;

	/**
	 * The click started flag indicates if the mouse was press over this object.
	 */
	protected boolean clickStarted = false;

	/**
	 * Instantiates a new canvas object.
	 * 
	 * @param xPos
	 *            the x pos
	 * @param yPos
	 *            the y pos
	 */
	public CanvasObject(int xPos, int yPos) {
		super();
		xPosition = xPos;
		yPosition = yPos;
		isVisible = false;
	}

	/**
	 * Gets the position of the object.
	 * 
	 * @return the position
	 */
	public Point getPosition() {
		return new Point(xPosition, yPosition);
	}

	/**
	 * Sets the position of this object.
	 * 
	 * @param xPos
	 *            the x position
	 * @param yPos
	 *            the y position
	 */
	public void setPosition(int xPos, int yPos) {
		xPosition = xPos;
		yPosition = yPos;
	}

	/**
	 * Changes the color of this object.
	 * 
	 * @param newColor
	 *            the new color
	 */
	public void changeColor(Color newColor) {
		color = newColor;
	}

	/**
	 * Draws the CanvasObject on the canvas.
	 */
	public abstract void draw();

	/**
	 * Erase the CanvasObject from screen.
	 */
	protected abstract void erase();

	/**
	 * Make this CanvasObject visible. If it was already visible, do nothing.
	 */
	public void makeVisible() {
		isVisible = true;
		draw();
	}

	/**
	 * Make this CanvasObject invisible. If it was already invisible, do
	 * nothing.
	 */
	public void makeInvisible() {
		erase();
		isVisible = false;
	}

	/**
	 * Checks if a CanvasObject is visible.
	 * 
	 * @return true, if it is visible
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * Check if an object contains a point. Must be implemented for each figure
	 * because the check is special for every shape!
	 * 
	 * @param point
	 *            the point
	 * @return true, if point is contained
	 */
	protected boolean containsPoint(Point point) {
		return false;
	}
}
