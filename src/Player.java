import javax.swing.*;
import java.awt.*;

/**
 * Created by Пользователь on 15.11.2016.
 */
public class Player {
    int x = 130;// координата положения панды по х
    int y = 540;// координата положения панды по у


    public Rectangle getRect(){//Конструктор
        return new Rectangle(x,y,130,150);// Возвращаем прямоугольник размером с панды

    }

}
