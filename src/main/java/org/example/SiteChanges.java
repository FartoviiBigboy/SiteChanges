package org.example;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SiteChanges {

    private Sites sites;
    private String name;

    public SiteChanges(Sites sites, String name) {
        this.sites = sites;
        this.name = name;
    }

    public String getChanges() {
        String result = String.join("\n"
                , "Здравствуйте, дорогая " + name
                , ""
                , "За последние сутки во вверенных Вам сайтах произошли следующие изменения:"
                , ""
                , "Исчезли следующие страницы: " + getDeleted()
                , "Появились следующие новые страницы: " + getAdded()
                , "Изменились следующие страницы: " + getModified()
                , ""
                , "С уважением,"
                , "автоматизированная система"
                , "мониторинга."
        );
        return result;
    }

    private String getDeleted() {
        HashSet<URL> deleted = new HashSet<>(sites.getYesterday().keySet());
        deleted.removeAll(sites.getToday().keySet());
        return toStringList(deleted);
    }

    private String getAdded() {
        HashSet<URL> added = new HashSet<>(sites.getToday().keySet());
        added.removeAll(sites.getYesterday().keySet());
        return toStringList(added);
    }

    private String getModified() {
        HashSet<URL> intersected = new HashSet<>(sites.getToday().keySet());
        intersected.retainAll(sites.getYesterday().keySet());

        HashSet<URL> result = new HashSet<>();
        for (URL url : intersected) {
            String todayHtml = sites.getToday().get(url);
            String yesterdayHtml = sites.getYesterday().get(url);
            if (todayHtml.equals(yesterdayHtml) == false) {
                result.add(url);
            }
        }
        return toStringList(result);
    }

    private String toStringList(HashSet<URL> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}

