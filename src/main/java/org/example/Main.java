package org.example;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static void main(String[] args) {

        // Scanner for user input.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter # of seconds to count down from: ");
        int response = scanner.nextInt();

        // Timer execution
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int count = response;

            @Override
            public void run() {
                System.out.println(count);
                count--;
                if (count < 0) {
                    System.out.println("YOU DID IT!");
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task,0,1000);
    }
}
