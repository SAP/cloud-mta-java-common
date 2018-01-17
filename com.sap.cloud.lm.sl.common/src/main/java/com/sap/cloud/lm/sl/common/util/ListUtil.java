package com.sap.cloud.lm.sl.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class ListUtil {

    public static <E> List<E> upcastUnmodifiable(List<? extends E> list) {
        return unmodifiable(list);
    }

    public static <E> List<E> upcast(List<? extends E> list) {
        return list == null ? null : new ArrayList<>(list);
    }

    public static <E> List<E> castUnmodifiable(List<?> list) {
        return unmodifiable(ListUtil.<E> cast(list));
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> cast(List<?> list) {
        return list == null ? null : new ArrayList<>((List<? extends E>) list);
    }

    public static <E> List<E> unmodifiable(List<? extends E> list) {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    public static <T> List<T> merge(List<T> original, List<T> override) {
        List<T> result = new ArrayList<T>();
        result.addAll(original);
        result.addAll(override);
        return result;
    }

    public static <T> void addNonNull(List<T> list, T item) {
        if (item != null) {
            list.add(item);
        }
    }

    public static <T> List<T> asList(T item) {
        if (item == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(item);
    }

    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<T>(new LinkedHashSet<T>(list));
    }

    public static <T> void addUnique(List<T> original, List<T> newValues) {
        for (T item : newValues) {
            if (!original.contains(item)) {
                original.add(item);
            }
        }
    }

}
