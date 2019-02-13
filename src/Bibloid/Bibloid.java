package Bibloid;

public class Bibloid {

    public static void main(String[] args) {
        DataMan data = new DataMan();//База
        User user = new User(data);//создать экземпляр пользака
        new MainWin(user,data);//создать окно
    }
}
