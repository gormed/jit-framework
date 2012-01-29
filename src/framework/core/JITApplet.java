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
 * File: JITApplet.java
 * Type: framework.core.JITApplet
 * 
 * Documentation created: 29.01.2012 - 13:19:52 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

/**
 * The Class JITApplet for using the JITFramework in an applet.
 * 
 * <p>
 * To use the JITApplet you need to inherit this class (because it is abstact).
 * After this you need to place your initialization code in the
 * <code>init()</code> method. <b>Note</b> that you need to make a call to the
 * <code>Application</code>'s initializer:
 * <code>initialize(JITApplet applet)</code> with the applet (this) as
 * parameter.
 * </p>
 * 
 * <p>
 * If you override the <code>start()</code> or <code>stop()</code> method make
 * sure you call their super-method!
 * </p>
 * 
 * <p>
 * <b>Never</b> override the <code>paint(Graphics g)</code> or
 * <code>update(Graphics g)</code> method unless you know what you are doing!
 * </p>
 * 
 * @version JITFramework 1.1
 * @author Hans Ferchland
 */
public abstract class JITApplet extends Applet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5430727807703927042L;

	/** The image to paint the content on. */
	private Image canvasImage;

	/** The application. */
	protected Application application;

	/**
	 * Sets the canvas image.
	 * 
	 * @param canvasImage
	 *            the canvas image to draw
	 */
	void setCanvasImage(Image canvasImage) {
		this.canvasImage = canvasImage;
	}

	/**
	 * Sets the application.
	 * 
	 * @param application
	 *            the new application
	 */
	void setApplication(Application application) {
		this.application = application;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#start()
	 */
	@Override
	public void start() {
		application.resume();
		super.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#stop()
	 */
	@Override
	public void stop() {
		application.pause();
		super.stop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#init()
	 */
	@Override
	public void init() {
		super.init();
		application = Application.getInstance();
		application.initialize(this);
		application.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(canvasImage, 0, 0, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		paint(g);
	}

}
