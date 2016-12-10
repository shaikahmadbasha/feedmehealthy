/**
 * 
 */
package com.mymeal.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author basha
 *
 */
@Component("appContext")
public class AppContext implements ApplicationContextAware {

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	public ApplicationContext getApplicationContext() {
		return context;
	}

	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}

}
