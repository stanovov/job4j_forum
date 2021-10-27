package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMem;

import java.util.Calendar;
import java.util.List;

@Service
public class PostService {

    private final PostMem postRepository;

    public PostService(PostMem postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(Post post) {
        Post original = postRepository.findPostById(post.getId());
        if (original == null) {
            post.setCreated(Calendar.getInstance());
        } else {
            post.setCreated(original.getCreated());
            original.getComments()
                    .forEach(post::addComment);
        }
        postRepository.savePost(post);
    }

    public void saveComment(Comment comment, int postId) {
        postRepository.saveComment(comment, postId);
    }

    public void deletePost(int id) {
        postRepository.deletePost(postRepository.findPostById(id));
    }

    public Post findPostById(int id) {
        return postRepository.findPostById(id);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAllPostsOrderByIdDesc();
    }
}
