package com.ssafy.xmagazine.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ssafy.xmagazine.dto.UserDto;
import com.ssafy.xmagazine.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testDeleteUser() {

    }

    @Test
    void testInsertUser() {

    }

    @Test
    void testLogin() {

    }

    @Test
    void testLogout() {

    }

    @Test
    public void testSelectAllUser() throws Exception {
        // given
        List<UserDto> users = Arrays.asList(new UserDto(1, "User1", "user1@example.com", "password1", "2021-01-01"),
                new UserDto(2, "User2", "user2@example.com", "password2", "2021-01-02"));
        given(userService.selectAllUser()).willReturn(users);

        // when
        mockMvc.perform(get("/user/"))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("User1")))
                .andExpect(jsonPath("$[0].email", is("user1@example.com")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("User2")))
                .andExpect(jsonPath("$[1].email", is("user2@example.com")));
    }

    @Test
    void testSelectUserById() {

    }

    @Test
    void testUpdateUser() {

    }
}
