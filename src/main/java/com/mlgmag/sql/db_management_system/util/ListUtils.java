package com.mlgmag.sql.db_management_system.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ListUtils {

    private ListUtils() {
    }

    public static List<String> addNumberPrefixToListItems(List<String> list) {
        return IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d.%s", i, list.get(i)))
                .collect(Collectors.toList());
    }
}
