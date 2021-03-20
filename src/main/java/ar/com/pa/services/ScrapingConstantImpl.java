package ar.com.pa.services;


import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map.Entry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;

import ar.com.pa.enums.regions.Regions;
import ar.com.pa.utils.SerializeImpl;
import io.vavr.control.Try;

@Service
public class ScrapingConstantImpl {

	private static final Logger logger = LoggerFactory.getLogger(ScrapingConstantImpl.class);
	
	@Autowired
	SerializeImpl serializeImpl;
	
	public ScrapingConstantImpl() {
	};

	public void getCountryConstant() {

		logger.info("Getting Countries Enum");

		ImmutableMap<String, String> regionsMap = ImmutableMap.of("1", "Americas", "2", "Europe", "3", "Asia-Pacific",
																				  "4", "Middle-East", "5", "Africa");

		Try<Document> doc = Try.of(() -> Jsoup.connect("https://www.investing.com/equities/").get());

		doc.onSuccess(data -> {
			
			regionsMap.entrySet().stream().forEach((region)-> saveCountriesByRegion(data,region));	
			
		});

		doc.onFailure(ex -> {
			logger.error(ex.toString());
		});

	}

	public void saveCountriesByRegion(Document data, Entry<String, String> region) {

		String idElement = String.format("#cdregion%s", region.getKey());

		Elements regionElements = data.select(idElement).first().select("a");

		Set<String> countriesAndCodes = regionElements.stream().map(this::getCountryEnumFormat).collect(Collectors.toSet());
		
		System.out.println("--------------------------------------" + region.getValue());
		serializeImpl.print(countriesAndCodes);
		
	}

	public String getCountryEnumFormat(Element element) {

		String urlCountry = element.attr("href");
		String titleCountry = element.text();
		
		if (Objects.nonNull(urlCountry) && urlCountry.contains("/equities/")) {
			
			String[] matcherUrl = Arrays.copyOfRange(urlCountry.split("/"), 2, 3);

			String enumFormat = String.format("private final String %s = \"%s\"; \n", titleCountry.replaceAll("\\s", ""), matcherUrl[0]);

			return enumFormat;
		}else {
			return "";
		}

	}
	
	public void saveMarketIndex(Regions region) {
		
		
		
			Try<Document> doc = Try.of(() -> Jsoup.connect("https://www.investing.com/equities/argentina").get());

		doc.onSuccess(data -> {
			
			Elements regionElements = data.select("#stocksFilter"); 
			
			
		});
		

	}
	
	
	
	
	public void getMarketIndex(Document data) {
		
		
	

		}
	public void getStockIndex(Document data) {
		
		
		

	}
	
	

	
	
	
}
