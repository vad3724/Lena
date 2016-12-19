import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Created by Пользователь on 14.11.2016.
 */
public class Background extends JPanel implements ActionListener,Runnable { // класс фона
    Player p = new Player(); // создаём объект типа плеер
    Image fon= new ImageIcon("res\\fon.jpg").getImage(); // Объект который хранит изображение фона
    Panda panda = new PandaNorm();

    public Image getpandaImage() {
        return panda.pandaImage;
    }
    Image pandaImage = getpandaImage();
    Timer timer = new Timer(1,this);// создаём объект типа таймер
    int x =p.x; // Координата панды по х
    int vremya = 60 ;// время
    int kolo=0;


    Thread lollipopFactory = new Thread(this); // создаём поток
    List<Lollipop> lollipop = new ArrayList<Lollipop>(); //лист который хранит леденцы


    public Background(){ // Конструктор класса
        timer.start(); // запускаем таймер
        lollipopFactory.start();
        addKeyListener(new MyKeyAdapter());// добавляем обработчик событий
        setFocusable(true); // делаем фрейм в фокусе
    }


    public void paint(Graphics g){// Метод который рисует
        g=(Graphics2D)g;// Приведение типов
        g.drawImage(fon,0,0,null);// Рисую фон
        Player p= new Player();// Объект объект типа Player
        g.drawImage(getpandaImage(), x, p.y,null);//Рисую панду

        g.setColor(Color.black);
        Font font=new Font("Arial", Font.ITALIC, 25);
        g.setFont(font);


        g.drawString("Оставшееся время : "+vremya,50, 50);
        g.drawString("Поймано : "+kolo,50, 80);

        Iterator<Lollipop> i=lollipop.iterator(); //итереатор для прохода
        while (i.hasNext()){ //бесконечный цикл
            Lollipop e=i.next(); // Берём элемент из коллекции
            if (e.y>650){ // если ушел за пределы
                i.remove(); //удаляем
            }
            else { //если нет
                e.move();//двигаем
                g.drawImage(e.getkeksImage(), e.x, e.y, null);//рисуем
            }
        }

    }

    @Override
    public void run() { // события потока
        while (true){ //безконечный цикл
            Random rand = new Random(); //генератор случайных чисел
            try {
                Thread.sleep(1100);
                if (lollipop.size()> 10) {// Если в коллекции больше 10 элеметов
                    lollipop.clear(); // Очищаем коллекцию
                }
                lollipop.add(new Lollipop(rand.nextInt(300)+25,-75,rand.nextInt(2))); //создаём кекс
                vremya--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private class MyKeyAdapter extends KeyAdapter{ // обработчик событий
        @Override
        public void keyPressed(KeyEvent e) { // если нажата клавиша
            int key=e.getKeyCode(); // переменная которая хранит код клавиши
            if(key==KeyEvent.VK_RIGHT){ // если нажата клавиша вправо
        if(x<=270) {// проверка на то чтобы панда не уходила за экран
            panda= new PandaRight();
            pandaImage=getpandaImage();
            x += 50;// двигаем панду
        }
          else x=x;// если уходит то не изменяем координаты
        }
        if (key==KeyEvent.VK_LEFT){// если нажата клавиша влево
        if (x>=10) {// проверка на то чтобы панда не уходила за экран
            panda=new PandaLeft();
            pandaImage=getpandaImage();
            x -= 50;// двигаем панду

          }
            else x=x;// если уходит то не изменяем координаты
          }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            panda=new PandaNorm();
            pandaImage=getpandaImage();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) { // обработчик событий таймера

        testCollision();//Проверка на то, что панда съела кекс
        yroven();
        repaint(); // перересовываем

    }




    private void testCollision(){
        Iterator<Lollipop> ii = lollipop.iterator(); // Объект для работы с коллекцией
        while (ii.hasNext()){//Бессконечный цикл
            Lollipop e=ii.next();//Берем элемент Lollipop
            if (p.getRect().intersects(e.getRect())){//Если координаты панды и кекса совпадают, следовательно
                kolo++;
                ii.remove();
            }
        }

    }

    private void yroven(){
        if(vremya<0) {//Проверка на проигрышь
            if (kolo>30)
            JOptionPane.showMessageDialog(null,"Вы выиграли");
            else
                JOptionPane.showMessageDialog(null,"Вы проиграли");
            System.exit(1);//игра закрывается
        }

    }
}


