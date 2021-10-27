package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PostMem {

    private final Map<Integer, Post> posts = new HashMap<>();

    private final AtomicInteger postsIds = new AtomicInteger(0);

    private final AtomicInteger commentsIds = new AtomicInteger(0);

    public void savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(postsIds.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public void saveComment(Comment comment, int postId) {
        if (!posts.containsKey(postId)) {
            throw new IllegalArgumentException("Couldn't find post");
        }
        if (comment.getId() == 0) {
            comment.setId(commentsIds.incrementAndGet());
        }
        posts.get(postId).addComment(comment);
    }

    public boolean deletePost(Post post) {
        return posts.remove(post.getId(), post);
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public List<Post> findAllPostsOrderByIdDesc() {
        return posts.values().stream()
                .sorted(Comparator.comparingInt(Post::getId).reversed())
                .collect(Collectors.toList());
    }
}
