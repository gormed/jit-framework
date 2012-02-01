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
 * File: Time.java
 * Type: framework.core.Time
 * 
 * Documentation created: 22.01.2012 - 18:22:56 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

/**
 * The class Time for calculating time-gaps between update-calls, usually a
 * time-gap should be around 16 ms.
 * <p>
 * The class measures in <b>milliseconds</b>! If you use second base formula for
 * movement or rotation, multiply by 0.001f.
 * </p>
 * <p>
 * The <code>getTimeGap()</code> method gives the delta time, the time since the
 * last call of the <code>update()</code> method of a <code>CanvasObject</code>
 * and/or <code>UpdateObject</code>. Usually the time-gap should be around 16 ms
 * (60 times the second), but in case you are drawing a lot it can be much
 * higher.
 * </p>
 * <p>
 * The <code>getActualTime()</code> returns the system-time in milliseconds
 * since when the system is running.
 * </p>
 * 
 * @author Hans Ferchland
 * @see UpdateObject
 * @see System
 */
public class Time {

	/** The actual time. */
	long actualTime = 0;

	/** The last time. */
	long lastTime = 0;

	/** The time gap. */
	long timeGap = 0;

	/**
	 * Instantiates a new time-instance.
	 */
	public Time() {
		actualTime = System.currentTimeMillis();
	}

	/**
	 * begins measuring of time.
	 */
	public void begin() {
		actualTime = System.currentTimeMillis();
		timeGap = actualTime - lastTime;
	}

	/**
	 * Ends the time-measurement.
	 */
	public void end() {
		lastTime = actualTime;
	}

	/**
	 * Gets the actual time.
	 * 
	 * @return the actualTime
	 */
	public long getActualTime() {
		return actualTime;
	}

	/**
	 * Gets the last time.
	 * 
	 * @return the lastTime
	 */
	public long getLastTime() {
		return lastTime;
	}

	/**
	 * Gets the time gap.
	 * 
	 * @return the timeGap
	 */
	public long getTimeGap() {
		return timeGap;
	}

}
