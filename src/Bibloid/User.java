package Bibloid;

public class User { //свойства клиента TODO внести сюда чтение из базы юзеров

    private boolean online;//прошёл проверку
    private String nick;//имя
    private boolean admin;//библиотекарь

    boolean isOnline() {
        return online;
    }

//    void setOnline(boolean online) {
//        this.online = online;
//    }

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
        if (login.equals("bib") && password.equals("71"))
        {//логин успешен
            nick = "Гыук"; //заглушка
            online = true; //заглушка
            admin = true;  //заглушка
        }//логин успешен
        else
        {//логин неуспешен
            nick = ""; //заглушка
            online = false; //заглушка
            admin = false;  //заглушка
        }//логин успешен

    }


}//User
