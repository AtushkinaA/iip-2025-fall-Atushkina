import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class CRM {
    private Auth auth;
    private Scanner sc;
    private List<Product> products;
    private List<Sell> sells;
    private int productIdCounter;
    private int saleIdCounter;
    public CRM() {
        auth = new Auth();
        sc = new Scanner(System.in);
        products = new ArrayList<>();
        sells = new ArrayList<>();
        productIdCounter = 1;
        saleIdCounter = 1;
        initializeProducts();
    }
    private void initializeProducts() {
        products.add(new Product(productIdCounter++, "Ноутбук", "Игровой ноутбук", 50000.0, 10));
        products.add(new Product(productIdCounter++, "Смартфон", "Флагманский смартфон", 35000.0, 20));
        products.add(new Product(productIdCounter++, "Планшет", "Графический планшет", 25000.0, 15));
        products.add(new Product(productIdCounter++, "Наушники", "Беспроводные наушники", 5000.0, 30));
        products.add(new Product(productIdCounter++, "Монитор", "4к монитор", 15000.0, 12));
    }
    public void start() {
        auth.start();
        if (!(auth.isAuth())) {
            return;
        }
        mainMenu();
    }
    private void mainMenu() {
        while (true) {
            System.out.println("Главное меню");
            System.out.println("1. Раздел продукты");
            System.out.println("2. Раздел продаж");
            System.out.println("3. Раздел статистика");
            System.out.println("4. Помощь");
            System.out.println("5. Выход");
            System.out.println("Выберите раздел:");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    productMenu();
                    break;
                case "2":
                    sellMenu();
                    break;
                case "3":
                    statisticsMenu();
                    break;
                case "4":
                    showHelp();
                    break;
                case "5":
                    System.out.println("Выход из системы..");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
    private void productMenu() {
        while (true) {
            System.out.println("Меню продуктов");
            System.out.println("1. Создать продукт");
            System.out.println("2. Посмотреть все продукты");
            System.out.println("3. Обновить продукт");
            System.out.println("4. Удалить продукт");
            System.out.println("5. Помощь");
            System.out.println("6. Назад в главное меню");
            System.out.println("Выберите действие:");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    createProduct();
                    break;
                case "2":
                    readProducts();
                    break;
                case "3":
                    updateProduct();
                    break;
                case "4":
                    deleteProduct();
                    break;
                case "5":
                    showProductHelp();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
    private void createProduct() {
        System.out.println("Создание продукта..");
        try {
            System.out.print("Название продукта: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Ошибка: Название не может быть пустым!");
                return;
            }

            System.out.print("Описание: ");
            String description = sc.nextLine().trim();

            System.out.print("Цена: ");
            double price;
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0) {
                    System.out.println("Ошибка: Цена должна быть больше 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Неверный формат цены!");
                return;
            }

            System.out.print("Количество на складе: ");
            int quantity;
            try {
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity < 0) {
                    System.out.println("Ошибка: Количество не может быть отрицательным!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Неверный формат количества!");
                return;
            }

            Product product = new Product(productIdCounter++, name, description, price, quantity);
            products.add(product);
            System.out.println("Продукт успешно создан! ID: " + product.getId());

        } catch (Exception e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }
    }
    private void readProducts() {
        System.out.println("Список продуктов:");
        if (products.isEmpty()) {
            System.out.println("Список продуктов пуст");
        } else {
            System.out.println("Всего продуктов: " + products.size());
            System.out.println("=======================");
            for (Product p : products) {
                System.out.println(p);
                System.out.println("=======================");
            }
        }
    }
    private void updateProduct() {
        System.out.println("Введите ID продукта для обновления:");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Product product = findProductById(id);
            if (product == null) {
                System.out.println("Продукт с ID" + id + "не найден!");
                return;
            }
            System.out.println("Текущие данные продукта:");
            System.out.println(product);
            System.out.println("Введите новые данные продукта (либо оставьте пустыми, если не хотите менять)");
            System.out.println("Новое название:");
            String newName = sc.nextLine();
            if (!(newName.isEmpty())) {
                product.setName(newName);
            }
            System.out.println("Новое описание:");
            String newDescription = sc.nextLine();
            if (!(newDescription.isEmpty())) {
                product.setDescription(newDescription);
            }
            System.out.println("Новая цена:");
            String priceStr = sc.nextLine().trim();
            if (!(priceStr.isEmpty())) {
                try {
                    double newPrice = Double.parseDouble(priceStr);
                    if (newPrice < 0) {
                        System.out.println("Ошибка: Цена должна быть больше 0!");
                        return;
                    }
                    product.setPrice(newPrice);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Неверный формат цены!");
                    return;
                }
            }
            System.out.print("Новое количество: ");
            String quantityStr = sc.nextLine().trim();
            if (!(quantityStr.isEmpty())) {
                try {
                    int newQuantity = Integer.parseInt(quantityStr);
                    if (newQuantity < 0) {
                        System.out.println("Ошибка: Количество не может быть отрицательным!");
                        return;
                    }
                    product.setQuantity(newQuantity);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Неверный формат количества!");
                    return;
                }
            }
            System.out.println("Продукт успешно обновлен!");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка неверный формат id!");
        }
    }
    private void deleteProduct() {
        System.out.println("Введите ID продукта, которого хотите удалить:");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Product product = findProductById(id);
            if (product == null) {
                System.out.println("Продукт с " + id + " не найден!");
                return;
            }
            products.remove(product);
            System.out.println("Продукт успешно удален!");

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат ID!");
        }
    }
    private void sellMenu() {
        while (true) {
            System.out.println("Меню продаж");
            System.out.println("1. Создать продажу");
            System.out.println("2. Посмотреть все продажи");
            System.out.println("3. Помощь");
            System.out.println("4. Назад в главное меню");
            System.out.println("Выберите действие:");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    createSell();
                    break;
                case "2":
                    readSells();
                    break;
                case "3":
                    showSellHelp();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
    private void createSell() {
        System.out.println("Создание продажи");
        if (products.isEmpty()) {
            System.out.println("Нет доступных продуктов для продажи!");
            return;
        }
        System.out.println("Доступные продукты для продажи: ");
        readProducts();
        try {
            System.out.print("Введите ID продукта для продажи: ");
            int productId = Integer.parseInt(sc.nextLine());
            Product product = findProductById(productId);

            if (product == null) {
                System.out.println("Продукт не найден!");
                return;
            }

            System.out.println("Выбран продукт: " + product.getName());
            System.out.println("Доступное количество: " + product.getQuantity());

            System.out.print("Количество для продажи: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity <= 0) {
                System.out.println("Количество должно быть больше 0!");
                return;
            }

            if (quantity > product.getQuantity()) {
                System.out.println("Недостаточно товара на складе!");
                return;
            }

            System.out.print("Дата продажи: ");
            String dateStr = sc.nextLine().trim();
            LocalDate saleDate;

            if (dateStr.isEmpty()) {
                saleDate = LocalDate.now();
                System.out.println("Используется сегодняшняя дата: " + saleDate);
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    saleDate = LocalDate.parse(dateStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Неверный формат даты! Используется сегодняшняя дата.");
                    saleDate = LocalDate.now();
                }
            }

            double totalPrice = product.getPrice() * quantity;
            Sell sale = new Sell(saleIdCounter++, product.getName(), quantity, totalPrice, saleDate);
            sells.add(sale);

            product.setQuantity(product.getQuantity() - quantity);

            System.out.println("Продажа успешно создана!");
            System.out.println("Сумма продажи: " + totalPrice);
            System.out.println("Остаток на складе: " + product.getQuantity());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный числовой формат!");
        }
    }
    private void readSells() {
        System.out.println("Список продаж: ");
        if (sells.isEmpty()) {
            System.out.println("Продаж нет!");
        } else {
            System.out.println("Всего продаж:" + sells.size());
            System.out.println("Общая выручка: " + calculateSells());
            for (Sell sell : sells) {
                System.out.println(sell);
            }
        }
    }
    private double calculateSells() {
        double total = 0;
        for (Sell sell : sells) {
            total += sell.getTotalPrice();
        }
        return total;
    }
    private void statisticsMenu() {
        System.out.println("Меню статистики продаж");
        try {
            System.out.println("Начальная дата: ");
            String startDateStr = sc.nextLine().trim();
            System.out.println("Конечная дата: ");
            String endDateStr = sc.nextLine().trim();
            if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
                System.out.println("Дата не может быть пустой!");
                return;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            if (endDate.isBefore(startDate)) {
                System.out.println("Конечная дата не может быть перед начальной!:/");
                return;
            }
            calculateStatistics(startDate, endDate);
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат данных!");
        }
    }
    private void calculateStatistics(LocalDate startDate, LocalDate endDate) {
        Map<String, Double> revenueByProduct = new HashMap<>();
        Map<String, Integer> quantityByProduct = new HashMap<>();
        int totalSalesInPeriod = 0;
        double totalRevenueInPeriod = 0;

        for (Sell sale : sells) {
            LocalDate saleDate = sale.getSaleDate();

            if (!saleDate.isBefore(startDate) && !saleDate.isAfter(endDate)) {
                String productName = sale.getProductName();

                double revenue = revenueByProduct.getOrDefault(productName, 0.0);
                revenueByProduct.put(productName, revenue + sale.getTotalPrice());

                int quantity = quantityByProduct.getOrDefault(productName, 0);
                quantityByProduct.put(productName, quantity + sale.getQuantity());

                totalSalesInPeriod++;
                totalRevenueInPeriod += sale.getTotalPrice();
            }
        }

        System.out.println("ОТЧЕТ ПО СТАТИСТИКЕ");
        System.out.println("Период: " + startDate + " - " + endDate);
        System.out.println("Всего продаж за период: " + totalSalesInPeriod);
        System.out.println("Общая выручка за период: " + totalRevenueInPeriod);

        if (revenueByProduct.isEmpty()) {
            System.out.println("Нет продаж за указанный период.");
        }
    }

    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private void showHelp() {
        System.out.println("СПРАВКА ПО CRM");
        System.out.println("РАЗДЕЛ ПРОДУКТЫ:");
        System.out.println("  - Создать: Добавить новый товар в систему");
        System.out.println("  - Просмотреть: Посмотреть все доступные товары");
        System.out.println("  - Обновить: Изменить данные существующего товара");
        System.out.println("  - Удалить: Удалить товар из системы");

        System.out.println("РАЗДЕЛ ПРОДАЖИ:");
        System.out.println("  - Создать: Зарегистрировать новую продажу товара");
        System.out.println("  - Просмотреть: Посмотреть историю всех продаж");

        System.out.println("РАЗДЕЛ СТАТИСТИКА:");
        System.out.println("  - Выберите период для анализа продаж");
        System.out.println("  - Система покажет сколько товаров принесли денег");

        System.out.println("ПРИМЕЧАНИЯ:");
        System.out.println("  - В каждом разделе доступна команда 'Помощь'");
        System.out.println("  - Все числовые значения должны быть положительными");
        System.out.println("  - Дата указывается в формате ДД.ММ.ГГГГ");
    }

    private void showProductHelp() {
        System.out.println("СПРАВКА ПО РАЗДЕЛУ ПРОДУКТЫ");
        System.out.println("1. СОЗДАТЬ ПРОДУКТ - добавляет новый товар в каталог");
        System.out.println("   Требуется: название, описание, цена, количество");

        System.out.println("2. ПРОСМОТРЕТЬ ПРОДУКТЫ - показывает все товары в системе");
        System.out.println("   Отображает: ID, название, описание, цену, остаток");

        System.out.println("3. ОБНОВИТЬ ПРОДУКТ - изменяет данные существующего товара");
        System.out.println("   Для обновления нужно знать ID товара");
        System.out.println("   Можно изменить любое поле или оставить без изменений");

        System.out.println("4. УДАЛИТЬ ПРОДУКТ - полностью удаляет товар из системы");
        System.out.println("Внимание: если у товара есть продажи, потребуется подтверждение");

        System.out.println("5. НАЗАД - возврат в главное меню");
    }

    private void showSellHelp() {
        System.out.println("СПРАВКА ПО РАЗДЕЛУ ПРОДАЖИ");
        System.out.println("1. СОЗДАТЬ ПРОДАЖУ - регистрирует новую продажу товара");
        System.out.println("   Шаги:");
        System.out.println("   а) Выбрать товар из списка (по ID)");
        System.out.println("   б) Указать количество для продажи");
        System.out.println("   в) Указать дату продажи или использовать текущую");
        System.out.println("Проверяется наличие достаточного количества товара");

        System.out.println("ПРОСМОТРЕТЬ ПРОДАЖИ - показывает историю всех продаж");
        System.out.println("   Отображает: ID продажи, товар, количество, сумму, дату");
        System.out.println("   Показывает общую выручку от всех продаж");

        System.out.println("НАЗАД - возврат в главное меню");
    }
}
