package ru.ob6.web.utils;

public class TrailerUrlParser {
    public static String parse(String url) {
        String[] split = url.split("/");
        return split[split.length - 1];
    }
}
