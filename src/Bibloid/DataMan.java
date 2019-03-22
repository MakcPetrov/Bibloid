package Bibloid;

import java.sql.*;

class DataMan {//Менеджер базы данных TODO сделать базы библиотеки, а не того, что есть
    private static volatile DataMan instance;

    private Connection connect;
    private Statement statement;

//TODO: сделать лог-файл if (Vars.debug_mode)

    //TODO: сделать проверку "если нет - то создать"
    private boolean newBase=true;
    private boolean failure=false;

    public boolean checkUser(String login, String password)
    {//User & passwd
        //password = password;//здесь можно вставить шифрование пароля для хранения в базе
        ResultSet outBD = runQuery("SELECT pswd FROM clients WHERE login = '"+login+"'");
        try {
            if (outBD !=null && outBD.next()&& password.equals(outBD.getString("pswd")))

                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;//User not found or password failure
    }//проверить пароль у юзера, при любой ошибке false

    private DataMan() {//конструктор + подключение к БД
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:"+ Vars.BDpath);
            statement = connect.createStatement();
            if (Vars.debug_mode) System.out.println("connect BD");
            if (newBase) { //создать базу с нуля
                failure=newBase(Vars.clientsBD);
                failure=newBase(Vars.booksBD);
            }//создать базу с нуля
        } catch (SQLException | ClassNotFoundException e) {
            failure=true;//авария (потом проверять будем)
            e.printStackTrace();
        }
    }//конструктор + подключение к БД

    public static DataMan getInstance() {
        DataMan localInstance = instance;
        if (localInstance == null) {
            synchronized (DataMan.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataMan();
                }
            }
        }
        return localInstance;
    }



    public int getUserQuote(String login) {//сколько места доступно для юзера
        ResultSet outBD = runQuery("SELECT quote FROM clients WHERE login = '"+login+"'");
        try {
            if (outBD !=null && outBD.next())
                return outBD.getInt("quote");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }//сколько места доступно для юзера

    private ResultSet runQuery(String query) {//только этот метод знает, в какой базе роемся
        try {
            return connect.prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }//выполнить запрос в базе

    void DataClose() {// DataClose всё закрыть
        try {
            statement.close();
            connect.close();
            if (Vars.debug_mode) System.out.println("disconnect DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//DataClose всё закрыть

    private boolean newBase(String BDname){//создать файл базы
        /* Структура базы */
//      clients:
//      id - порядковый номер записи, первичный ключ
//      login - имя пользователя
//      pswd - пароль
//      quote - доступное место
//
//      files:
//      id - порядковый номер записи, первичный ключ
//      fname - имя файла
//      fext - расширение файла
//      login - имя клиента, которому принадлежит файл
//      size - размер
//      crTm - время создания
//      reTm - время обращения
//      wrTm - время записи
        if (Vars.debug_mode) System.out.println("create BD");
        try {
            statement.execute("DROP TABLE IF EXISTS "+Vars.clientsBD);
            statement.execute("CREATE TABLE IF NOT EXISTS "+Vars.clientsBD+" (id  INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT UNIQUE ON CONFLICT REPLACE, pswd TEXT, quote INTEGER);");
            statement.execute("DELETE FROM "+Vars.clientsBD);
            return true;//всё создалось
        } catch (SQLException e) {
            e.printStackTrace();
            return false;//авария
        }
    }//создать файл базы
}//Менеджер базы данных
