package jira;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import jira.repository.EmployeeDepartmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Datajpa770ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeDepartmentRepository repository;

    @Test
    public void contextLoads() {
    }

    @Before
    public void deleteAllBeforeTests() {
        repository.deleteAll();
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$._links.employeeDepartments").exists());
    }

    @Test
    public void shouldCreateEntity() throws Exception {
        mockMvc.perform(post("/employeeDepartments")
                .content("{ \"employeeId\" : \"oneEmployee\", \"departmentId\" : \"oneDepartment\" }"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("employeeDepartments")));
    }
}
