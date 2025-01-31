package io.github.vampireachao.stream.core.stream;

import io.github.vampireachao.stream.core.collector.Collectors;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * stream辅助类
 *
 * @author VampireAchao &lt; achao1441470436@gmail.com &gt;
 * @since 2022/5/26 19:11
 */
public class StreamHelper {

    private StreamHelper() {
        /* Do not new me! */
    }

    public static <T, R> Set<R> mapToSet(Stream<T> stream, Function<T, R> function) {
        return stream.map(function).collect(Collectors.toSet());
    }

    public static <T, R> Set<R> mapToSet(List<T> dataList, Function<T, R> function) {
        return dataList.stream().map(function).collect(Collectors.toSet());
    }

    public static <T, R> List<R> mapToList(Stream<T> stream, Function<T, R> function) {
        return stream.map(function).collect(Collectors.toList());
    }

    public static <T, R> List<R> mapToList(List<T> dataList, Function<T, R> function) {
        return dataList.stream().map(function).collect(Collectors.toList());
    }

    public static <T, R> Set<R> listToSet(List<T> dataList, Function<T, R> function) {
        return dataList.stream().map(function).collect(Collectors.toSet());
    }

    public static <T, K> Map<K, T> listToMap(List<T> dataList, Function<T, K> function) {
        return dataList.stream().collect(Collectors.toMap(function, Function.identity()));
    }

    public static <T, K, V> Map<K, V> listToMap(List<T> dataList, Function<T, K> keyFunction, Function<T, V> valueFunction) {
        return dataList.stream().collect(Collectors.toMap(keyFunction, valueFunction));
    }

}
