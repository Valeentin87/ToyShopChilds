package shopToys.view;

/**
 * MANAGER - позволяет зайти в магазин с правами менеджера (пароль shop)
 * USER - вход в магазин с правами пользователя
 * READ - поиск товара по имени
 * CREATE - добавление нового товара в магазин (только в режиме MANAGER)
 * VIEW - просмотр всех товаров в магазине
 * PUT - оформление заказа (в режиме USER)
 * DELETE - удаление товара из магазина (в режиме MANAGER)
 * PRIZEBASKET - формирование призововй корзины (в режиме MANAGER)
 * FORTUNE - просмотр списка победителей
 * CASTBASKET - провести розыгрыш призовой корзины
 * EXIT - выход
 * BACK - возвращение на уровень назад
 * HELP - получение информации о текущем режиме
 */
public enum Commands {

    NONE,
    MANAGER,
    USER,
    READ,
    CREATE,
    VIEW,
    PUT,
    DELETE,
    PRIZEBASKET,
    FORTUNE,
    CASTBASKET,
    EXIT,
    BACK,
    HELP
}
