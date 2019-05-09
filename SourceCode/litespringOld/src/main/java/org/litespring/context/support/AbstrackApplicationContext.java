package org.litespring.context.support;

import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;

public abstract class AbstrackApplicationContext implements ApplicationContext {
	
	private DefaultBeanFactory factory = null;
	
	public AbstrackApplicationContext(String configFile){    //相同代码
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = this.getResourceByPath(configFile);
		reader.loadBeanDefinition(resource);
	}
	
	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}
	
	protected abstract Resource getResourceByPath(String path);      //有差异的抽象方法
}
