package com.example.wiprotest.utils;import com.example.wiprotest.model.InstrumentPriceHistory;import org.junit.jupiter.api.Test;import java.io.IOException;import java.nio.file.Files;import java.nio.file.Path;import java.text.ParseException;import java.util.List;import static org.junit.jupiter.api.Assertions.*;class InstrumentPriceHistoryReaderTest {	@Test	void readHistoryLog() throws IOException {		// WHEN		List<InstrumentPriceHistory> historyLog = InstrumentPriceHistoryReader.readHistoryLog(			Files.lines(Path.of("src/test/resources/example_input.txt"))		).toList();		// THEN		assertFalse(historyLog.isEmpty());		assertTrue(historyLog.stream().anyMatch(item -> "INSTRUMENT1".equals(item.name())));	}	@Test	void toInstrumentPriceHistory() throws ParseException {		assertEquals(			new InstrumentPriceHistory("INSTRUMENT1", DateParser.parseDumpDate("26-May-2003"), 3.6699d),			InstrumentPriceHistoryReader.toInstrumentPriceHistory("INSTRUMENT1,26-May-2003,3.6699")		);	}}