package app.cn.qtt.bm.common.utils;

import java.util.Calendar;


public class RandomUtils {

	protected static String zeroPad(int length, String toPad) {
		int numberOfZeroes = length - toPad.length();
		for (int counter = 0; counter < numberOfZeroes; counter++) {
			toPad = "0" + toPad;
		}
		return toPad;
	}
	
	public static String getLineTimeStamp() {
		String retDate = "";
		String padString = "";
		Calendar fullCalendar = Calendar.getInstance();

		// for naming Validations, the array goes from 0-6 DAY_OF_WEEK is 1-7

		retDate = Integer.toString(fullCalendar.get(Calendar.YEAR))
				+ zeroPad(2, Integer
						.toString(fullCalendar.get(Calendar.MONTH) + 1))
				+ zeroPad(2, Integer.toString(fullCalendar.get(Calendar.DATE)));
		padString = Integer.toString(fullCalendar.get(Calendar.HOUR_OF_DAY));
		retDate += zeroPad(2, padString);
		padString = Integer.toString(fullCalendar.get(Calendar.MINUTE));
		retDate += zeroPad(2, padString);
		padString = Integer.toString(fullCalendar.get(Calendar.SECOND));
		retDate += zeroPad(2, padString);
		padString = Integer.toString(fullCalendar.get(Calendar.MILLISECOND));
		retDate += zeroPad(3, padString);
		return retDate;
	}
	
	public static String Random() {
		java.util.Random r = new java.util.Random();
		String random = Integer.toString(r.nextInt());
		if (random.length() >= 4) {
			random = random.substring(random.length() - 3, random.length());
		}
		return random;
	}
	
	public static String getMmsId(){
		String mmsId = getLineTimeStamp() + Random();
		return mmsId;
	}
	
	public static String getBatchId(){
		String batchId = getLineTimeStamp() + Random();
		return batchId;
	}
}