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
 * File: Time.java
 * Type: framework.core.Time
 * 
 * Documentation created: 21.12.2011 - 01:52:09 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

/**
 * The class Time for calculating time-gaps between update-calls, normally an
 * gap should be 16 ms. 
 * <p>The class measures in milliseconds! If you use second
 * base formula for movement or rotation, multiply by 0.001f.</p>
 * 
 * @author Hans Ferchland
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
