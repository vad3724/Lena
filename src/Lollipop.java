import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Пользователь on 15.11.2016.
 */
public class Lollipop {//класс кекса
    int x; // координата по х
    int y;// координата по у
    int v;// перемещение
    public Rectangle getRect(){//Конструктор
        return new Rectangle(x,y,50,50);// возвращаем прямоугольник размером с кекс
    }


    Keks keks;

    public Image getkeksImage() {
        return keks.keksImage;
    }

    public int getspeed() {
        return keks.speed;
    }


    Random rand = new Random();
    int i=rand.nextInt(4)+1;



    public Lollipop(int x,int y,int v){// конструктор
        this.x=x;
        this.y=y;
        if(i==1){
          keks=new KeksBrown();
            v=getspeed();
        }
        if (i==2){
           keks=new KeksGreen();
            v=getspeed();
        }
        if(i==3){
           keks=new KeksPink();
            v=getspeed();
        }
        if(i==4){
            keks=new KeksWhite();
            v=getspeed();
        }
        this.v=v;
    }

    public void move(){
        y=y+v;
    }// метод движения леденца

}
