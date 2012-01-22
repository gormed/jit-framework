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
 * File: Application.java
 * Type: framework.core.Application
 * 
 * Documentation created: 22.01.2012 - 18:22:56 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import framework.interfaces.Updateable;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;

import framework.events.KeyboardControl;
import framework.events.MouseControl;
import framework.events.TimedControl;
import framework.events.WindowControl;

/**
 * The Class Application is a singleton for a window frame with separate
 * update-thread.
 * <p>
 * The application handles all the updating and drawing on the window. It is
 * constructed as singleton, so ONLY ONE object is possible. The reference to
 * this one is stored in a static member-variable and the constructor is hidden.
 * You can get the one and only instance by calling
 * <code>Application.getInstance()</code>. On the first time calling this static
 * method, the constructor is executed once to create one instance and will
 * return this instance on later calls.
 * </p>
 * <p>
 * You will start the application after calling the <code>start()</code>-method.
 * After this the application can be terminated with the
 * <code>terminate()</code>-method.
 * </p>
 * <p>
 * You can use <code>pause()</code> and <code>unpause()</code> to handle
 * minimizing the windows. Also a game-menu could be implemented with this.
 * </p>
 * 
 * @author Hans Ferchland
 * @version v1.0
 * 
 */
public final class Application {

	/**
	 * The Enum ApplicationState.
	 * 
	 * <p>
	 * Has the CREATED, INITIALIZING, RUNNING, PAUSED, RESUMING and EXITING
	 * state to describe the applications behavior.
	 * </p>
	 * 
	 * <p>
	 * The CREATED state is the first state of the app, after the constructor
	 * was called the first time.
	 * </p>
	 * <p>
	 * The INITIALIZING state is reached after the start()-method was called.
	 * </p>
	 * <p>
	 * The RUNNING state follows the initialization when the first
	 * applicationTick() occurs.
	 * </p>
	 * <p>
	 * The PAUSED state will pause your application-refresh, call resume() to
	 * unpause.
	 * </p>
	 * <p>
	 * The RESUMING state is active after calling resume() till the application
	 * updated them-self the first time again.
	 * </p>
	 * <p>
	 * The EXITING state occurs after your call to terminate(), after this the
	 * app normally closes.
	 * </p>
	 */
	public enum ApplicationState {

		/**
		 * The CREATED state is the first state of the app, after the
		 * constructor was called the first time.
		 */
		CREATED,

		/**
		 * The INITIALIZING state is reached after the start()-method was
		 * called.
		 */
		INITIALIZING,

		/**
		 * The RUNNING state follows the initialization when the first
		 * applicationTick() occurs.
		 */
		RUNNING,

		/**
		 * The PAUSED state will pause your application-refresh, call resume()
		 * to unpause.
		 */
		PAUSED,

		/**
		 * The RESUMING state is active after calling resume() till the
		 * application updated them-self the first time again.
		 */
		RESUMING,

		/**
		 * The EXITING state occurs after your call to terminate(), after this
		 * the app normally closes.
		 */
		EXITING
	}

	/**
	 * The application-canvas to draw graphics, text, pictures, lines and so on.
	 */
	private Canvas applicationCanvas;

	/**
	 * The update-objects of the application, UpdateObjects normally add
	 * them-self into this List.
	 */
	private ArrayList<Updateable> updateObjects;

	/** The timer thread. */
	private TimerThread timerThread;

	/** The timer. */
	private Timer timer;

	/** The time state measures time between two calls to applicationTick(). */
	private Time timeState;

	/** The isRunning-flag, indicating the state of execution. */
	private boolean isRunning = false;

	/** The application singleton. */
	private static Application applicationSingleton;

	/**
	 * The update thread handles the updating of all your objects and content
	 * separate.
	 */
	private UpdateThread updateThread;

	/**
	 * The applications state, see ApplicationState-enum for more.
	 * 
	 * @see Application.ApplicationState
	 */
	private ApplicationState state;

	/**
	 * Gets the one and only application reference of the Application-class.
	 * 
	 * On the first time calling this static method, the constructor is executed
	 * once to create one instance and will return this instance on later calls.
	 * 
	 * @return the application-instance-singleton
	 */
	public static synchronized Application getInstance() {
		if (applicationSingleton == null)
			applicationSingleton = new Application();
		return applicationSingleton;
	}

	/**
	 * Instantiates a new application.
	 * 
	 */
	private Application() {
		applicationCanvas = Canvas.getCanvas();
		addDefaultWindowControl();
		timeState = new Time();

		updateObjects = new ArrayList<Updateable>();
		updateThread = new UpdateThread(this);

		timerThread = new TimerThread(this);
		timer = new Timer("TimerThread");

		state = ApplicationState.CREATED;
		System.out
				.println("Application created, please call start() if you want to start the application-loop!");
	}

	/**
	 * Starts the application by initiating a infinite gameloop. The "isRunning"
	 * flag indicates the termination of the application.
	 * 
	 * You can terminate the loop by calling the terminate()-method.
	 */
	public void start() {
		if (state == ApplicationState.CREATED) {
			state = ApplicationState.INITIALIZING;
			System.out.println("Application started!");
			isRunning = true;

			updateThread.start();
			timer.schedule(timerThread, 0, 1);

			state = ApplicationState.RUNNING;
		} else {

		}
	}

	/**
	 * Pauses the application-loop.
	 */
	public void pause() {
		if (state != ApplicationState.PAUSED
				&& state != ApplicationState.CREATED) {
			state = ApplicationState.PAUSED;
			isRunning = false;

			if (updateThread != null) {
				updateThread.interrupt();
				updateThread = null;
			}

			System.out.println("Application paused!");
		}
	}

	/**
	 * Resumes the application-loop.
	 */
	public void resume() {
		if (state == ApplicationState.PAUSED) {
			state = ApplicationState.RESUMING;
			isRunning = true;

			if (updateThread == null) {
				updateThread = new UpdateThread(this);
				updateThread.start();
			}

			System.out.println("Application resumed!");
		}
	}

	/**
	 * Terminates the application by interrupting the update thread and
	 * terminating the canvas.
	 */
	public void terminate() {
		state = ApplicationState.EXITING;
		isRunning = false;

		if (updateThread != null) {
			updateThread.interrupt();
			updateThread = null;
		}

		applicationCanvas.terminate();
		System.out.println("Application terminating!");
		System.exit(0);
	}

	/**
	 * Gets the current state of the application.
	 * 
	 * Animation states are defined in the enum Application.ApplicationState
	 * 
	 * @return the current state of the application
	 * @see Application.ApplicationState
	 */
	public ApplicationState getState() {
		return state;
	}

	/**
	 * Checks if the application is currently running.
	 * 
	 * @return true, if running
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Adds a specific update object.
	 * 
	 * @param updateObject
	 *            the update object
	 */
	protected void addUpdateObject(Updateable updateObject) {
		updateObjects.add(updateObject);
	}

	/**
	 * Removes the desired update object.
	 * <p>
	 * This method should only be called if you explicitly don't want to update
	 * a desired object. All <code>Updateables</code> implemented by
	 * <code>UpdateObject</code> are added automatically. All classes inheriting
	 * <code>CanvasObject</code> also will be added automatically through a call
	 * to <code>super()</code> in their constructor.
	 * </p>
	 * 
	 * @param updateObject
	 *            the update object to remove
	 */
	public void removeUpdateObject(Updateable updateObject) {
		updateObjects.remove(updateObject);
	}

	/**
	 * Adds the timed object.
	 * 
	 * @param control
	 *            the control
	 */
	public void addTimedObject(TimedControl control) {
		timerThread.addTimedListner(control);
	}

	/**
	 * Removes the timed object.
	 * 
	 * @param control
	 *            the control
	 */
	public void removeTimedObject(TimedControl control) {
		try {
			timerThread.removeTimedListner(control);
		} catch (Exception e) {
			// do nothing
		}
	}

	/**
	 * Updates all objects that where created an are visible.
	 * 
	 * @param timeState
	 *            the time state
	 */
	private void updateObjects(Time timeState) {
		@SuppressWarnings("unchecked")
		ArrayList<Updateable> cloned = (ArrayList<Updateable>) updateObjects
				.clone();

		for (Updateable u : cloned) {
			u.update(timeState);
		}
	}

	/**
	 * Application tick.
	 * 
	 * This is the main loop method, means this method is called every time
	 * until the frame closes. First time it updates all your objects that need
	 * a update (by calling its update()-method). Than it calls all your objects
	 * draw()-methods.
	 * 
	 */
	protected void applicationTick() {
		state = ApplicationState.RUNNING;

		timeState.begin();
		updateObjects(timeState);

		synchronized (applicationCanvas) {
			applicationCanvas.redraw();
		}

		timeState.end();

	}

	// *******************************************************************************
	// * Frame and Canvas section
	// *******************************************************************************

	/**
	 * Sets the dimensions of the canvas.
	 * 
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setDimensions(int width, int height) {
		applicationCanvas.setDimensions(width, height);
	}

	/**
	 * Gets the dimensions of the canvas.
	 * 
	 * @return the dimensions
	 */
	public Dimension getDimensions() {
		return applicationCanvas.getPane().getSize();
	}

	/**
	 * Sets the visibility of the frame.
	 * 
	 * @param visible
	 *            the new visible
	 */
	public void setVisible(boolean visible) {
		applicationCanvas.setVisible(visible);
	}

	/**
	 * Sets the title of the frame.
	 * 
	 * @param title
	 *            the new title to set
	 */
	public void setTitle(String title) {
		applicationCanvas.setTitle(title);
	}

	/**
	 * Adds the keyboard control to the listeners of the frame.
	 * 
	 * @param control
	 *            the control to add
	 */
	public void addKeyboardControl(KeyboardControl control) {
		applicationCanvas.addKeyboardControl(control);
	}

	/**
	 * Removes the keyboard control from the listeners of the canvas.
	 * 
	 * @param control
	 *            the control to remove
	 */
	public void removeKeyboardControl(KeyboardControl control) {
		applicationCanvas.removeKeyboardControl(control);
	}

	/**
	 * Adds the mouse control to the listeners of the canvas.
	 * 
	 * @param control
	 *            the control to add
	 */
	public void addMouseControl(MouseControl control) {
		applicationCanvas.addMouseControl(control);
	}

	/**
	 * Removes the mouse control from the listeners of the canvas.
	 * 
	 * @param control
	 *            the control to remove
	 */
	public void removeMouseControl(MouseControl control) {
		applicationCanvas.removeMouseControl(control);
	}

	/**
	 * Adds the window control to the listeners of the frame.
	 * 
	 * @param control
	 *            the control to add
	 */
	public void addWindowControl(WindowControl control) {
		applicationCanvas.addWindowControl(control);
	}

	/**
	 * Removes the window control from the listeners of the frame.
	 * 
	 * @param control
	 *            the control to remove
	 */
	public void removeWindowControl(WindowControl control) {
		applicationCanvas.removeWindowControl(control);
	}

	/**
	 * Adds the default window control.
	 */
	protected void addDefaultWindowControl() {
		// add a default control for the applications update thread in special
		applicationCanvas.addWindowControl(new WindowControl() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowOpened(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowOpened(WindowEvent event) {
				// do nothing, you could load some resources here or sth like
				// that
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowIconified(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowIconified(WindowEvent event) {
				Application.getInstance().pause();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowDeiconified(java.awt.event.WindowEvent
			 * )
			 */
			@Override
			public void windowDeiconified(WindowEvent event) {
				Application.getInstance().resume();

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowDeactivated(java.awt.event.WindowEvent
			 * )
			 */
			@Override
			public void windowDeactivated(WindowEvent event) {
				Application.getInstance().pause();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent event) {
				if (Application.getInstance().isRunning())
					Application.getInstance().terminate();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowClosed(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosed(WindowEvent event) {
				// do nothing
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowActivated(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowActivated(WindowEvent event) {
				Application.getInstance().resume();
			}
		});
	}
}
