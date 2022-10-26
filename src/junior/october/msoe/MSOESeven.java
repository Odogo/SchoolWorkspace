package junior.october.msoe;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
Enter UNIX Time: 1666816222
08:30:22 PM 26/10/2022

Date and time (GMT): Wednesday, October 26, 2022 8:30:22 PM
Date and time (CST - "America/Chicago"): Wednesday, October 26, 2022 3:30:22 PM GMT-05:00
 */
public class MSOESeven {
	
	private static final int SECONDS_PER_MINUTE = 60;
	private static final int SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE;
	private static final int SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR;
	
	private static final int DAYS_PER_YEAR = 365;
	private static final int DAYS_PER_LEAP_YEAR = DAYS_PER_YEAR + 1;
	
	private static final int EPOCH_MONTH = 1;
	private static final int EPOCH_YEAR = 1970;

	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Enter UNIX Time: ");
			int unix = Integer.parseInt(scanner.nextLine());
			
			int days = unix / SECONDS_PER_DAY;
			int year = EPOCH_YEAR;
			
			while(days >= getDaysForYear(year)) {
				days -= getDaysForYear(year);
				year++;
			}
			
			int[] daysPerMonth = getDaysPerMonth(year);
			int month = EPOCH_MONTH;
			while(days >= daysPerMonth[month]) {
				days -= daysPerMonth[month];
				month++;
			}
			
			int day = days + 1;
			int seconds = unix % SECONDS_PER_DAY;
			int hour = seconds / SECONDS_PER_HOUR;
			int minute = (seconds / SECONDS_PER_MINUTE) % SECONDS_PER_MINUTE;
			int second = seconds % SECONDS_PER_MINUTE;
			
			DecimalFormat f = new DecimalFormat("00");
			System.out.println(f.format(hour % 12) + ":" + f.format(minute) + ":" + f.format(second) + " " + (hour >= 12 ? "PM": "AM") + " " + f.format(day) + "/" + f.format(month) + "/" + f.format(year));
			
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
	private static int getDaysForYear(int year) {
		return isLeapYear(year) ? DAYS_PER_LEAP_YEAR : DAYS_PER_YEAR;
	}
	
	private static int[] getDaysPerMonth(int year) {
		return new int[] {
				0,
				31,
				(isLeapYear(year) ? 29 : 28),
				31,
				30,
				31,
				30,
				31,
				31,
				30,
				31,
				30,
				31
		};
	}
	
	private static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}
}
