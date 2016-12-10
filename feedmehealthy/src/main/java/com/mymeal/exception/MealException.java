/**
 * 
 */
package com.mymeal.exception;

/**
 * @author basha
 *
 */
public class MealException extends Exception {

	private static final long serialVersionUID = -8832463537200479994L;

	public MealException() {
		super();
	}

	public MealException(String messsage) {
		super(messsage);
	}

	public MealException(Throwable th) {
		super(th);
	}

	public MealException(String messsage, Throwable th) {
		super(messsage, th);
	}
}
