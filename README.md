# simbirsofttest                
                                                              Порядок запуска программы
1. Первый вариант. Откройте проект в _IntelIj Idea_. 
   Выполните сборку проекта и запустите его.
2. Второй вариант. В папке проекта откройте консоль. 
   Введите команду `mvn compile`.
   После успешного выполнения команды (появления BUILD SUCCESS)
   введите команду `mvn exec:java -Dexec.mainClass="com.Main"`
   
                                                         Правила использования приложения
После запуска программы, консоль будет ожидать ввод URL сайта для анализа.
После ввода URL, на консоль выведется статистика по количеству уникальных слов.
В папке _pages_ проекта будут сохраняться скаченные страницы,
а в папке _log_ хранятся логи ошибок. 