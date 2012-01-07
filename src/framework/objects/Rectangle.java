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
 * File: Rectangle.java
 * Type: framework.objects.Rectangle
 * 
 * Documentation created: 21.12.2011 - 01:51:35 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects;

import java.awt.Color;
import java.awt.Point;

import framework.core.Canvas;


/**
 * The Class Rectangle that is used to draw colored rectangles, receive events,
 * check collision and so on.
 * 
 * @author Hans Ferchland
 */
public class Rectangle extends Square {

	/** The width of the rectangle. */
	protected int width;

	/** The height of the rectangle. */
	protected int height;

	/**
	 * Instantiates a new rectangle at default position and default size.
	 */
	public Rectangle() {
		super();
		width = 30;
		height = 60;
		color = Color.red;
	}
	
	/**
	 * Instantiates a new rectangle with given position, color and size.
	 *
	 * @param xPos the initial x position
	 * @param yPos the initial y position
	 * @param width the initial width
	 * @param height the initial height
	 * @param color the color
	 */
	public Rectangle(int xPos, int yPos, int width, int height, Color color) {
		super();
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	/**
	 * Changes the size of the rectangle.
	 * 
	 * newWidth and newHeight must be >= 0.
	 * 
	 * @param newWidth
	 *            the new width
	 * @param newHeight
	 *            the new height
	 */
	public void changeSize(int newWidth, int newHeight) {
		width = newWidth;
		height = newHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.Square#changeSize(int)
	 */
	@Override
	public void changeSize(int newSize) {
		width = newSize;
		height = newSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.Square#draw()
	 */
	@Override
	public void draw() {
		if (isVisible()) {
			Canvas canvas = Canvas.getCanvas();
			canvas.draw(this, color, new java.awt.Rectangle(xPosition,
					yPosition, width, height));
		}
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

}
