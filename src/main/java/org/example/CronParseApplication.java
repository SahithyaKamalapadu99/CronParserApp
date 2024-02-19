package org.example;

import org.example.model.CronSchedule;
import org.example.service.CronParseService;

import java.util.List;
import java.util.Scanner;

public class CronParseApplication {

    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a cron expression (or type 'exit' to quit):");
            String cronExpression = scanner.nextLine().trim();
            if (cronExpression.equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }
            processCronExpression(cronExpression);
        }
        scanner.close();
    }

    private static void processCronExpression(String cronExpression) {
        try {
            CronSchedule schedule = CronParseService.parseCronExpression(cronExpression);
            printSchedule(schedule);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid expression: " + e.getMessage());
        }
    }

    private static void printSchedule(CronSchedule schedule) {
        System.out.println("Schedule:");
        printLine("Minute", join(schedule.minute, " "));
        printLine("Hour", join(schedule.hour, " "));
        printLine("Day of Month", join(schedule.dayOfMonth, " "));
        printLine("Month", join(schedule.month, " "));
        printLine("Day of Week", join(schedule.dayOfWeek, " "));
        printLine("Command", schedule.command);
        System.out.println();
    }

    private static void printLine(String label, String value) {
        System.out.printf("%-15s%s\n", label + ":", value);
    }

    private static String join(List<Integer> list, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
