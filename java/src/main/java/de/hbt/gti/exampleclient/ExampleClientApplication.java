package de.hbt.gti.exampleclient;

import de.hbt.gti.example.invoker.ApiException;
import de.hbt.gti.example.model.CNResponse;
import de.hbt.gti.example.model.InitResponse;
import de.hbt.gti.example.model.RegionalSDName;
import de.hbt.gti.example.model.SDName;
import de.hbt.gti.example.model.SDName.TypeEnum;

public class ExampleClientApplication {

	private final InitService initService = new InitService();
	private final CheckNameService cnService = new CheckNameService();
	private final GetRouteService grService = new GetRouteService();

	public static void main(String[] args) throws ApiException {
		ExampleClientApplication app = new ExampleClientApplication();
		app.run();
	}

	private SDName resolveStation(String name) throws ApiException {
		CNResponse cnResponse = cnService.checkname(name);
		System.out.println(cnResponse);
		RegionalSDName regionalSDName = cnResponse.getResults().stream()
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Could resolve station id of " + name));
		SDName sdName = new SDName();
		sdName.setType(TypeEnum.STATION);
		sdName.setId(regionalSDName.getId());
		return sdName;
	}

	public void run() throws ApiException {
		InitResponse initResponse = initService.init();
		System.out.println(initResponse);

		SDName schlump = resolveStation("Schlump");
		SDName berlinerTor = resolveStation("Berliner Tor");

		grService.getRoute(schlump, berlinerTor);
	}

}
