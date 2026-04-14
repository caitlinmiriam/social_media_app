import java.util.*;
public class SocialMediaApp{
    static Scanner sc=new Scanner(System.in);
    static Map<String,User> users=new HashMap<>();
    public static void main(String[] args){
        while(true){
            System.out.println("\n1.Register");
            System.out.println("2.Login");
            System.out.println("3.Exit");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    static void register(){
        System.out.print("Enter Username: ");
        String username=sc.nextLine();
        System.out.print("Enter Password: ");
        String password=sc.nextLine();
        System.out.print("Enter Bio: ");
        String bio=sc.nextLine();
        users.put(username,new User(username,password,bio));
        System.out.println("Registration Successful");
    }
    static void login(){
        System.out.print("Enter Username: ");
        String username=sc.nextLine();
        System.out.print("Enter Password: ");
        String password=sc.nextLine();
        User user=users.get(username);
        if(user!=null&&user.checkPassword(password)){
            userMenu(user);
        }
        else{
            System.out.println("Invalid Credentials");
        }
    }
    static void userMenu(User user){
        while(true){
            System.out.println("\n1.View Profile");
            System.out.println("2.Create Post");
            System.out.println("3.View My Posts");
            System.out.println("4.Follow User");
            System.out.println("5.View Notifications");
            System.out.println("6.View All Users");
            System.out.println("7.View Feed");
            System.out.println("8.Like Post");
            System.out.println("9.Comment On Post");
            System.out.println("10.Logout");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    user.viewProfile();
                    break;
                case 2:
                    System.out.print("Enter Post Content: ");
                    String content=sc.nextLine();
                    user.addPost(new Post(content,user));
                    break;
                case 3:
                    for(Post p:user.getPosts()){
                        p.display();
                    }
                    break;
                case 4:
                    System.out.print("Enter Username to Follow: ");
                    String target=sc.nextLine();
                    if(users.containsKey(target)){
                        user.follow(users.get(target));
                    }
                    else{
                        System.out.println("User Not Found");
                    }
                    break;
                case 5:
                    System.out.println("Notifications:");
                    for(String n:user.getNotifications()){
                        System.out.println(n);
                    }
                    break;
                case 6:
                    viewAllUsers(user);
                    break;
                case 7:
                    viewFeed();
                    break;
                case 8:
                    likePost(user);
                    break;
                case 9:
                    commentPost(user);
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    static void viewAllUsers(User currentUser){
        for(String username:users.keySet()){
            if(!username.equals(currentUser.getUsername())){
                System.out.println(username);
            }
        }
    }
    static void viewFeed(){
        for(User u:users.values()){
            for(Post p:u.getPosts()){
                p.display();
                System.out.println();
            }
        }
    }
    static void likePost(User currentUser){
        System.out.print("Enter username whose post to like: ");
        String target=sc.nextLine();
        if(users.containsKey(target)){
            User other=users.get(target);
            List<Post> posts=other.getPosts();
            for(int i=0;i<posts.size();i++){
                System.out.println((i+1)+".");
                posts.get(i).display();
            }
            System.out.print("Choose post number: ");
            int index=sc.nextInt();
            sc.nextLine();
            posts.get(index-1).like(currentUser);
        }
        else{
            System.out.println("User Not Found");
        }
    }
    static void commentPost(User currentUser){
        System.out.print("Enter username whose post to comment on: ");
        String target=sc.nextLine();
        if(users.containsKey(target)){
            User other=users.get(target);
            List<Post> posts=other.getPosts();
            for(int i=0;i<posts.size();i++){
                System.out.println((i+1)+".");
                posts.get(i).display();
            }
            System.out.print("Choose post number: ");
            int index=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Comment: ");
            String text=sc.nextLine();
            posts.get(index-1).comment(currentUser,text);
        }
        else{
            System.out.println("User Not Found");
        }
    }
}
