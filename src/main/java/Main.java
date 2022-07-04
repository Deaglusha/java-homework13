import model.User;
import service.HttpUtil;
import service.UserService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

        /*Напишите программу, которая будет взаимодействовать с API https://jsonplaceholder.typicode.com.

          Можно пользоваться стандартными возможностями Java (HttpURLConnection), либо познакомиться самостоятельно со
          сторонними библиотеками (Apache Fluent API, Apache HTTPClient, Jsoup).*/

public class Main {
    private static final String SITE_URL = "https://jsonplaceholder.typicode.com";
    private static final String SITE_USER = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpUtil httpUtil = new HttpUtil(SITE_URL);
        User defaultUser = UserService.getDefaultUser();

        /*Задание 1
        Программа должна содержать методы для реализации следующего функционала:*/
        /*создание нового объекта в https://jsonplaceholder.typicode.com/users. Возможно, вы не увидите обновлений на
        сайте. Метод создания работает правильно, если в ответ на JSON с объектом вернулся такой же JSON, но с полем
        id со значением на 1 больше, чем самый большой id на сайте.*/
        User createdUser = httpUtil.sendPost(URI.create(SITE_USER), defaultUser);
        System.out.println("Створення користувача: \n" + createdUser + "\n");

        /*обновление объекта в https://jsonplaceholder.typicode.com/users. Возможно, обновления не будут видны на
        сайте напрямую. Будем считать, что метод работает правильно, если в ответ на запрос придет обновленный JSON
        (он должен быть точно таким же, какой вы отправляли).*/
        defaultUser.setUsername("Deagl");
        User updatedUser = httpUtil.sendPut(URI.create(String.format("%s/%d", SITE_USER, defaultUser.getId())),
                defaultUser);
        System.out.println("Оновлення користувача: \n" + updatedUser + "\n");

        /*удаление объекта из https://jsonplaceholder.typicode.com/users. Здесь будем считать корректным результат -
        статус из группы 2хх в ответ на запрос.*/
        System.out.println("Видалення користувача: \n" + httpUtil.sendDelete(URI.create(String.format("%s/%d",
                SITE_USER, defaultUser.getId()))) + "\n");

        /*получение информации обо всех пользователях https://jsonplaceholder.typicode.com/users*/
        System.out.println("Всі користувачі: \n" + httpUtil.sendGetWithListOfResults(URI.create(SITE_USER)) + "\n");

        /*получение информации о пользователе с определенным id https://jsonplaceholder.typicode.com/users/{id}*/
        System.out.println("Користувач із id = 8: \n" + httpUtil.sendGet(URI.create(String.format("%s?id=%d",
                SITE_USER, 8))) + "\n");

        /*получение информации о пользователе с опредленным username -
        https://jsonplaceholder.typicode.com/users?username={username}*/
        System.out.println("Користувач із username = Kamren: \n"
                + httpUtil.sendGet(URI.create(String.format("%s?username=%s", SITE_USER, "Kamren"))) + "\n");

        /*Задание 2
        Дополните программу методом, который будет выводить все комментарии к последнему посту определенного
        пользователя и записывать их в файл.
        https://jsonplaceholder.typicode.com/users/1/posts Последним будем считать пост с наибольшим id.
        https://jsonplaceholder.typicode.com/posts/10/comments
        Файл должен называться "user-X-post-Y-comments.json", где Х - номер пользователя, Y - номер поста.*/
        System.out.println("Коментарі із останнього посту: " + httpUtil.getCommentsFromLastPost(defaultUser) + "\n");

        /*Задание 3
        Дополните программу методом, который будет выводить все открытые задачи для пользователя Х.
        https://jsonplaceholder.typicode.com/users/1/todos.
        Открытыми считаются все задачи, у которых completed = false.*/
        System.out.println("Відкриті задачі користувача: " + httpUtil.getOpenTask(defaultUser) + "\n");
    }
}
