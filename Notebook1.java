import java.util.*;

// Класс для представления ноутбука с различными характеристиками
class Notebook {
    private int ram; // ОЗУ в ГБ
    private int hdd; // Объем ЖД в ГБ
    private int os; // Операционная система
    private String color; // Цвет

    // Конструктор с параметрами
    public Notebook(int ram, int hdd, int os, String color) {
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    // Геттеры для полей
    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public int getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
    @Override
    public String toString() {
        return "Операционная система: "+ os +"ОЗУ: " + ram + " ГБ" + "Объем ЖД: " + hdd + " ГБ"+"Цвет: " + color;
    }


    @Override
    public boolean equals(Object obj){
        Notebook notebook = (Notebook)obj;
        return os == notebook.os &&
         color.equals(notebook.color) &&
         ram < notebook.ram &&
         hdd < notebook.hdd;

         
        }

    @Override
    public int hashCode() {
         return Objects.hash(ram,hdd,os,color);
    }

    // Метод для вывода информации о ноутбуке
    public void printInfo() {
        System.out.println("ОЗУ: " + ram + " ГБ");
        System.out.println("Объем ЖД: " + hdd + " ГБ");
        System.out.println("Операционная система: " + os);
        System.out.println("Цвет: " + color);
        System.out.println();
    }
}

// Класс для решения задачи
public class  Notebook1{

    // Метод для создания множества ноутбуков
    public static Set<Notebook> createNotebooks() {
        // Создаем множество ноутбуков
        Set<Notebook> notebooks = new HashSet<>();

        // Добавляем некоторые ноутбуки в множество
        notebooks.add(new Notebook(8, 256, 1, "Black"));
        notebooks.add(new Notebook(16, 512, 2, "Silver"));
        notebooks.add(new Notebook(4, 128, 3, "Red"));
        notebooks.add(new Notebook(12, 256, 3, "Blue"));
        notebooks.add(new Notebook(8, 512, 2, "Gold"));

        // Возвращаем множество ноутбуков
        return notebooks;
    }

    // Метод для запрашивания у пользователя критериев фильтрации и вывода ноутбуков, отвечающих фильтру
    /**
     * @param notebooks
     */
    public static void filterNotebooks(Set<Notebook> notebooks) {
        // Создаем сканер для ввода данных с клавиатуры
        Scanner scanner = new Scanner(System.in);

        // Создаем карту для хранения критериев фильтрации и их значений
        Map<String, Object> criteria = new HashMap<>();

        // Выводим сообщение с вариантами критериев фильтрации
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить ввод критериев");

        // Вводим цифру, соответствующую критерию
        int choice = scanner.nextInt();

        // Пока не введен 0, продолжаем запрашивать критерии
        while (choice != 0) {
            final Map<String, Object> criteria2 ;
            // В зависимости от выбранной цифры, запрашиваем значение критерия и добавляем его в карту
            switch (choice) {
                case 1: // ОЗУ
                    System.out.println("Введите минимальное значение ОЗУ в ГБ:");
                    int ram = scanner.nextInt();
                    criteria.put("ram", ram);
                    break;
                case 2: // Объем ЖД
                    System.out.println("Введите минимальный объем ЖД в ГБ:");
                    int hdd = scanner.nextInt();
                    criteria.put("hdd", hdd);
                    break;
                case 3: // Операционная система
                    System.out.println("Введите название операционной системы:");
                    System.out.println("1 - Windows 10");
                    System.out.println("2 - Linux");
                    System.out.println("3 - MacOS");
                    int os = scanner.nextInt();
                    criteria.put("os", os);
                    break;
                case 4: // Цвет
                    System.out.println("Введите цвет ноутбука:");
                    String color = scanner.next();
                    criteria.put("color", color);
                    break;
                default: // Неверный ввод
                    System.out.println("Неверный ввод. Повторите попытку.");
                    break;
            }

            // Вводим следующую цифру, соответствующую критерию
            choice = scanner.nextInt();
        }

        // Закрываем сканер
        scanner.close();

        // Выводим сообщение о начале фильтрации
        System.out.println("Начинаем фильтрацию ноутбуков по заданным критериям...");

        // Создаем флаг для проверки, есть ли хотя бы один ноутбук, отвечающий фильтру
        boolean found = false;

        // Проходим по всем ноутбукам в множестве
        for (Notebook notebook : notebooks) {
            // Создаем флаг для проверки, удовлетворяет ли ноутбук всем критериям фильтра
            boolean match = true;

            // Проходим по всем критериям в карте
            for (Map.Entry<String, Object> entry : criteria.entrySet()) {
                // Получаем ключ и значение критерия
                String key = entry.getKey();
                Object value = entry.getValue();

                // В зависимости от ключа, сравниваем значение критерия с соответствующим полем ноутбука
                switch (key) {
                    case "ram": // ОЗУ
                        if (notebook.getRam() < (int) value) {
                            match = false;
                        }
                        break;
                    case "hdd": // Объем ЖД
                        if (notebook.getHdd() < (int) value) {
                            match = false;
                        }
                        break;
                    case "os": // Операционная система
                        if (notebook.getOs() < ((int) value)) {
                            match = false;
                        }
                        break;
                    case "color": // Цвет
                        if (!notebook.getColor().equals((String) value)) {
                            match = false;
                        }
                        break;
                }

                // Если ноутбук не удовлетворяет одному из критериев, прерываем цикл
                if (!match) {
                    break;
                }
            }

            // Если ноутбук удовлетворяет всем критериям, выводим его информацию и меняем флаг
            if (match) {
                notebook.printInfo();
                found = true;
            }
        }

        // Если не найден ни один ноутбук, отвечающий фильтру, выводим сообщение об этом
        if (!found) {
            System.out.println("Нет ноутбуков, отвечающих заданным критериям.");
        }
    }

    // Главный метод для запуска программы
    public static void main(String[] args) {
        // Создаем множество ноутбуков
        Set<Notebook> notebooks = createNotebooks();

        // Запрашиваем у пользователя критерии фильтрации и выводим ноутбуки, отвечающие фильтру
        filterNotebooks(notebooks);
    }

    
}