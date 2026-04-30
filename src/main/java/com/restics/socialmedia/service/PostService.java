package com.restics.socialmedia.service;

import com.restics.socialmedia.model.Post;
import com.restics.socialmedia.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    public final int TEST_USER = 0;
    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Post> getPostsOfUser(int userId){
        return postRepository.getPostsByAuthorId(userId);
    }

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }
    public List<Post> findFollowingPosts(){
        return postRepository.getFollowingPosts(TEST_USER); //TODO: switch with current user later
    }

    public void deletePost(int postId){
        postRepository.deletePost(postId);
    }

    public void updatePost(int postId, String newText){
        postRepository.updatePostContent(postId, "text", newText, "");
    }


    public void replyToPost(int postId, String newText){
        Post parent = postRepository.getPostByID(postId).get();
        int newpostid = postRepository.insertPost(TEST_USER, postId);
        postRepository.insertPostContent(newpostid,"text", newText, "");
    }

    public List<Post> findReplies(int postId){
        return postRepository.getPostReplies(postId);
    }


    public void likePost(int postId){

    }


}
