Система автоматической организации очереди на экзамен

Постановка задачи
Приложение для удобного решения задачи о назначениях N людей на N мест с учетом их пожеланий, рассматривается на примере очереди на экзамен. Приложением пользуются Старосты и Студенты, связанные через членство в группе. Староста может поделиться ссылкой на группу и одобрить/отклонить заявку в нее. Староста может создать событие экзамена на N человеко-мест, где N - размер группы старосты. Студент может посмотреть событие, посмотреть приоритеты остальных Студентов и указать свой приоритет для каждого места. Когда все Студенты указали приоритет (или истек срок), Староста запускает решение события, после чего Студенты могут посмотреть решение события (порядок очереди).

Сущности
* Пользователь - ФИО, логин, пароль, роль
* Группа - название, список студентов, староста
* Событие - название, дата, группа, приоритеты студентов группы, решение
* Приоритет - студент, отображение “место-приоритет от 0 до 1”
* Решение - порядок студентов

Приложение реализуется в виде клиентской и серверной части (клиентом пользуются Староста и Студент, сервер обслуживает клиентов). GUI имеет только клиентская часть.

Клиент состоит из окон авторизации и основного экрана.
На экране авторизации пользователь выбирает роль Староста или Студент, вводит свои данные и авторизуется в приложении, попадая на основной экран.

На основном экране Староста должен иметь возможность:
    • посмотреть состав группы, принять/отклонить заявку в группу, получить инвайт-код (ссылку) в группу
    • создать событие, указав его имя и дедлайн заполнения приоритетов
    • посмотреть для события кто еще не заполнил приоритеты
    • посмотреть текущие заполненные приоритеты
    • запустить решение, посмотреть решение
    • изменить решение вручную

На основном экране Студент должен иметь возможность:
    • ввести инвайт-код и послать заявку в группу
    • посмотреть события в группе, для каждого события текущие заполненные приоритеты
    • заполнить свой приоритет для события
    • посмотреть решение и его историю изменений старостой


