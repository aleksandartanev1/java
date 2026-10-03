import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import src.Calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

class CalculatorTest {

    private Calculator calculator;

    // Ова се извршува ПРЕД секој поединечен тест метод
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAddition() {
        int result = calculator.add(2, 3);
        // Проверуваме дали резултатот е еднаков на 5
        assertEquals(5, result, "2 + 3 треба да биде 5");
    }

    @Test
    void testDivisionByZero() {
        // Проверуваме дали фрла исклучок (Exception) кога делиме со 0
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
    }

    @Test
    void testPerformance() {
        // Проверуваме дали парче код завршува за помалку од 200 милисекунди
        assertTimeout(Duration.ofMillis(200), () -> {
            // Овде оди кодот што го тестираме
            calculator.add(5, 5);
        });
    }

    @Test
    void testSubtract(){
        int result = calculator.subtract(8, 4);
        assertEquals(4, result, "8 - 4 treba da e 4");
    }
}