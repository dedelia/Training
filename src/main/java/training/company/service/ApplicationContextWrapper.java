package training.company.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Wrapper to return a reference to the Spring Application Context
 *
 * @author marianab
 */
@Component
public class ApplicationContextWrapper implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * This method is called once the ApplicationContext it's starting up, it
	 * will stick a reference to itself into this bean.
	 *
	 * @param appContext
	 *            a reference to the ApplicationContext.
	 */
	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		applicationContext = appContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Gets an instance of the specified type.
	 *
	 * @param className
	 *            bean type. Can be an interface or superclass of the actual
	 *            class, or null for any match. For example, if the value is
	 *            Object.class, this method will succeed whatever the class of
	 *            the returned instance.
	 * @return an instance of the bean
	 */
	public static <T> T getBeanByClass(Class<T> className) {
		T obj = ApplicationContextWrapper.getApplicationContext().getBean(className);
		return obj;
	}

	/**
	 * Gets an instance of the specified bean.
	 *
	 * @param beanName
	 *            the name of the bean to retrieve
	 * @return an instance of the bean
	 */
	public static <T> T getBeanByName(String beanName) {
		@SuppressWarnings("unchecked")
		T obj = (T) ApplicationContextWrapper.getApplicationContext().getBean(beanName);
		return obj;
	}

	/**
	 * Gets an instance of the specified bean and type.
	 *
	 * @param className
	 *            bean type. Can be an interface or superclass of the actual
	 *            class, or null for any match. For example, if the value is
	 *            Object.class, this method will succeed whatever the class of
	 *            the returned instance.
	 * @param beanName
	 *            the name of the bean to retrieve
	 * @return an instance of the bean
	 */
	public static <T> T getBeanByClassAndName(Class<T> className, String beanName) {
		T obj = ApplicationContextWrapper.getApplicationContext().getBean(beanName, className);
		return obj;
	}
}