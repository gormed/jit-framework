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
 * File: TimedControl.java
 * Type: framework.events.TimedControl
 * 
 * Documentation created: 21.12.2011 - 01:52:04 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package framework.events;

import java.util.EventListener;

import framework.core.TimedEvent;


/**
 * The listener interface for receiving timed events.
 * <p>
 * The class that is interested in processing a timed event implements this
 * interface, and the object created with that class is registered with a
 * component using the applications
 * <code>Application.getInstance().addTimedObject(this)</code> method. When the
 * timed event occurs, that object's appropriate method
 * <code>onTimedEvent</code> is invoked.
 * </p>
 * 
 * <p>
 * A timed event can occur every millisecond, but screen-drawing is really
 * expensive, so 10 milliseconds should be the lowest value! Just define a field
 * for your event-period e.g. <code>private int period;</code> and return that
 * value in the <code>getPeriod()</code> method.
 * </p>
 * 
 * @author Hans Ferchland
 * @see EventListener
 */
public interface TimedControl extends EventListener {

	/**
	 * On timed event that will be executed all getPeriod()-milliseconds.
	 * 
	 * @param t
	 *            the t
	 */
	public void onTimedEvent(TimedEvent t);

	/**
	 * Gets the period. This is the times to tick the listener wants to hear.
	 * e.g. 100 for 10 times per second. Every base tick occurs every
	 * millisecond.
	 * 
	 * @return the period
	 */
	public long getPeriod();
}
