package com.odds.springtestsdemo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class RepositoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryService repositoryService;

    @Test
    void testGetRepositoryForUserShouldCallRepositoryService() throws Exception {
        String url = "/users/yothinix/repository";
        mockMvc.perform(MockMvcRequestBuilders.get(url));

        Mockito.verify(repositoryService).getRepository("yothinix");
    }

    @Test
    void testGetRepositoryForUserShouldReturnRepository() throws Exception {
        String url = "/users/yothinix/repository";
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get(url));

        actual.andExpect(MockMvcResultMatchers.status().isOk());
        actual.andExpect(MockMvcResultMatchers.content().string(containsString("{\"id\":1234,\"name\":\"Banana\",\"url\":null}")));
    }
}
