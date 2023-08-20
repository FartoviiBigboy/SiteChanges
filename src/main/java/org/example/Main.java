package org.example;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.lang.reflect.Type;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {

    public static String getStringFromFile(String name) {
        Path path = Paths.get(name);

        String content;
        try {
            byte[] encoded = Files.readAllBytes(path);
            content = new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            content = "";
        }
        return content;
    }


    public static void main(String[] args) {
        System.out.println("Текущая рабочая директория = " + System.getProperty("user.dir"));
        System.out.println("Введите имя секретаря и.о. секретаря, путь к json файлу с состоянием сайтов на вчера, путь к json файлу с состоянием сайтов на сегодня через enter:");

        Type type = new TypeToken<Hashtable<URL, String>>() {
        }.getType();
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        Hashtable<URL, String> yesterdaySites = new Gson().fromJson(getStringFromFile(in.nextLine()), type);
        Hashtable<URL, String> todaySites = new Gson().fromJson(getStringFromFile(in.nextLine()), type);

        Sites sites = new Sites(todaySites, yesterdaySites);
        System.out.println(new SiteChanges(sites, name).getChanges());
    }
}