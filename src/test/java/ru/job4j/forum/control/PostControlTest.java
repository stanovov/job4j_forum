package ru.job4j.forum.control;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser
    public void whenGetPostPageWithIdParameterThenShouldReturnIsOk() throws Exception {
        Post post = Post.of("some title", "some description", new User());
        post.setId(1);
        when(postService.findPostById(1)).thenReturn(post);
        mockMvc.perform(get("/post?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("user", "post"))
                .andExpect(model().attribute("post",
                        hasProperty("id", is(1))))
                .andExpect(model().attribute("post",
                        hasProperty("name", is("some title"))))
                .andExpect(model().attribute("post",
                        hasProperty("description", is("some description"))));
    }

    @Test
    @WithMockUser
    public void whenGetPostPageWithoutIdParameterThenShouldReturn4xxError() throws Exception {
        mockMvc.perform(get("/post"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    public void whenGetCreatePostPageThenShouldReturnIsOk() throws Exception {
        mockMvc.perform(get("/post/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/create"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser
    public void whenGetEditPostPageWithIdParameterThenShouldReturnIsOk() throws Exception {
        Post post = Post.of("test name", "test description", new User());
        post.setId(2);
        when(postService.findPostById(2)).thenReturn(post);
        mockMvc.perform(get("/post/edit?id=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"))
                .andExpect(model().attributeExists("user", "post"))
                .andExpect(model().attribute("post",
                        hasProperty("id", is(2))))
                .andExpect(model().attribute("post",
                        hasProperty("name", is("test name"))))
                .andExpect(model().attribute("post",
                        hasProperty("description", is("test description"))));
    }

    @Test
    @WithMockUser
    public void whenGetEditPostPageWithoutIdParameterThenShouldReturn4xxError() throws Exception {
        mockMvc.perform(get("/post/edit"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    public void whenSavePostShouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(post("/post/save")
                        .param("name", "Куплю ладу-грант. Дорого.")
                        .param("description", "Рассматриваю пробег до 50 тысяч"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postService).savePost(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("Рассматриваю пробег до 50 тысяч"));
    }

    @Test
    @WithMockUser
    public void whenDeletePostShouldDefaultMessage() throws Exception {
        mockMvc.perform(post("/post/delete")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(postService).deletePost(argument.capture());
        assertThat(argument.getValue(), is(1));
    }
}
