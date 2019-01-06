package com.sked.android.workmanager.log;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

public final class LoggerUtility {
	public static String applicationLogFolderName = "DemoApp";
	public static String log_dir = Environment.getExternalStorageDirectory() + "/" + applicationLogFolderName;
	public static String log_filename = "log.txt";
	public static String crashlog_filename = log_dir + "/crashlog.txt";
	public static long   maxFileSizeBytes  = 5*1024*1024;
	
	public static void Print(String tag, String data) {
		Write(tag, data);
	}
	
	public static void PrintAPIBegin(String tag, String APIName) {
		Print(tag, "------------------ " + APIName + "--- S");
	}
	
	public static void PrintAPIEnd(String tag, String APIName) {
		Print(tag, "------------------ " + APIName + "--- E");
	}
	
	public static void Write(String tag, String data) {
     String type_str = "Verbose";
     Log.d(tag,data);
		
		if(log_filename == null || log_dir == null || data == null)
			return;

		Date currentDate  = Calendar.getInstance().getTime();
		
       	File log_dir1 = new File(log_dir);
       	if(log_dir1.exists() != true)
       		log_dir1.mkdir();
       	
		FileOutputStream log_fos = null;
		File log_file = new File(getLogFilePath(currentDate));
		
		try {
			if(log_file.exists() != true) {
				log_file.createNewFile();				
			}
			else if (log_file.length() > maxFileSizeBytes) {
				log_file.delete();		
				log_file.createNewFile();				
			}
			
			if (log_file.length() < maxFileSizeBytes) {
				String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(currentDate);
				log_fos = new FileOutputStream(log_file, true);
				String str = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				str += "\t" + tag + "\t" + data + "\n";
				log_fos.write(str.getBytes());				
			}
		} catch (Exception e) {
			Log.e(tag, "Write: " + e.getMessage());
		}
		finally
		{
			try
			{
				if(log_fos != null)
					log_fos.close();
			}
			catch(Exception e)
			{
				Log.e(tag, "WRITE: " + "file close failed");
			}
		}
	}
	
	public static String getLogFilePath(Date date) {
		String dateString = getLogFileDateString(date);
		
		return(log_dir + "/" + dateString + "_" + log_filename);
	}
	
	public static String getLogFileDateString(Date date) {
		return(new java.text.SimpleDateFormat("yyyy-MM-dd").format(date));				
	}	
}