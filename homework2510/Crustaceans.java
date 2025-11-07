package homework2510;

public class Crustaceans extends Animal implements HowIEating, Temperature, WhatWeWantEat, Printing {
    public Crustaceans(String title, String environment, String whatBloods, String bodyStructure, String haveSpine, String typeEating) {
        super(title, environment, whatBloods, bodyStructure, haveSpine, typeEating);
    }

    @Override
    void say() {
        System.out.println("Наша особенность - двуветвистые конечности.");
    }

    @Override
    public void howIEating(String haveSpine) {
        if (haveSpine.equals("Позвоночные")) {
            System.out.println("У нас есть зубыыы, мы грызем.");
        } else {
            System.out.println("Мы получаем еду в организм с помощью фильтрации либо сосательным путем.");
        }
    }

    @Override
    public void dependenceTemperature(String whatBloods) {
        if (whatBloods.equals("Теплокровные")) {
            System.out.println("Наша температура не зависит от окружающей среды.");
        } else {
            System.out.println("Температура тела зависит от окружающей среды.");
        }
    }

    @Override
    public void eatIam(String typeEating) {
        if (typeEating.equals("Травоядные")) {
            System.out.println("Давай травушку!");
        }
        if (typeEating.equals("Всеядные")) {
            System.out.println("Я съем всё!");
        }
        if (typeEating.equals("Хищники")) {
            System.out.println("Давай мяса, давай мяса!");
        }
        if (typeEating.equals("Зависит от вида")) {
            System.out.println("Ну посмотрим, чем я буду питаться:/");
        }
    }
    @Override
    public void printing(String title, String environment, String whatBloods, String bodyStructure, String haveSpine, String typeEating) {
        System.out.println("Наименование животного - " + title + "; Среда обитания - " + environment + "; Тип крови - " + whatBloods + "; Внешность - " + bodyStructure + "; " + haveSpine + "; Тип питания - " + typeEating);
    }
}
