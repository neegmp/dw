package com.tdf;

public class OddEvenThreads2 {

    public static void main(String[] args) {

        Odd2 odd = null;
        Even2 even = null;
        odd = new Odd2(0);
        even = new Even2(0);

        for(int i=1;i<=100;i++){

            Even2.num=i;
            Odd2.num=i;

            if (!odd.isAlive()) {
                odd.start();
            }
            if (!even.isAlive()) {
                even.start();
            }
        }
    }
}

class Even2 extends Thread{

    public static int num = 0;
    public Even2(int i) {
        this.num=i;
    }

    @Override
    public void run() {
        //System.out.println("In Even");
        try {
                printEven(this.num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized private void printEven(int i) throws InterruptedException {

        if(i%2!=0)
            this.wait();
        else{
            System.out.println(i);
            this.notifyAll();
        }
    }
}


class Odd2 extends Thread{

    public static int num = 0;
    public Odd2(int i) {
        this.num=i;
    }

    @Override
    public void run() {
        //System.out.println("In Odd");
        try {
            printOdd(this.num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized private void printOdd(int i) throws InterruptedException {

        if(i%2==0)
            this.wait();
        else{
            System.out.println(i);
            this.notifyAll();
        }
    }
}

