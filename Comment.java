public class Comment {
    private User author;
    private String text;
    public Comment(User author,String text) {
        this.author = author;
        this.text = text;
    }
    public String toString() {
        return author.getUsername() + ": " + text;
    }
}
