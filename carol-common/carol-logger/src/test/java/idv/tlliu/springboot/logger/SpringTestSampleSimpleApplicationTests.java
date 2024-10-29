/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package idv.tlliu.springboot.logger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


/**
 * Tests for {@link SampleSimpleApplication}.
 * 
 * @author Dave Syer
 */
@SpringBootTest(classes = SampleSimpleApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT) 
//Instructs Spring to load an ApplicationContext from TestConfig.class
//@ContextConfiguration(classes = TestConfig.class)
//@SpringJUnitConfig(TestConfig.class)
//@SpringJUnitWebConfig(TestWebConfig.class)
public class SpringTestSampleSimpleApplicationTests {

	@Test
	public void testContextLoads() throws Exception {
	}

}
