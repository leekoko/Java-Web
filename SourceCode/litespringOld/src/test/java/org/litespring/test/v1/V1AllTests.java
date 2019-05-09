package org.litespring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	BeanFactoryTest.class, 
	ApplicationContextTest.class,
	ResourceTest.class})
public class V1AllTests {

}
