package com.jiabin.test;

import com.jiabin.test.practice.service.UserService;
import com.jiabin.test.practice.TestPracticeApplication;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestPracticeApplication.class)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class DemoTests {
    private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private MockMvc mockMvc;
	//@Autowired
	@MockBean
	UserService userService;
    @Before
    public void before()  {
        log.info("init some data");
    }
    @After
    public void after(){
        log.info("clean some data");
    }
    @Test
    public void execute() {
		userServiceMockBean();
        log.info("username:"+userService.getUserName());
		Assertions.assertThat(userService.getUserName().equals("mockname"));

    }
	@Test
	public void bookApiTest() throws Exception {

		// mockbean start
		userServiceMockBean();
		// mockbean end
		String expect = "{\"msg\":\"HelloWorld\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
				.andExpect(MockMvcResultMatchers.content().json(expect))
				.andDo(MockMvcResultHandlers.print());
		// mockbean reset
	}

	public void userServiceMockBean() {
		BDDMockito.given(userService.getUserName()).willReturn("mockname");
	}



}

