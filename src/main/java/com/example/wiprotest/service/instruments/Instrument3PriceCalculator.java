package com.example.wiprotest.service.instruments;import com.example.wiprotest.model.CalculatedPrice;import com.example.wiprotest.model.InstrumentPriceHistory;import com.example.wiprotest.service.InstrumentPriceCalculator;import java.util.ArrayList;import java.util.Comparator;import java.util.List;import java.util.stream.Collectors;import static com.example.wiprotest.utils.ListUtils.checkNotEmpty;import static com.example.wiprotest.utils.ListUtils.reduceList;/** * SPEC: For INSTRUMENT3 – any other statistical calculation that we can compute "on-the-fly" as we read the file (it's up to you) * <p> * own idea: average from the average of last 10 days and the average of last 2 days */public class Instrument3PriceCalculator implements InstrumentPriceCalculator {	private static int KEEP_LAST_N_RECORDS = 10;	List<InstrumentPriceHistory> lastNRecords = new ArrayList<>();	static double getAverageOfLastNDays(List<InstrumentPriceHistory> history, int lastNDays) {		return history.stream()			.sorted(Comparator.comparing(InstrumentPriceHistory::date).reversed())			.limit(lastNDays)			.collect(Collectors.averagingDouble(InstrumentPriceHistory::value));	}	@Override	public void consume(InstrumentPriceHistory history) {		lastNRecords.add(history);		if (lastNRecords.size() > KEEP_LAST_N_RECORDS) {			lastNRecords = reduceList(				lastNRecords,				KEEP_LAST_N_RECORDS,				Comparator.comparing(InstrumentPriceHistory::date).reversed()			);		}	}	@Override	public CalculatedPrice calculate() {		return new CalculatedPrice(			"INSTRUMENT3",			(getAverageOfLastNDays(lastNRecords, KEEP_LAST_N_RECORDS) + getAverageOfLastNDays(lastNRecords, 2)) / 2		);	}}