package Bibloid;

public class Bibloid {

    public static void main(String[] args) {
        //База
        User user = new User(DataMan.getInstance());//создать экземпляр пользака
        new MainWin(user,DataMan.getInstance());//создать окно
    }
}
