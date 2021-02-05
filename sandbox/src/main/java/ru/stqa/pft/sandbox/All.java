//пакет = директория, чтобы избежать конфликтов из-за повторения названий классов. Рекомендуется классы размещать в пакетах. ПКМ - Refactor - Move
package ru.stqa.pft.sandbox;
//объявляем класс = имя файла (всегда с большой буквы!)
//объявляем метод в ()
//в {} пишем саму программу (код) = тело метода
public class All {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        System.out.println(2 + 2);

        System.out.println(2.0 + 2);

        System.out.println("Привет" + 2); //это склейка строк = конкатенация

        System.out.println("Привет " + "медвед!"); //это тоже

        System.out.println("2 + 2 = " + (2 + 2)); //выражения берем в скобки

        System.out.println("Hello," + " world!"); //значение = выражение, которое нельзя упростить = кусочек данных (литеральные и вычисляемые)

        int l = 5;//переменная = синоним значения
        int s = l * l;//int объявляет целочисленные значения, double - с плавающей точкой, string - строки
        System.out.println("Площадь квадрата со стороной " + l + " = " + s);
    }
}

/*функция = переменная большого куска кода
--------после имени функции указываем круглые скобки, в фигурных пишем код (содержимое)

public class Example {
    public static void main(String args[]) {
        program();
    }
    public static void program() {                       -это функция program
        String somebody = "Java"
        System.out.println("Простая программа на " + somebody);
    }
}

--------если переменная определена внутри функции то и значение ей присваивается внутри функции, для того чтобы значение переменной мб менять извне необходимо её объявить иначе:

public class Example {
    public static void main(String args[]) {
        program();
    }
    public static void program(String somebody) {         -переместили, теперь это параметр = аргумент функции
        System.out.println("Простая программа на " + somebody);
    }
}

--------и в том месте где функция вызывается необходимо передать конкретное значение для этого параметра:

public class Example {
    public static void main(String args[]) {
        program("Java");                              -вот здесь конкретное значение
    }
    public static void program(String somebody) {
        System.out.println("Простая программа на " + somebody);
    }
}

-------можем выводить разные параметры:
public class Example {
    public static void main(String args[]) {
        program("Java");
        program("JavaScript");
        program("C++");
    }
    public static void program(String somebody) {
        System.out.println("Простая программа на " + somebody);
    }
}
*/

//область видимости переменной - фигурные скобки в которых она определена


