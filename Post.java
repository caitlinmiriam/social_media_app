import java.util.*;

public class Post {
    private String content;
    private User author;
    private Set<User> likes=new HashSet<>();
    private List<Comment> comments=new ArrayList<>();

    public Post(String content,User author) {
        this.content=content;
        this.author=author;
    }
    public void like(User user) {
        if(likes.add(user)) {
            author.getNotifications().add(user.getUsername()+" liked your post");
        }
    }

    public void comment(User user,String text) {
        comments.add(new Comment(user,text));
        author.getNotifications().add(user.getUsername()+" commented on your post");
    }

    public void display() {
        System.out.println("Post by "+author.getUsername()+": "+content);
        System.out.println("Likes: "+likes.size());

        for(Comment c:comments) {
            System.out.println(c);
        }
    }
}
