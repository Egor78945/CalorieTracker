# Calorie Tracker
## Описание проекта
Calorie Tracker представляет собой API для учёта и контроля калорийности съеденных за день блюд.
### Функционал  
1) Регистрация пользователя
2) Расчёт рекомендуемого лимита калорий в день
3) Добавление списка съеденных за текущий день блюд
4) Получение сумму калорий и список съеденных за текущий день блюд
5) Получить отчёт, уложился ли пользователь в рекомендуемый лимит калорий
6) Получить список когда либо съеденных блюд, сгрупированный по дням  
## Инструкция по использованию
### Конечные точки  
1) /auth/registration [POST]  
   Принимает JSON формата:
     
   **{**  
    **"email",** - Электронная почта пользователя.  
    **"name",** - Имя пользователя. Должно содержать только буквы (a-z, A-Z), иметь длину от 2 до 20 символов.  
    **"age",** - Возраст пользователя. Возраст должен быть от 14 до 110 (лет).  
    **"weight",** - Вес пользователя. Вес должен быть от 20 до 250 (килограммов).  
    **"height",** - Рост пользователя. Рост должен быть от 130 до 220 (сантиметров).  
    **"gender",** - Пол пользователя. Должен быть либо мужчина("m"), либо женщина("f").  
    **"goal"** - Цель пользователя. Целью может быть: Сброс веса / Увеличение веса / Удержание веса - loss / increase / hold.  
**}**
  
   Возвращает JSON формата:
  
   **{**  
    **"id"**, - ID зарегестрированного пользователя.  
    **"email",** - Электронная почта пользователя.  
    **"name",** - Имя пользователя.  
    **"age",** - Возраст пользователя.  
    **"weight",** - Вес пользователя.  
    **"height",** - Рост пользователя.  
    **"gender",** - Пол пользователя.  
    **"goalId"** - Цель пользователя.  
**}**

2) /calorie/recommended/{email} [GET]  
   Принимает электронную почту пользователя.  

   Возвращает рекомендуемое количество калорий в день для пользователя.  
     
4) /calorie/check/{email} [GET]  
   Принимает электронную почту пользователя.  

   Возвращает boolean ответ, который сигнализирует о том, уложился ли сегодня пользователь в рекомендуемый лимит калорий.  

5) /details/goal?email&goal [POST]  
   Принимает email и цель пользователя.  
  
   Изменяет текущую цель пользователя на принятую в запросе.  
  
6) /dish/add [POST]  
   Принимает JSON формата (Электронную почту пользователя и список съеденных блюд):

   **{**  
   **"email",**  - Электронная почта пользователя.  
   **"dishes":**  - Список съеденных пользователем блюд.  
   **[**  
   **{**  
   **"name",**  - Название блюда. Должно содержать только буквы (a-z, A-Z), иметь длину от 0 до 50 символов.  
   **"calorie",**  - Количество килокалорий в блюде. Количество калорий должно быть от 0 до 3000.  
   **"protein",**  - Количество белков в блюде. Количество белков должно быть от 0% до 100%.  
   **"fat",**  - Количество жиров в блюде. Количество жиров в блюде должно быть от 0% до 100%.  
   **"carbohydrate"**  - Количество углеводов в блюде. Количество углеводов в блюде должно быть от 0% до 100%.  
   **}**  
   **]**  
   **}**  
   **ВАЖНО: Сумма белков, жиров и углеводов в одном блюде должна равняться 100%.**

   Возвращает сумму калорий всех добавленных блюд.

7) /dish/all/{email} [GET]  
   Принимает электронную почту пользователя.

   Возвращает JSON формата (Списки когда либо съеденных пользователем блюд, сгрупированных по дням):

   **{**  
   **"дата":** - Дата, когда были съедены блюда из списка.  
   **[**  
   **{**  
   **"id",** - ID съеденного блюда.  
   **"name",** - Название съеденного блюда.  
   **"calorie",** - Количество калорий съеденного блюда.  
   **"protein",** - Количество белков в съеденном блюде.  
   **"fat",** - Количество жиров в съеденном блюде.  
   **"carbohydrate",** - Количество углеводов в съеденном блюде.  
   **"date",** - Дата, когда блюдо было съедено.  
   **"userEmail"** - Электронная почта пользователя, который съел блюдо.  
   **}**  
   **]**  
   **}**  

8) /dish/today/{email} [GET]  
   Принимает электронную почту пользователя.  

   Возвращает JSON формата (Сумма калорий и список съеденных за сегодня блюд):

   **{**  
   **"calorie",** - Сумма съеденных за день блюд.  
   **"dishes":** - Список съеденных блюд.  
   **[**  
   **{**  
   **"id",** - ID съеденного блюда.  
   **"name",** - Название съеденного блюда.  
   **"calorie",** - Количество калорий съеденного блюда.  
   **"protein",** - Количество белков в съеденном блюде.  
   **"fat",** - Количество жиров в съеденном блюде.  
   **"carbohydrate",** - Количество углеводов в съеденном блюде.  
   **"date",** - Дата, когда блюдо было съедено.  
   **"userEmail"** - Электронная почта пользователя, который съел блюдо.  
   **}**  
   **]**  
   **}**

## Инструкция по запуску  
В проекте присутствует файл docker-compose.yml. Следует по порядку запустить сначала сервис postgres и только через минуту сервис app.
