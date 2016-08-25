package br.com.segware;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalisadorRelatorio implements IAnalisadorRelatorio {

	private List<Relatorio> listaRelatorio;
	
	public AnalisadorRelatorio() {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get("src/test/java/br/com/segware/relatorio.csv"));
			listaRelatorio = br.lines().map(mapToRelatorio).collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Function<String, Relatorio> mapToRelatorio = (line) -> {
		String[] r = line.split(",");
		return new Relatorio(r[0], (r[1]), r[2], r[3], r[4], r[5], r[6]);
	};
	
	@Override
	public Map<String, Long> getTotalEventosCliente() {
		
		Map<String, Long> collect = listaRelatorio.stream().collect(
				Collectors.groupingBy(Relatorio::getCodCliente, Collectors.counting()));
		return collect;
	}
	
	@Override
	public Map<String, Double> getTempoMedioAtendimentoAtendente() {
		Map<String, Double> collect = listaRelatorio.stream().collect(
				Collectors.groupingBy(Relatorio::getCodAtendente, Collectors.averagingLong(Relatorio::getTempoMedioAtendimentoSegundos)));
		return collect;
	}

	@Override
	public List<Tipo> getTiposOrdenadosNumerosEventosDecrescente() {
		Map<Tipo, Long> collect = listaRelatorio.stream().collect(
				Collectors.groupingBy(Relatorio::getTipoEvento, Collectors.counting()));
				
		List<Tipo> list = collect.entrySet().stream()
				.sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<Integer> getCodigoSequencialEventosDesarmeAposAlarme() {
		
		List<Relatorio> list = listaRelatorio.stream().collect(Collectors.toList());
		Map<String, LocalTime> client = new HashMap<String, LocalTime>();
		List<Integer> result = new ArrayList<Integer>();
		
		for (Relatorio item : list) {
			if (!client.containsKey(item.getCodCliente()) && item.getTipoEvento() == Tipo.ALARME) {
				client.put(item.getCodCliente(), item.getDataInicio());
			} else if (client.containsKey(item.getCodCliente()) 
					&& item.getTipoEvento() == Tipo.DESARME 
					&& Duration.between(client.get(item.getCodCliente()), item.getDataInicio()).getSeconds() <= 150) {
				
				result.add(item.getCodSequencial());
			}
		}
		return result;
	}

}
