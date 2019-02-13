package Bibloid;

import java.sql.ResultSet;
import java.sql.SQLException;
import static Bibloid.DataMan.*;

public class User { //свойства клиента TODO внести сюда чтение из базы юзеров

    DataMan dataMan;

    public User(DataMan data) {
        dataMan=data;
    }

    private boolean online;//прошёл проверку
    private String nick;//имя
    private boolean admin;//библиотекарь

    boolean isOnline() {
        return online;
    }

    void setOnline() {
        this.online = true;
    }

    void setOffline() {
        this.online = false;
    }

    String getNick() {
        return nick;
    }

//    void setNick(String nick) {
//        this.nick = nick;
//    }

    public boolean isAdmin() {
        return admin;
    }

//    public void setAdmin(boolean admin) {
//        this.admin = admin;
//    }

    void auth(String login, String password) {
        //TODO запрос к базе (AUTH + " " + login + " " + password);, а пока всё счиитаем ОК
        String psh="71";
        if (Vars.debug_mode) System.out.println(login + password);

        if (dataMan.checkUser(login, password))
        {//логин успешен
            nick = "Гыук"; //заглушка
            online = true;
            admin = true;
            if (Vars.debug_mode) System.out.println("OK");
        }//логин успешен
        else
        {//логин неуспешен
            nick = ""; //заглушка
            online = false;
            admin = false;
            if (Vars.debug_mode) System.out.println("DEN");
        }//логин неуспешен
    }

//    private boolean checkUser(String login, String password) {
//        return dataMan.checkUser(login,password);
//    }


}//User
