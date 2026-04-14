import java.util.*;
public class User {
    private String username;
    private String password;
    private String bio;
    private List<Post> posts = new ArrayList<>();
    private Set<User> followers = new HashSet<>();
    private Set<User> following = new HashSet<>();
    private Queue<String> notifications = new LinkedList<>();
    public User(String username,String password,String bio) {
        this.username = username;
        this.password = password;
        this.bio = bio;
    }
    public String getUsername() {
        return username;
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    public List<Post> getPosts() {
        return posts;
    }
    public Queue<String> getNotifications() {
        return notifications;
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    public void follow(User other) {
        if(other != this && following.add(other)) {
            other.followers.add(this);
            other.notifications.add(username + " started following you");
        }
    }
    public void viewProfile() {
        System.out.println("Username: " + username);
        System.out.println("Bio: " + bio);
        System.out.println("Followers: " + followers.size());
        System.out.println("Following: " + following.size());
    }
}
