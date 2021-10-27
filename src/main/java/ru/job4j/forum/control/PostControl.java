package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class PostControl {

    private final PostService postService;

    private final UserService userService;

    public PostControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post")
    public String post(@RequestParam int id, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", postService.findPostById(id));
        return "post";
    }

    @GetMapping("/post/create")
    public String create(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "post/create";
    }

    @GetMapping("/post/edit")
    public String edit(@RequestParam int id, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", postService.findPostById(id));
        return "post/edit";
    }

    @PostMapping("/post/save")
    public String save(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setAuthor(userService.findByUsername(user.getUsername()));
        postService.savePost(post);
        return "redirect:/post?id=" + post.getId();
    }

    @PostMapping("/post/delete")
    public String delete(@RequestParam int id) {
        postService.deletePost(id);
        return "redirect:/index";
    }
}
