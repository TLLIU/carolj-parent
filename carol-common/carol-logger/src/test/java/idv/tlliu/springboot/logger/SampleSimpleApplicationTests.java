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

//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


//import org.springframework.boot.test.test.OutputCapture;

//import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link SampleSimpleApplication}.
 * 
 * @author Dave Syer
 * @author Phillip Webb
 */
@ExtendWith(OutputCaptureExtension.class)
//@ExtendWith(SpringExtension.class)
public class SampleSimpleApplicationTests {

	private String profiles;

	@BeforeEach
	public void init() {
		this.profiles = System.getProperty("spring.profiles.active");
	}

	@AfterEach
	public void after() {
		if (this.profiles != null) {
			System.setProperty("spring.profiles.active", this.profiles);
		}
		else {
			System.clearProperty("spring.profiles.active");
		}
	}

	@Test
	public void testDefaultSettings(CapturedOutput outputCapture) throws Exception {
		SampleSimpleApplication.main(new String[0]);
		String output = outputCapture.toString();
		Assertions.assertTrue(output.contains("Hello Phil"), "Wrong output: " + output);
	}

	@Test
	public void testCommandLineOverrides(CapturedOutput outputCapture) throws Exception {
		SampleSimpleApplication.main(new String[] { "--name=Gordon" });
		String output = outputCapture.toString();
		Assertions.assertTrue(output.contains("Hello Gordon"), "Wrong output: " + output);
	}

}
