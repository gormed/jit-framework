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
 * File: UpdateObject.java
 * Type: framework.core.UpdateObject
 * 
 * Documentation created: 21.12.2011 - 01:52:09 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import java.awt.event.MouseEvent;

import framework.interfaces.Clickable;
import framework.interfaces.Collisionable;
import framework.interfaces.Updateable;

/**
 * The Class UpdateObject is an abstract base-class for objects on the canvas.
 * 
 * Collects all important interfaces. Adds possibility to update, check
 * collision and listen to clicks.
 * 
 * @author Hans
 */
public abstract class UpdateObject implements Updateable, Collisionable,
		Clickable {

	/**
	 * Instantiates a new update object.
	 */
	public UpdateObject() {
		Application.getInstance().addUpdateObject(this);
	}

	/**
	 * Disposes the object, means that it won't be updated anymore, the object
	 * will be drawn until makeInvisible() is called. After dispose() and
	 * makeInvisible() was called the object will be deleted!
	 * 
	 * @return true, if successful disposed
	 */
	public boolean dispose() {
		try {
			Application.getInstance().removeUpdateObject(this);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			System.out.println("Object " + this.toString() + " disposed!");
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Updateable#update(interfaces.Time)
	 */
	@Override
	public abstract void update(Time time);

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Clickable#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public abstract void onClick(MouseEvent event);

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Clickable#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public abstract void onRelease(MouseEvent event);

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.Collisionable#checkCollision(interfaces.Collisionable)
	 */
	@Override
	public boolean checkCollision(Collisionable collisionable) {
		return false;
	}

}
