import javax.swing.*;

public class Main extends JPanel {

    public static void main(String[] args) {
       JFrame f=new JFrame("Hungry Player"); // Оздаём объект типа фрейм
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // При закрытии фрейма должна остановиться программа
        f.setSize(400,700);// Задаем размер фрейма
        f.add(new Background());//Добовляем класс "Background"
        f.setLocationRelativeTo(null);
        f.setVisible(true);// Делаем фрейм видимым




    }
}
