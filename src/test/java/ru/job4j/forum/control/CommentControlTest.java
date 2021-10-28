package ru.job4j.forum.control;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class CommentControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser
    public void whenSaveCommentShouldReturnDefaultMessage() throws Exception {
        Post post = Post.of("test", "test", new User());
        post.setId(1);
        when(postService.findPostById(1)).thenReturn(post);
        mockMvc.perform(post("/comment/save")
                .param("postId", "1")
                .param("text", "Какой-то комментарий"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Comment> commentArgument = ArgumentCaptor.forClass(Comment.class);
        verify(commentService).saveComment(commentArgument.capture());
        assertThat(commentArgument.getValue().getText(), is("Какой-то комментарий"));
        assertThat(commentArgument.getValue().getPost().getId(), is(1));
    }

}
