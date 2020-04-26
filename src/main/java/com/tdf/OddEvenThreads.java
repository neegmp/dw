package com.tdf;

public class OddEvenThreads {

    public static void main(String[] args) {

        Odd odd = new Odd();
        Even even = new Even();
        odd.start();
        even.start();
    }
}

class Even extends Thread{

    @Override
    public void run() {
        try {
            printEven();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized private void printEven() throws InterruptedException {
        int i=NumClass.num;
        while (i <= 10) {
            if (i % 2 != 0)
                wait();
            System.out.println(i);
            NumClass.incrementNum();
            Thread.sleep(500);
            i=NumClass.num;
            notifyAll();
        }
    }
}


class Odd extends Thread{

    @Override
    public void run() {
        try {
            printOdd();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized private void printOdd() throws InterruptedException {
        int i = NumClass.num;
        while (i <= 10) {
            if(i%2==0)
                wait();
            System.out.println(i);
            NumClass.incrementNum();
            Thread.sleep(500);
            i=NumClass.num;
            notifyAll();
        }
    }
}

class NumClass {
    static int num=1;

    public static void incrementNum(){
       num++;
    }

    public static int getNum(){
        return num;
    }

}
