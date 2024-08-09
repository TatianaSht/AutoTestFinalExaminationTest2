package autoTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathServiceTest {

    private MathService mathService;

    @BeforeEach
    void setUp() {
        mathService = new MathService();
    }


    @Epic("Итоговая контрольная работа по программе 'Автоматизация тестирования на Java'. Часть 1.")
    @Feature("Вычисление квадратных корней уравнения через дискриминант")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Вычисление дискриминанта")
    @ParameterizedTest
    @CsvSource(value = {"-1, 7, 8, 81",
            "4, 4, 1, 0",
            "1, 2, 8, -28",
            "1, 3, 2, 1"})
    void getDiscriminantTest(int a, int b, int c, int discriminant){
        Assertions.assertEquals(discriminant, mathService.getD(a, b, c));
    }


    @Epic("Итоговая контрольная работа по программе 'Автоматизация тестирования на Java'. Часть 1.")
    @Feature("Вычисление квадратных корней уравнения через дискриминант")
    @Severity(SeverityLevel.NORMAL)
    @Story("Обработка исключений. NotFoundAnswerException")
    @Test
    void getDLessThanZero_ShouldThrowNewNotFoundAnswerExceptionTest() {
        NotFoundAnswerException notFoundAnswerException = assertThrows(NotFoundAnswerException.class, () ->
                mathService.getAnswer(-3,4, -2));
        assertTrue(mathService.getD(-3,4, -2) < 0);
        Assertions.assertEquals("Корни не могут быть найдены", notFoundAnswerException.getMessage());
    }


    @Epic("Итоговая контрольная работа по программе 'Автоматизация тестирования на Java'. Часть 1.")
    @Feature("Вычисление квадратных корней уравнения через дискриминант")
    @Severity(SeverityLevel.NORMAL)
    @Story("Обработка исключений. ArithmeticException")
    @Test
    void getDivisionByZero_ShouldThrowNewArithmeticException() {
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () ->
                mathService.getAnswer(0,0, 0));
        Assertions.assertEquals("/ by zero", arithmeticException.getMessage());
    }


    @Epic("Итоговая контрольная работа по программе 'Автоматизация тестирования на Java'. Часть 1.")
    @Feature("Вычисление квадратных корней уравнения через дискриминант")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Дискриминант = 0. Квадратные корни равны")
    @Test
    public void squareRootsAreEqual_IfTheDiscriminantIsZeroTest() throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(9, -6, 1);
        Assertions.assertEquals(0, mathService.getD(9, -6, 1));
        Assertions.assertEquals(pair.first, pair.second);

    }


    @Epic("Итоговая контрольная работа по программе 'Автоматизация тестирования на Java'. Часть 1.")
    @Feature("Вычисление квадратных корней уравнения через дискриминант")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Вычисление первого квадратного корня")
    @ParameterizedTest
    @CsvSource({"-1, 7, 8, -1.0",
                "4, 4, 1, 0.5",
                "-1, 2, 8, -2.0",
                "1, 3, 2, -1.0"})
    public void getFirstSquareRoot_IfTheDiscriminantIsGreaterThanZeroTest(int a, int b, int c, Double rootFirst)
            throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(a, b, c);
        assertTrue(mathService.getD(a, b, c) > 0);
        Assertions.assertEquals(rootFirst, pair.first);
    }


    @Epic("Итоговая контрольная работа по программе 'Автоматизация тестирования на Java'. Часть 1.")
    @Feature("Вычисление квадратных корней уравнения через дискриминант")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Вычисление второго квадратного корня")
    @ParameterizedTest
    @CsvSource({"-1, 7, 8, 8.0",
                "4, 4, 1, 0.5",
                "-1, 2, 8, -4.0",
                "1, 3, 2, -2.0"})
    public void getSecondSquareRoot_IfTheDiscriminantIsGreaterThanZeroTest(int a, int b, int c, Double rootSecond)
            throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(a, b, c);
        assertTrue(mathService.getD(a, b, c) > 0);
        Assertions.assertEquals(rootSecond, pair.second);
    }


    @Epic("Итоговая контрольная работа по программе 'Автоматизация тестирования на Java'. Часть 1.")
    @Feature("Вычисление квадратных корней уравнения через дискриминант")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Вывод Answer text")
    @Test
    void getStringAnswerTest() throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(4, 4, 1);
        Assertions.assertEquals("Answer{first=" + pair.first+", second=" + pair.second+"}",
                String.valueOf(mathService.getAnswer(4, 4, 1)));
    }

}
