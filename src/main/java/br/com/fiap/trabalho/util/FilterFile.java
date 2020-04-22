package br.com.fiap.trabalho.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilterFile {

	public static List<String> filterFromResource(String fileName) {
		List<String> saida = new ArrayList<String>();

		try {

			InputStream in = new FilterFile().getClass().getResourceAsStream("/" + fileName);

			InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);

			Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");

			saida.addAll(br.lines()	.filter((line) -> !"".equals(line))
									.filter((line) -> pattern.matcher(line.substring(0, 1)).matches())
									.collect(Collectors.toList()));

			return saida;
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
		return saida;
	}
}
