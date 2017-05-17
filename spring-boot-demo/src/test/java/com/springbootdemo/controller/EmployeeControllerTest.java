package com.springbootdemo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootdemo.SpringBootDemoApplication;
import com.springbootdemo.domain.Employee;
import com.springbootdemo.service.EmployeeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest extends EmployeeController{
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                //.addFilters(new CORSFilter())
                .build();
    }

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetEmployee() throws Exception {
		
		Employee employee = new Employee("Chuck", "9487","male","0987487987","Taipei",35);

        when(employeeService.getEmployee("9487")).thenReturn(employee);
		
		mockMvc.perform(get("/employees/{id}",9487))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath(("$.name"), is("Chuck")))
				.andExpect(jsonPath(("$.id"), is("9487")))
				.andExpect(jsonPath(("$.gender"), is("male")))
				.andExpect(jsonPath(("$.cellphone"), is("0987487987")))
				.andExpect(jsonPath(("$.address"), is("Taipei")))
				.andExpect(jsonPath(("$.age"), is(35)));
	    verify(employeeService, times(1)).getEmployee("9487");
	    verifyNoMoreInteractions(employeeService);

	}
	
	@Test
	public void TestAddEmployee() throws Exception {
		Employee employee = new Employee("Test", "0000","male","0000000000","Test",-1);
	    when(employeeService.exists(employee)).thenReturn(false);
	    doNothing().when(employeeService).addEmployee(employee);
	    mockMvc.perform(post("/employees")
	    		.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(employee)))
	            .andExpect(status().isCreated());
	    //verify(employeeService, times(1)).exists(employee);
	    //verify(employeeService, times(1)).addEmployee(employee);
	    //verifyNoMoreInteractions(employeeService);
	}
	
	@Test
	public void TestUpdateEmployee() throws Exception {
		Employee employee = new Employee("Test", "9487","male","0000000000","Test",-1);
	    when(employeeService.getEmployee(employee.getId())).thenReturn(employee);
	    doNothing().when(employeeService).updateEmployee(employee.getId(),employee);
	    mockMvc.perform(put("/employees/{id}", employee.getId())
	    		.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(employee)))
	            .andExpect(status().isOk());
	    //verify(employeeService, times(1)).getEmployee(employee.getId());
	    //verify(employeeService, times(1)).updateEmployee(employee.getId(),employee);
	    //verifyNoMoreInteractions(employeeService);
	}
	
	@Test
	public void TestDeleteEmployee() throws Exception {
		Employee employee = new Employee("Chuck", "9487","male","0987487987","Taipei",35);
	    when(employeeService.getEmployee(employee.getId())).thenReturn(employee);
	    doNothing().when(employeeService).deleteEmployee(employee.getId());
	    mockMvc.perform(delete("/employees/{id}", employee.getId()))
	            .andExpect(status().isOk());
	    verify(employeeService, times(1)).getEmployee(employee.getId());
	    verify(employeeService, times(1)).deleteEmployee(employee.getId());
	    verifyNoMoreInteractions(employeeService);
	}
	

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	

}
