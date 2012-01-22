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
 * File: Updateable.java
 * Type: framework.interfaces.Updateable
 * 
 * Documentation created: 22.01.2012 - 18:22:54 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.interfaces;

import framework.core.Application;
import framework.core.Time;
import framework.events.KeyboardControl;
import framework.events.MouseControl;
import framework.events.WindowControl;

/**
 * The Interface Updateable for handling the update-objects in the application.
 * 
 * @see Application
 * @author Hans Ferchland
 */
public interface Updateable {

	/**
	 * The update-method is called by the application for all updateable
	 * instances.
	 * <p>
	 * Implement your code for animation here. If you want to check for mouse,
	 * key or window events, use the <code>WindowControl</code>,
	 * <code>MouseControl</code> and/or <code>KeyboardControl</code> interfaces.
	 * </p>
	 *
	 * @param time the time
	 * @see Application
	 * @see WindowControl
	 * @see KeyboardControl
	 * @see MouseControl
	 */
	void update(Time time);
}
