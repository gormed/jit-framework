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
 * File: UpdateThread.java
 * Type: framework.core.UpdateThread
 * 
 * Documentation created: 22.01.2012 - 18:22:56 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import java.util.concurrent.TimeUnit;

/**
 * The Class UpdateThread is responsible for the looping for animation-purposes.
 * 
 * <p>
 * The <code>UpdateThread</code> loops the applications instructions until the
 * user quits. This is done in a separate thread for optimal performance issues.
 * The desired gap between two ticks is 16 ms which means approximately 60 calls
 * the second.
 * <p>
 * 
 * <p>
 * To retrieve the actual time between calls use the time-state given in every
 * <code>update(Time timeState)</code> method. See the class <code>Time</code> for more.
 * </p>
 * 
 * @author Hans Ferchland
 * @version JIT Framework 1.0
 * @see Time
 * @see Application
 */

public class UpdateThread extends Thread {

	/** The application-reference for the updating. */
	private Application application;

	/**
	 * Instantiates a new update thread.
	 * 
	 * @param app
	 *            the app
	 */
	public UpdateThread(Application app) {
		application = app;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (application.isRunning() && !isInterrupted()) {

			application.applicationTick();
			try {
				TimeUnit.MILLISECONDS.sleep(15);
			} catch (InterruptedException e) {
				interrupt();
			}

		}
	}

}
