package com.example.wiprotest.utils;import java.io.*;import java.text.ParseException;import java.util.*;public class GenerateHugeInput {	public static void main(String[] args) throws IOException {		List<String> instruments = generateInstrumentNames();		List<String> dates = generateDates();		Random random = new Random();		try (Writer output = new OutputStreamWriter(new FileOutputStream("large-output.txt"))) {			instruments.forEach(instrument -> dates.forEach(date -> {				try {					output.write(instrument);					output.write(",");					output.write(date);					output.write(",");					output.write(Double.toString(random.nextDouble(10d)));					output.write("\n");				} catch (IOException ioe) {					throw new RuntimeException(ioe);				}			}));		}	}	private static Date silentlyParseDate(String stringDate) {		try {			return DateParser.parseDumpDate(stringDate);		} catch (ParseException e) {			throw new RuntimeException(e);		}	}	private static List<String> generateDates() {		List<String> list = new ArrayList<>();		Date startDate = silentlyParseDate("01-JAN-1994");		Date endDate = silentlyParseDate("30-DEC-2022");		GregorianCalendar calendar = new GregorianCalendar();		calendar.setTime(startDate);		while (calendar.getTime().before(endDate)) {			list.add(DateParser.formatDumpDate(calendar.getTime()));			calendar.add(GregorianCalendar.DATE, 1);		}		Collections.shuffle(list);		return list;	}	private static List<String> generateInstrumentNames() {		List<String> list = new ArrayList<>();		for (int i = 0; i < 10000; i++) {			list.add("INSTRUMENT" + i);		}		Collections.shuffle(list);		return list;	}}