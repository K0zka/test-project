package com.example.wiprotest.service.instruments;import com.example.wiprotest.model.CalculatedPrice;import com.example.wiprotest.model.InstrumentPriceHistory;import com.example.wiprotest.utils.DateParser;import com.google.common.collect.ImmutableList;import org.junit.jupiter.api.Test;import java.text.ParseException;import java.util.List;import static org.junit.jupiter.api.Assertions.*;class FallbackInstrumentPriceCalculatorTest {	@Test	void calculate() throws ParseException {		/*		 * For any other instrument from the input file - sum of the newest 10 elements (in terms of the date).		 */		// GIVEN		List<InstrumentPriceHistory> records = ImmutableList.<InstrumentPriceHistory>builder()			// this extreme value is about to be ignored as it is not one of the 10 newest			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("30-DEC-2022"), 100d))			// the ten newest			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("01-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("02-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("03-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("04-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("05-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("06-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("07-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("08-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("09-JAN-2023"), 1d))			.add(new InstrumentPriceHistory("INSTRUMENTX", DateParser.parseDumpDate("10-JAN-2023"), 1d))			.build();		// WHEN		CalculatedPrice calculatedPrice = new FallbackInstrumentPriceCalculator().calculate(records);		// THEN		assertEquals(10d, calculatedPrice.calculatedPrice());	}}