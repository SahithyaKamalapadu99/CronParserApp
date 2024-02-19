# Cron Expression Parser

This is a simple command-line application to parse cron schedule expressions and expand them to show the times at which they will run.

## Usage

The application accepts a single cron expression as an argument:

```
java CronParseApplication
"*/15 0 1,15 * 1-5 /usr/bin/script"
```

Example output:

```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/script
```
