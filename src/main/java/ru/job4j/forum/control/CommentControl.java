package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class CommentControl {

    private final PostService postService;

    private final UserService userService;

    public CommentControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/comment/save")
    public String save(@RequestParam int postId, String text) {
        postService.saveComment(Comment.of(text, userService.findByUsername("user")), postId);
        return "redirect:/post?id=" + postId;
    }
}
