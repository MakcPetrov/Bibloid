package Bibloid;

class Vars {
    //параметры программы TODO сделать чтение, сохранение и окно настроек
    static boolean debug_mode=true; //для отладки, если true - выводим в консоль сообщения
    static String BDfilename="bibloid"; //Имя базы
    static String clientsBD="clients"; //Имя базы
    static String booksBD="books"; //Имя базы
    static String BDpath="./BD/"+BDfilename+".db";
}
