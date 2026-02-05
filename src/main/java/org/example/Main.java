package org.example;

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
     * @param location - File location.
     */
    public static void playSound(String location) {
        try {
            File soundPath = new File(location);

            if (soundPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                clip.loop(10);
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception e) {
            System.out.println("Exception thrown. MESSAGE: " + e.getMessage());
        }
    }


    static void main(String[] args) {

        // Dialogue box for user input:
        String input = JOptionPane.showInputDialog("Enter # of seconds to count down from: ");
        System.out.println("Enter # of seconds to count down from: ");
        int response = Integer.parseInt(input);

        // Timer execution:
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
                    playSound(filePath);
                    JOptionPane.showMessageDialog(null, "TIME'S UP!", "Timer Results", JOptionPane.INFORMATION_MESSAGE);
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task,0,1000);
    }
}
