package com.restassured.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.restassured.constants.Constants;

public class CsvDataProvider {

	@DataProvider(name = "ContentItemData")
	public static Iterator<Object[]> provideData(Method method) throws CsvValidationException {
		List<Object[]> list = new ArrayList<Object[]>();
		String pathName = Constants.CSV_FILE_PATH;
		File file = new File(pathName);
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] keys = reader.readNext();
			if (keys != null) {
				String[] dataParts;
				while ((dataParts = reader.readNext()) != null) {
					Map<String, String> testData = new HashMap<String, String>();
					for (int i = 0; i < keys.length; i++) {
						testData.put(keys[i], dataParts[i]);
					}
					list.add(new Object[] { testData });
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + pathName + " was not found. \n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + pathName + " file.\n" + e.getStackTrace().toString());
		}

		return list.iterator();
	}
}
