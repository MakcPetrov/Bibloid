package Bibloid;

public class User { //свойства клиента TODO внести сюда чтение из базы юзеров

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
        if (Vars.debug_mode) System.out.println(login + password);
        if (login.equals("bib") && password.equals("71"))
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
}//User
