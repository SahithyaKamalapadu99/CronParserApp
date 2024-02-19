package org.example.service;

import org.example.model.CronSchedule;

import java.util.ArrayList;

public class CronParseService {
    private static final int EXPRESSION_LENGTH = 6;
    public static CronSchedule parseCronExpression(String expression) {
        String[] parts = expression.split(" ");
        if (parts.length < EXPRESSION_LENGTH) {
            throw new IllegalArgumentException(String.format("Invalid cron expression, expecting at least %d parts", EXPRESSION_LENGTH));
        }

        CronSchedule schedule = new CronSchedule();


        schedule.minute = parseCronField(parts[0], 0, 59);
        schedule.hour = parseCronField(parts[1], 0 ,23);
        schedule.dayOfMonth = parseCronField(parts[2], 1, 31);
        schedule.month = parseCronField(parts[3], 1, 12);
        schedule.dayOfWeek = parseCronField(parts[4], 1, 7);
        schedule.command = parts[5];

        return schedule;
    }
    public static ArrayList<Integer> parseCronField(String value, int min, int max) {
        ArrayList<Integer> result = new ArrayList<>();

        // Handle */increment
        if (value.contains("*/")) {
            int increment = Integer.parseInt(value.substring(value.indexOf("/") + 1));
            for (int i = min; i <= max; i += increment) {
                result.add(i);
            }
        } else if (value.contains("-")) {  // Handle ranges
            String[] range = value.split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            if (start < min || end > max) {
                throw new IllegalArgumentException("Range out of bounds");
            }

            for (int i = start; i <= end; i++) {
                result.add(i);
            }
        } else if (value.contains(",")) {  // Handle comma separated values
            for (String str : value.split(",")) {
                int val = Integer.parseInt(str);
                if (val < min || val > max) {
                    throw new IllegalArgumentException("Value out of bounds");
                }
                result.add(val);
            }
        } else if (value.equals("*")) {   // Handle all
            for (int i = min; i <= max; i++) {
                result.add(i);
            }
        } else {   // Handle single value
            int val = Integer.parseInt(value);
            if (val < min || val > max) {
                throw new IllegalArgumentException("Value out of bounds");
            }
            result.add(val);
        }

        return result;
    }
}
