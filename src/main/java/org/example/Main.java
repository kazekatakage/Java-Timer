package org.example;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Main {

    /**
     * Simple method to play the audio clip.
     * @param location
     */
    public static void playMusic(String location) {
        try {
            File musicPath = new File(location);

            if (musicPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception e) {
            System.out.println("Exception thrown. MESSAGE: " + e.getMessage());
        }
    }


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
                    System.out.println("TIME'S UP!");
                    String filePath = "alarm.wav";
                    playMusic(filePath);
                    JOptionPane.showMessageDialog(null,"TIME'S UP!");
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task,0,1000);
    }
}
