package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class MainTest {

    @ParameterizedTest
    @MethodSource("dataForArraySliceAfterLast4")
    void arraySliceAfterLast4(int[] actualArray, int[] expectedArray) {
        Assertions.assertArrayEquals(expectedArray, Main.arraySliceAfterLast4(actualArray));
    }

    public static Stream<Arguments> dataForArraySliceAfterLast4() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}),
                Arguments.of(new int[]{3, 8, 4, 2, 9, 1, 3, 8}, new int[]{2, 9, 1, 3, 8}),
                Arguments.of(new int[]{6, 4, 7}, new int[]{7})
        );
    }

    @ParameterizedTest
    @MethodSource("dataForArraySliceAfterLast4Exception")
    void arraySliceAfterLast4EmptyArrayException(int[] actualArray) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Main.arraySliceAfterLast4(actualArray));
    }

    public static Stream<Arguments> dataForArraySliceAfterLast4Exception() {
        return Stream.of(
                Arguments.of(new int[]{2, 1, 7, 1, 5, 2, 6, 3, 8}),
                Arguments.of(new int[0])
        );
    }

    @ParameterizedTest
    @MethodSource("dataForCheckCompositionArrayFrom1And4NumbersException")
    void checkCompositionArrayFrom1And4Numbers(int[] actualArray, boolean expectedReply) {
        Assertions.assertEquals(expectedReply, Main.checkCompositionArrayFrom1And4Numbers(actualArray));
    }

    public static Stream<Arguments> dataForCheckCompositionArrayFrom1And4NumbersException() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 1, 4, 4, 1, 4, 4}, true),
                Arguments.of(new int[]{1, 1, 1, 1, 1, 1}, false),
                Arguments.of(new int[]{4, 4, 4, 4}, false),
                Arguments.of(new int[]{1, 4, 4, 1, 1, 4, 3}, false)
        );
    }

    @Test
    void checkCompositionArrayFrom1And4NumbersException() { // Проверка на пустой массив и выброс исключения
        int[] actualArray = new int[0];
        Assertions.assertThrows(IllegalArgumentException.class, () -> Main.arraySliceAfterLast4(actualArray));
    }
}