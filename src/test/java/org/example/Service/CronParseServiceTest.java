package org.example.Service;

import org.example.service.CronParseService;
import org.example.model.CronSchedule;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CronParseServiceTest {

    @Test
    public void testParseValidExpression() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";
        CronSchedule schedule = CronParseService.parseCronExpression(expression);
        
        assertEquals(generateList(0, 15, 30, 45), schedule.minute);
        assertEquals(generateList(0), schedule.hour);
        assertEquals(generateList(1, 15), schedule.dayOfMonth);
        assertEquals(generateList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), schedule.month);
        assertEquals(generateList(1, 2, 3, 4, 5), schedule.dayOfWeek);
        assertEquals("/usr/bin/find", schedule.command);
    }

    // Test invalid cron expression with insufficient parts
    @Test
    public void testParseInvalidParts() {
        String expression = "* * * *";
        try {
            CronParseService.parseCronExpression(expression);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid cron expression, expecting at least 6 parts", e.getMessage());
        }
    }

    // Test invalid cron expression with value out of bounds
    @Test
    public void testParseValueOutOfBounds() {
        String expression = "*/15 122 15 * 6 /usr/bin/script";
        try {
            CronParseService.parseCronExpression(expression);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Value out of bounds", e.getMessage());
        }
    }

    @Test
    public void testParseIncrements() {
        String expression = "*/10 */2 * * * /usr/bin";
        CronSchedule schedule = CronParseService.parseCronExpression(expression);
        
        assertEquals(generateList(0, 10, 20, 30, 40, 50), schedule.minute);
        assertEquals(generateList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22), schedule.hour);
    }

    @Test
    public void testParseRanges() {
        String expression = "0-10 0-5 * * * /command";
        CronSchedule schedule = CronParseService.parseCronExpression(expression);
        
        assertEquals(generateList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), schedule.minute);
        assertEquals(generateList(0, 1, 2, 3, 4, 5), schedule.hour);
    }

    @Test
    public void testParseWeekdays() {
        String expression = "0 0 * * 1-5 command";
        CronSchedule schedule = CronParseService.parseCronExpression(expression);

        assertEquals(generateList(1, 2, 3, 4, 5), schedule.dayOfWeek);
    }

    @Test
    public void testParseAllValues() {
        String expression = "* * * * * run_me";
        CronSchedule schedule = CronParseService.parseCronExpression(expression);

        assertEquals(generateList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59), schedule.minute);
        assertEquals(generateList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23), schedule.hour);

    }

    private static ArrayList<Integer> generateList(int... values) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int value : values) {
            list.add(value);
        }
        return list;
    }

}