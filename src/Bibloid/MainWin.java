package Bibloid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainWin extends JFrame { //стартовое окно

    private JPanel workPanel;//панель работы
    private JPanel loginPanel;//панель авторизации

    MainWin(){
        User user = new User();//создать экземпляр пользака

        //описание основного окна программы
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(true);//а пусть будет

        //панель авторизации
        loginPanel = new JPanel(new GridLayout(1, 3));
        JTextField login = new JTextField();
        JPasswordField password = new JPasswordField();
        password.addActionListener(new ActionListener() {//что делать с введенным
            @Override
            public void actionPerformed(ActionEvent e) {
                user.auth(login.getText(), new String(password.getPassword()));
                //login.setText(""); оставляем логин
                password.setText("");
            }
        });//password.ActionListener

        JButton authIn = new JButton("Login");
        authIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.auth(login.getText(), new String(password.getPassword()));
                //login.setText(""); оставляем логин
                password.setText("");
            }
        });
        loginPanel.add(login);
        loginPanel.add(password);
        loginPanel.add(authIn);
        //панель команд
        //controlPanel = new JPanel(new GridLayout(1, 2));//сюда складываем элементы управления

//        add(workPanel, BorderLayout.CENTER);//главное окно занимает весь бокс
        add(loginPanel, BorderLayout.NORTH);//сверху панель логина
        //add(controlPanel, BorderLayout.SOUTH);//снизу элементы ввода
        switchWindows(user);//включаем в актуальный режим
        setVisible(true);

    }//MainWin

    private void switchWindows(User user) {//меняем вид окна при подключении-отключении
        setTitle(user.getNick());//меняем имя окна
        loginPanel.setVisible(!user.isOnline());//показывать, если юзера нет
//        workPanel.setVisible(user.isOnline());//показывать, если юзер онлайн
    }//switchWindows



}
