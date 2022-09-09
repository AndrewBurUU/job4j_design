package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> chat;
    private List<String> botWordList;
    private int countBotWords;
    private boolean botMode;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.chat = new ArrayList<>();
        this.botMode = true;
        this.botWordList = new ArrayList<>();
        this.countBotWords = 0;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a word");
        String userWord = "";
        List<String> botListAnswers = readPhrases();
        String botWord = "";

        while (!OUT.equals(userWord)) {
            System.out.print("User : ");
            userWord = in.nextLine();
            chat.add(String.format("User : %s", userWord));
            if (OUT.equals(userWord)) {
                break;
            }
            if (STOP.equals(userWord)) {
                System.out.println(String.format("Bot stop mode. Enter %s to continue", CONTINUE));
                botMode = false;
            }
            if (CONTINUE.equals(userWord)) {
                System.out.println(String.format("Bot online mode. Enter %s to stop mode", STOP));
                botMode = true;
            }
            if (botMode) {
                botWord = String.format("Bot: %s", getBotWord());
                System.out.println(botWord);
                chat.add(botWord);
            }
        }
        in.close();
    }

    private List<String> readPhrases() {
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            String str;
            while ((str = in.readLine()) != null) {
                botWordList.add(str);
                countBotWords++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return botWordList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)
                ))) {
            log.forEach(s -> out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBotWord() {
       String res = "";
       int randomNum = (int) (Math.random() * countBotWords);
       res = botWordList.get(randomNum);
       return res;
    }

    public static void main(String[] args) {
         ConsoleChat cc = new ConsoleChat("./data/chat/chat.txt", "./data/chat/botWords.txt");
        cc.run();
        cc.saveLog(cc.chat);
    }
}
