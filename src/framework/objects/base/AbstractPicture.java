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
 * File: AbstractPicture.java
 * Type: framework.objects.base.AbstractPicture
 * 
 * Documentation created: 13.02.2012 - 13:16:50 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.objects.base;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import framework.core.Application;
import framework.core.Canvas;
import framework.core.JITApplet;
import framework.core.Time;

import framework.events.MouseControl;

/**
 * An abstract picture class, that can be used for drawing custom images.
 * <p>
 * Using this class provides an easy way to implement the methods
 * <code>public abstract void update(Time time)</code>,
 * <code>public abstract void
 * onClick(MouseEvent event)</code> and
 * <code>public abstract void onRelease(MouseEvent event)</code>.
 * </p>
 * @author Hans Ferchland
 */
public abstract class AbstractPicture extends CanvasObject implements
		MouseControl {

	/** The image to display. */
	private Image image;

	private int width = 0;

	private int height = 0;

	/**
	 * Instantiates a new picture.
	 * 
	 * @param image
	 *            the image
	 */
	public AbstractPicture(Image image) {
		super(0, 0);
		this.image = image;
		if (image != null) {
			width = image.getWidth(null);
			height = image.getHeight(null);
		}
		Canvas.getCanvas().draw(this, Color.white, null);
		addMouseControl();
	}

	/**
	 * Instantiates a new picture.
	 * <p>
	 * This constructor will set the position and will load your image from your
	 * base directory.
	 * </p>
	 * e.g. "my_picture.jpg" or "pictures//my_picture.jpg"
	 * 
	 * @param xPos
	 *            the initial x position
	 * @param yPos
	 *            the initial y position
	 * @param imagePath
	 *            the image path
	 */
	public AbstractPicture(int xPos, int yPos, String imagePath) {
		super(xPos, yPos);
		this.image = null;

		try {
			if (Application.getInstance().isApplet()) {
				JITApplet applet = Application.getInstance().getApplet();

				image = ImageIO.read(new URL(applet.getCodeBase(), imagePath));

			} else
				image = ImageIO.read(new File(imagePath));
		} catch (IllegalArgumentException iae) {
			System.err
					.println("Could not find image in path: "
							+ imagePath
							+ "! Please check if the image is there. The default path is your project directory!");
		} catch (IOException ioe) {
			System.err.println("Error during image-loading!");
		}

		if (image != null) {
			width = image.getWidth(null);
			height = image.getHeight(null);
		}

		Canvas.getCanvas().draw(this, Color.white, null);
		addMouseControl();
	}
	
	/**
	 * Gets the pictures height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the pictures width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.CanvasObject#draw()
	 */
	@Override
	public void draw() {
		if (isVisible() && image != null) {
			Canvas canvas = Canvas.getCanvas();
			canvas.drawImage(image, xPosition, yPosition);
		}
	}

	/**
	 * Erase the picture from screen.
	 */
	protected void erase() {
		if (isVisible()) {
			
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
		image.flush();
		image = null;
		return super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.CanvasObject#containsPoint(java.awt.Point)
	 */
	@Override
	protected boolean containsPoint(Point point) {
		if (image == null)
			return false;
		Point topLeft = new Point(xPosition, yPosition);
		Point bottomRight = new Point(xPosition + width,
				yPosition + height);
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
			clickStarted = true;
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
	 * @see framework.events.MouseControl#addMouseControl()
	 */
	@Override
	public void addMouseControl() {
		Application.getInstance().addMouseControl(this);
	}
	
	/* (non-Javadoc)
	 * @see framework.events.MouseControl#removeMouseControl()
	 */
	@Override
	public void removeMouseControl() {
		Application.getInstance().removeMouseControl(this);
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
