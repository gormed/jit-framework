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
 * File: AbstractText.java
 * Type: framework.objects.base.AbstractText
 * 
 * Documentation created: 31.01.2012 - 09:13:54 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import framework.core.Canvas;
import framework.core.Time;


/**
 * The Class Text displays text on a given position, with a certain color and font.
 * 
 * @author Hans Ferchland
 */
public abstract class AbstractText extends CanvasObject {

	/** The text to display. */
	private String text;
	
	/** The font used for text-painting. */
	private Font font;

	/**
	 * Instantiates a new text with position and text.
	 *
	 * @param xPos the initial x position
	 * @param yPos the initial y position
	 * @param text the initial text
	 */
	public AbstractText(int xPos, int yPos, String text) {
		super(xPos, yPos);
		this.text = text;
		this.font = new Font("helvetica", Font.BOLD, 14);
		Canvas.getCanvas().setFont(font);
		color = Color.black;
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
	public AbstractText(int xPos, int yPos, String text, Font font, Color color) {
		super(xPos, yPos);
		this.text = text;
		this.font = font;
		this.color = color;
		Canvas.getCanvas().setFont(font);
		Canvas.getCanvas().draw(this, color, null);
	}
	
	/* (non-Javadoc)
	 * @see objects.CanvasObject#draw()
	 */
	@Override
	public void draw() {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.setForegroundColor(color);
            canvas.setFont(font);
            canvas.drawString(text, xPosition, yPosition);
        }
	}
	
    /* (non-Javadoc)
     * @see CanvasObject#changeColor(java.awt.Color)
     */
    @Override
    public void changeColor(Color newColor) {
    	super.changeColor(newColor);
    	draw();
    }
    
    /**
     * Changes the text to display.
     *
     * @param text the new text
     */
    public void changeText(String text) {
    	this.text = text;
    }
    
    /**
     * Erase the text from screen.
     */
    protected void erase()
    {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.eraseString(text, xPosition, yPosition);
        }
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
