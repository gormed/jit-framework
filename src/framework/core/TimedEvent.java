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
 * File: TimedEvent.java
 * Type: framework.core.TimedEvent
 * 
 * Documentation created: 21.12.2011 - 01:52:09 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import java.util.EventObject;

/**
 * The Class TimedEvent is an Event special for timed things.
 * <p>
 * Occurs if a timed-control is listening for a special time. It stores the
 * time it is occurred and the firing object, the application.
 * </p>
 * 
 * @author Hans Ferchland
 */
public class TimedEvent extends EventObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The execution time. */
	long mExecutionTime = 0;

	/**
	 * Instantiates a new timed event.
	 * 
	 * @param source
	 *            the source
	 * @param time
	 *            the time
	 */
	public TimedEvent(Object source, long time) {
		super(source);
		mExecutionTime = time;
	}

	/**
	 * Gets the execution time.
	 * 
	 * @return the execution time
	 */
	public long getExecutionTime() {
		return mExecutionTime;
	}

}
