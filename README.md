# Bibloid
Классы системы Bibloid

Серверная часть, содержащая АРМ библиотекаря. Как вариант, может быть интегрирован с базой.

1. Bibloid +
	стартовый модуль, инициализирующий остальные и передающий управление главному окну
2. MainWin ~
	окно основного интерфейса АРМ библиотекаря. Наследует стандартному JFrame
	2.1. loginPanel - 
		интерфейс входа в систему
		поля логина, пароля, кнопка входа, кнопка авторизации через внешний ID, кнопка отмены. вызывает DataMan.checkUser, в случае успеха передаёт управление workPanel
	2.2. workPanel - 
		основное окно с кнопками действий
		кнопки 
			поиск книги 
				вызов BookList createOff
			поиск читателя
				вызов UserList createOff
			новая книга
				вызов BookList createOn				
			новый читатель			
				вызов UserList createOn
			настройка системы
				вызов controlPanel
			резрервирование базы
				вызов DataMan.save
			восстановление базы
				вызов DataMan.restore
			отчёты
				вызов ListGen.standart
			списки  
				вызов ListGen.optional
	2.3. controlPanel - 
		окно настроек программы 
			настройки владельца
				вызов Vars.main	
			пути				
				вызов Vars.path
			поведение
				вызов Vars.table
3. BookList ~
	окно работы со списком книг
4. UserList ~
	окно работы со списком читателей
5. DataMan ~ (Singleton)
	модуль работы с базой данных
	5.1 create -
		создание пустой структуры базы
	5.2 import ~
		создать структуру и загрузить в неё данные из указанного при вызове файла chv
	5.3 query ~
		выполнить в базе запрос с полями, полученными из вызывающего модуля
	5.4 save ~
		сохранить информацию из базы в backup.chv
	5.5. restore ~
		удалить базу, создать заново и загрузить данные из backup.chv		
6. Vars ~
	модуль настроек, переменных, путей и прочей персонализирующей информации
	6.1 main
	6.2 path
	6.3 table
		окно с полями значений всех конфигурационных переменных, кроме данных персонализации и путей
7. User ~
	объект пользователь - учётка, состояние, атрибуты, права, дата регистрации, дата выбытия, книги на руках * (ID)
8. Book ~
	объект книга - ID, автор, название, издание, год, инв№, ISBN, у кого 1 (ID пользователя) 
