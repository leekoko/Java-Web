package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

public interface BeanFactory {

	Object getBean(String string);    //返回可能各种对象

}
