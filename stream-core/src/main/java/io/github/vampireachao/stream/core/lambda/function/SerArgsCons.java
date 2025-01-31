package io.github.vampireachao.stream.core.lambda.function;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * SerArgsSerArgsCons
 *
 * @author VampireAchao
 * @since 2022/6/8
 */
public interface SerArgsCons<T> extends Serializable {

    /**
     * multi
     *
     * @param consumers lambda
     * @param <T>       type
     * @return lambda
     */
    @SafeVarargs
    static <T> SerArgsCons<T> multi(SerArgsCons<T>... consumers) {
        return Stream.of(consumers).reduce(SerArgsCons::andThen).orElseGet(() -> o -> {});
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input arguments
     */
    void accept(T... t);

    /**
     * Returns a composed {@code SerArgsCons} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code SerArgsCons} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default SerArgsCons<T> andThen(SerArgsCons<? super T> after) {
        Objects.requireNonNull(after);
        return (T... t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * nothing
     *
     * @return nothing
     */
    static <T> SerArgsCons<T> nothing() {
        return t -> {};
    }
}
