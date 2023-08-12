package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExtract {

    public static Map<String, String> keyValueError(String message) {

        Map<String, String> result = new HashMap<>();

        // Définir le motif de l'expression régulière pour extraire les informations

        // Trouver l'indice du premier apostrophe
        int firstApostropheIndex = message.indexOf("'");

        // Trouver l'indice du dernier apostrophe
        int lastApostropheIndex = message.lastIndexOf("'");

        // Extraire la sous-chaîne entre les apostrophes
        String duplicateEntry = message.substring(firstApostropheIndex + 1, lastApostropheIndex);

        // Trouver l'indice du premier point après "key"
        int dotAfterKeyIndex = message.indexOf('.', message.indexOf("key"));

        // Extraire la sous-chaîne entre l'espace après "key" et le point
        String key = message.substring(message.indexOf(' ', message.indexOf("key")) + 1, dotAfterKeyIndex);

        // Afficher les informations extraites
        System.out.println("Duplicate entry: " + duplicateEntry);
        System.out.println("Key: " + key);
        result.put(key, duplicateEntry);

        return result;
    }
}

