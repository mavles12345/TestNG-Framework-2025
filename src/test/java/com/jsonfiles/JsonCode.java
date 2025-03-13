package com.jsonfiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCode {

	public List<HashMap<String, String>> getJsonCode() throws IOException {

		String jsonfilePath = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\jsonfiles\\purchaseorderdata.json"),
				StandardCharsets.UTF_8);
		// alternate way
//		String jsonfilePath = FileUtils.readFileToString(new File(
//				"F:\\Eclipse-workspace-RestAssured-April2024\\TestNG-Framework-2025\\src\\test\\java\\com\\jsonfiles\\purchaseorderdata.json"),
//				StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonfilePath,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

//				List<HashMap<String, String>> data = mapper.readValue(jsonfilePath,
//						new TypeReference<List<HashMap<String, String>>>() {
//
//						});

	}
}