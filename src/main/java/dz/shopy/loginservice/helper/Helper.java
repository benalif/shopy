package dz.shopy.loginservice.helper;

import java.util.Calendar;
import java.util.Date;

public class Helper {

	public static Date addDays(int daysNumber) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysNumber);

		return cal.getTime();
	}
}
