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
 * Documentation created: 31.01.2012 - 09:18:07 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects;

import java.awt.*;
import java.awt.event.MouseEvent;
import framework.core.Time;

import framework.objects.base.AbstractSquare;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 * @author Michael Kölling and David J. Barnes
 * @author Hans Ferchland
 * @version 1.1 (04.02.2012)
 */

public class Square extends AbstractSquare {

	/**
	 * Create a new square at default position with default color.
	 */
	public Square() {
		super();
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
	public Square(int xPos, int yPos, int size, Color color) {
		super(xPos, yPos, size, color);
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
