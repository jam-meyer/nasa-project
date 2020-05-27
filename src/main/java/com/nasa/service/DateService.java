package com.nasa.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.lang3.time.DateUtils;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;



@Service

public class DateService {

	public Set<String> dateSet = new HashSet<>();
	@Autowired
	private ResourceLoader resourceLoader;

	public Set<String> getAndFormatDates() {
		try {
//			public String dateTextPath = "/nasaDates.txt";
//			File fileObject = new File("resources/nasaDates.txt");
//			Scanner reader = new Scanner(fileObject);
			Resource resource = resourceLoader.getResource("classpath:nasaDates.txt");
			InputStream inputStream = resource.getInputStream();
			Scanner reader = new Scanner(inputStream);
			String[] possibleDateFormats = {"MM/dd/yy","MMM d, yyyy","MMM-d-yyyy"};
			while (reader.hasNextLine()) {
				String date = reader.nextLine();
				try {
				Date parsedDate = DateUtils.parseDateStrictly(date, possibleDateFormats);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				dateSet.add(formatter.format(parsedDate));
				System.out.println(formatter.format(parsedDate));
				}
				catch (ParseException e){
					System.out.println("Date provided cannot be parsed");
					e.printStackTrace();
				}
			}
			reader.close();
		}
			
		catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
			return dateSet;
	}
}
