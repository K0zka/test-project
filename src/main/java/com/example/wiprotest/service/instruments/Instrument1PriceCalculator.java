package com.example.wiprotest.service.instruments;import com.example.wiprotest.model.CalculatedPrice;import com.example.wiprotest.model.InstrumentPriceHistory;import com.example.wiprotest.service.InstrumentPriceCalculator;/** * SPEC: mean */public class Instrument1PriceCalculator implements InstrumentPriceCalculator {	private Double sum = 0d;	private long counter = 0;	@Override	public void consume(InstrumentPriceHistory history) {		sum += history.value();		counter++;	}	@Override	public CalculatedPrice calculate() {		return new CalculatedPrice("INSTRUMENT1", sum / counter);	}}