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
 * File: Line.java
 * Type: framework.objects.Line
 * 
 * Documentation created: 22.01.2012 - 18:22:55 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import framework.core.Canvas;
import framework.core.Time;


/**
 * The Class Line defines simple lines from a staring point to an end point.
 * <p>
 * You cannot make lines thicker or thinner!
 * </p>
 *
 * @author Hans Ferchland
 */
public class Line extends CanvasObject {

	/** The start point. */
	private Point start;
	
	/** The end point. */
	private Point end;
	
	/**
	 * Instantiates a new line with default parameter.
	 *
	 */
	public Line() {
		super(0, 0);
		color = Color.black;
		start = new Point(20, 20);
		end = new Point(60, 30);
		Canvas.getCanvas().draw(this, color, null);
	}
	
	/**
	 * Instantiates a new line with a given start- and end-point and color.
	 *
	 * @param start the initial start position of the line
	 * @param end the initial end position of the line
	 * @param color the color of the line
	 */
	public Line(Point start, Point end, Color color) {
		super(0, 0);
		this.color = color;
		this.start = start;
		this.end = end;
		Canvas.getCanvas().draw(this, color, null);
	}

	/**
	 * Sets the start point.
	 *
	 * @param newStart the new start
	 * @param newEnd the new end
	 */
	public void setStartPoint(Point newStart, Point newEnd) {
		start = newStart;
		end = newEnd;
	}
	
	/* (non-Javadoc)
	 * @see objects.CanvasObject#draw()
	 */
	@Override
	public void draw() {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.setForegroundColor(color);
            canvas.drawLine(start.x, start.y, end.x, end.y);
        }
	}

    /**
     * Erase the line from screen.
     */
	protected void erase()
    {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

	/* (non-Javadoc)
	 * @see framework.core.UpdateObject#update(framework.core.Time)
	 */
	@Override
	public void update(Time time) {
		
	}

	/* (non-Javadoc)
	 * @see framework.core.UpdateObject#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public void onClick(MouseEvent event) {
		
	}

	/* (non-Javadoc)
	 * @see framework.core.UpdateObject#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public void onRelease(MouseEvent event) {
		
	}
    
}
