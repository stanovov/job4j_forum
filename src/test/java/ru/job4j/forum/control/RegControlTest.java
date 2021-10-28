package ru.job4j.forum.control;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void whenGetRegPageThenShouldReturnIsOk() throws Exception {
        mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenRegUserThenShouldDefaultMessage() throws Exception {
        when(userService.existsByUsername("spammer")).thenReturn(false);
        mockMvc.perform(post("/reg")
                .param("username", "spammer")
                .param("password", "12345"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);
        verify(userService).save(userArgument.capture());
        assertThat(userArgument.getValue().getUsername(), is("spammer"));
        assertThat(userArgument.getValue().getPassword(), is("12345"));
    }

    @Test
    @WithMockUser
    public void whenRegUserWithExistsUsernameThenShouldReturnIsOkAndGetRegPage() throws Exception {
        when(userService.existsByUsername("test")).thenReturn(true);
        mockMvc.perform(post("/reg")
                .param("username", "test")
                .param("password", "test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"))
                .andExpect(model().attribute("errorMessage", "Пользователь с таким именем уже существует!"));
    }
}
