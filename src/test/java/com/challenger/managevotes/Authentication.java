package com.challenger.managevotes;
import com.challenger.model.user.User;
import com.challenger.model.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ActiveProfiles("test")
@SpringBootTest
public class Authentication {
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser("/register")
    @Test
    public void register() throws Exception{
        User user = new User("testeadm","teste123",UserRole.ADMIN);
        String jsonRequest = mapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(post("/auth/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden()).andReturn();

        assertEquals(403, result.getResponse().getStatus());

    }


    @WithMockUser("/login")
    @Test
    public void login() throws Exception{
        User user = new User("testeadm","teste123",UserRole.ADMIN);
        String jsonRequest = mapper.writeValueAsString(user);
        MvcResult result = mockMvc.perform(post("/auth/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden()).andReturn();

        assertEquals(403, result.getResponse().getStatus());
    }
}
