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
 * File: WorkerThread.java
 * Type: framework.core.WorkerThread
 * 
 * Documentation created: 31.01.2012 - 23:01:02 by Hans Ferchland
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package framework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import framework.core.Application.LoaderFunction;

/**
 * The Class WorkerThread can handle much operations and work.
 * 
 * <p>
 * A worker thread can execute any given function without parameters that
 * returns void! This thread is a daemon thread.
 * </p>
 */
public class WorkerThread extends Thread {

	/** The m function. */
	protected Method loaderFunction;

	/** The m receiver. */
	protected Object carrierObject;

	/** The TAG. */
	public static final String TAG = "WorkerThread";

	/**
	 * Instantiates a new worker thread.
	 * <p>
	 * The carrier is an instance of the class carrying the function to execute.
	 * The functions name is simply the name without brackets and parameters. A
	 * worker thread is automatically registered by the <code>Application</code>
	 * ! Also if the thread is interrupted, the <code>Application</code> is
	 * notified.
	 * </p>
	 * <b>e.g.</b> the following method-signature:
	 * <p>
	 * <code>
	 * <pre>	public void loadContent();</pre>
	 * </code> will end up as the string <code>"loadContent"</code>, that will
	 * be passed into the parameter <code>functionName</code>. Define the code
	 * you want to execute in this method!
	 * </p>
	 * 
	 * @param carryer
	 *            the carryer is an instance of the class carrying the function
	 *            to execute
	 * @param functionName
	 *            the function name is simply the name without brackets and
	 *            parameters
	 */
	public WorkerThread(Object carryer, String functionName) {

		carrierObject = carryer;
		setName(TAG + " - " + functionName + "()");
		setDaemon(true);

		Method method = null;
		try {
			method = carryer.getClass().getDeclaredMethod(functionName,
					new Class[0]);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("No function name given!");
			return;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			System.err.println("No matching method found!");
			return;
		} catch (SecurityException e) {
			e.printStackTrace();
			System.err
					.println("SecurityException thrown! See 'getDeclaredMethod()' in 'Class' for more.");
			return;
		} finally {
			loaderFunction = method;
		}

		Application.getInstance().addWorkerThread(this);
	}

	/**
	 * Instantiates a new worker thread form a <code>LoaderFunction</code>. <br>
	 * <b><code>LoaderFunction</code> is only for package usage!</b></br>
	 * 
	 * <p>
	 * A worker thread is automatically registered by the
	 * <code>Application</code> ! Also if the thread is interrupted, the
	 * <code>Application</code> is notified.
	 * </p>
	 * 
	 * @param loader
	 *            the loader function to load the method-parameters.
	 */
	WorkerThread(LoaderFunction loader) {
		carrierObject = loader.carrier;
		loaderFunction = loader.method;
		setName("[LoaderFunction] " + TAG + " - " + loaderFunction.getName()
				+ "()");
		setDaemon(true);

		Application.getInstance().addWorkerThread(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		System.out.println("WorkerThread: " + getName() + " from object: "
				+ carrierObject.toString() + " is executing!");

		if (loaderFunction == null || carrierObject == null) {
			System.err
					.println("No loader-function and/or carryer object found!");
			return;
		}
		try {
			loaderFunction.invoke(carrierObject, (Object[]) null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.err.println("No arguments are allowed in the function!");
			return;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.err.println("Method inaccessible!");
			return;
		} catch (InvocationTargetException e) {
			System.err.println("The called method throws an exception!");
			e.printStackTrace();
			return;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err
					.println("The specified object is null and the method is an instance method!");
			return;
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
			System.err
					.println("The initialization provoked by this method failed!");
			return;
		}

		super.run();

		try {
			interrupt();
		} catch (SecurityException e) {
			e.printStackTrace();
			System.err.println(getName() + " could not terminate!");
			return;
		}

		System.out.println("WorkerThread: " + getName() + " from object: "
				+ carrierObject.toString() + " finished execution!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#interrupt()
	 */
	@Override
	public void interrupt() {
		try {
			Application.getInstance().removeWorkerThread(this);
			super.interrupt();
		} catch (SecurityException e) {
			e.printStackTrace();
			System.err.println("The current thread cannot modify this thread!");
		}
	}
}
