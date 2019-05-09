package org.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {

	DefaultBeanFactory factory = null;
	XmlBeanDefinitionReader reader = null;

	@Before
	public void setUP(){
		factory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(factory);
	}

	@Test
	public void testGetBean() {
		//读取配置文件     面向接口编程
		Resource resource = new ClassPathResource("petstore-v1.xml");
		reader.loadBeanDefinition(resource);
//		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		//校验配置文件属性
		assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());
		//实例化对象
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		//判断对象成功实例化否
		assertNotNull(petStore);
	}

	@Test
	public void testInvalidBean(){
		//读取配置文件     面向接口编程
		Resource resource = new ClassPathResource("petstore-v1.xml");
		reader.loadBeanDefinition(resource);
//		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		try {
			factory.getBean("invalidBean");
		} catch (Exception e) {
			return;   //触发异常则返回
		}
		Assert.fail("expect BeanCreationException");
	}

	@Test
	public void testInvalidXML(){
		try {
			Resource resource = new ClassPathResource("v1.xml");
			reader.loadBeanDefinition(resource);
//			new DefaultBeanFactory("xxx.xml");
		} catch (BeanDefinitionStoreException e) {
			return;
		}
		Assert.fail("expect BeanCreationException");
	}

}
