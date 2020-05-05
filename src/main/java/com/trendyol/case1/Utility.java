package com.trendyol.case1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

    public static List<Card> cardList(Integer... values) {
        return Stream.of(values).map(Card::new).collect(Collectors.toCollection(ArrayList::new));
    }
}
