package org.example;

class Numbers {
    private int n;
    private int currentNumber;

    public Numbers(int n) {
        this.n = n;
        this.currentNumber = 1;
    }

    public synchronized void fizz() {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                System.out.println("fizz");
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void buzz() {
        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 && currentNumber % 5 == 0) {
                System.out.println("buzz");
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void fizzBuzz() {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                System.out.println("fizzbuzz");
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void number() {
        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                System.out.println(currentNumber);
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        Numbers fizzBuzz = new Numbers(n);

        Thread threadA = new Thread(() -> fizzBuzz.fizz());
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzBuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}