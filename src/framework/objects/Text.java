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
 * File: Text.java
 * Type: framework.objects.Text
 * 
 * Documentation created: 31.01.2012 - 09:16:14 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import framework.core.Time;
import framework.objects.base.AbstractText;


/**
 * The Class Text displays text on a given position, with a certain color and font.
 * 
 * @author Hans Ferchland
 */
public class Text extends AbstractText {

	/**
	 * Instantiates a new text.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 * @param text the text
	 */
	public Text(int xPos, int yPos, String text) {
		super(xPos, yPos, text);
	}
	
	/**
	 * Instantiates a new text with a given position, text, font and color.
	 *
	 * @param xPos the initial x position
	 * @param yPos the initial y position
	 * @param text the initial text
	 * @param font the initial font
	 * @param color the color
	 */
	public Text(int xPos, int yPos, String text, Font font, Color color) {
		super(xPos, yPos, text, font, color);
	}

	/* (non-Javadoc)
	 * @see framework.objects.base.AbstractText#update(framework.core.Time)
	 */
	@Override
	public void update(Time time) {
		
	}

	/* (non-Javadoc)
	 * @see framework.objects.base.AbstractText#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public void onClick(MouseEvent event) {
		
	}

	/* (non-Javadoc)
	 * @see framework.objects.base.AbstractText#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public void onRelease(MouseEvent event) {
		
	}

	
}
