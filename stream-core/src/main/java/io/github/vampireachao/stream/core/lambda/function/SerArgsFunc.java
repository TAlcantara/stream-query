package io.github.vampireachao.stream.core.lambda.function;

import java.io.Serializable;
import java.util.Objects;

/**
 * 可序列化的SerArgsFunc
 *
 * @author VampireAchao
 * @see SerArgsFunc
 */
@FunctionalInterface
public interface SerArgsFunc<T, R> extends Serializable {

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> SerArgsFunc<T, T> last() {
        return t -> t != null && t.length > 0 ? t[t.length - 1] : null;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T... t);

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>    the type of input to the {@code before} function, and to the
     *               composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     * @see #andThen(SerArgsFunc)
     */
    default <V> SerArgsFunc<V, R> compose(SerArgsFunc<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V... v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(SerArgsFunc)
     */
    default <V> SerArgsFunc<T, V> andThen(SerArgsFunc<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T... t) -> after.apply(apply(t));
    }
}