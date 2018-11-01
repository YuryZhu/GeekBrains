package ru.yzhiharevich.yellowpages_lesson3;

import java.util.*;

public class YellowPages {
    public static void main(String[] args) {
        GuideRandomizer GuideRand = new GuideRandomizer();

        //Create the guide with random LastNames and Phones
        LinkedList<LastNames> ll = GuideRand.getGuide(20);
        String whatNameDoYouSearch = "Петров";

        // Add to the list a new entity(s) for searching
        ll.add(new LastNames(whatNameDoYouSearch, "79045555555"));
        ll.add(new LastNames(whatNameDoYouSearch, "79213333333"));

        //Search and count the duplicates
        Map<String, Integer> hm = new HashMap<>();
        for (LastNames e : ll) {
            Integer current = hm.get(e.getName());
            hm.put(e.getName(), current == null ? 1 : current + 1);
        }
        System.out.println(hm);

        //Search and print the asking information
        for (LastNames e : ll) {
            if (Arrays.asList(whatNameDoYouSearch).contains(e.getName()))
                e.info();
        }
    }
}
