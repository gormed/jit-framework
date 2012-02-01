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
 * File: Rectangle.java
 * Type: framework.objects.Rectangle
 * 
 * Documentation created: 31.01.2012 - 09:11:30 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects;

import java.awt.Color;
import java.awt.event.MouseEvent;

import framework.core.Time;
import framework.objects.base.AbstractRectangle;


/**
 * The Class Rectangle that is used to draw colored rectangles, receive events,
 * check collision and so on.
 * 
 * @author Hans Ferchland
 */
public class Rectangle extends AbstractRectangle {

	/**
	 * Instantiates a new rectangle at default position and default size.
	 */
	public Rectangle() {
		super();
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
		super(xPos, yPos, width, height, color);
	}
	
	/* (non-Javadoc)
	 * @see framework.objects.base.AbstractSquare#update(framework.core.Time)
	 */
	@Override
	public void update(Time time) {
	}

	/* (non-Javadoc)
	 * @see framework.objects.base.AbstractSquare#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public void onClick(MouseEvent event) {
	}

	/* (non-Javadoc)
	 * @see framework.objects.base.AbstractSquare#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public void onRelease(MouseEvent event) {
	}

}
