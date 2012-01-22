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
 * File: TimerThread.java
 * Type: framework.core.TimerThread
 * 
 * Documentation created: 22.01.2012 - 18:22:55 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.Map.Entry;

import framework.events.TimedControl;

/**
 * The Class TimerThread handles all events that are timed, such as moving an
 * object every second.
 * <p>
 * The timer thread is a single huge timer which takes care of all events.
 * </p>
 * 
 * @author Hans Ferchland
 */
public class TimerThread extends TimerTask {

	/** The timed listener. */
	private HashMap<TimedControl, Long> timedListener;
	
	/** The removed listener. */
	private ArrayList<TimedControl> removeListener;
	
	/** The added listener. */
	private ArrayList<TimedControl> addListener;

	/** The application. */
	private Application application = null;

	/**
	 * Instantiates a new timer thread.
	 * 
	 * @param application
	 *            the application
	 */
	public TimerThread(Application application) {
		this.application = application;
		this.timedListener = new HashMap<TimedControl, Long>(20);
		this.removeListener = new ArrayList<TimedControl>();
		this.addListener = new ArrayList<TimedControl>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		try {
			invokeTimedEvent(new TimedEvent(application,
					System.currentTimeMillis()));
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * Invokes all timed events if their time has come.
	 * 
	 * @param t
	 *            the timed event fired
	 */
	private void invokeTimedEvent(TimedEvent t) {
		
		for (TimedControl tc : addListener) {
			timedListener.put(tc, 0L);
		}
		addListener.clear();
		
		// run through all events
		for (Entry<TimedControl, Long> entry : timedListener.entrySet()) {
			// get us the times called and the listener itself
			Long time = entry.getValue();
			TimedControl l = entry.getKey();
			// if the times called modulo the event-period, the event wants to be called
			if (time % l.getPeriod() == 0)
				// so we call it
				l.onTimedEvent(t);
			// after that, raise the entry-value because we went one millisecond further
			entry.setValue(time + 1);
		}
		
		for (TimedControl tc : removeListener) {
			timedListener.remove(tc);
		}
		removeListener.clear();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#cancel()
	 */
	@Override
	public boolean cancel() {
		return super.cancel();
	}

	/**
	 * Adds a specific timed listner.
	 * 
	 * @param t
	 *            the timed lister to add
	 */
	void addTimedListner(TimedControl t) {
		//timedListener.put(t, 0l);
		addListener.add(t);
	}

	/**
	 * Removes a specific timed listner.
	 * 
	 * @param t
	 *            the timed lister to remove
	 */
	void removeTimedListner(TimedControl t) {
		removeListener.add(t);
	}
}
