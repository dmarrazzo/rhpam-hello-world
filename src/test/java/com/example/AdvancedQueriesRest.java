package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.definition.QueryFilterSpec;
import org.kie.server.api.model.definition.QueryParam;
import org.kie.server.api.model.instance.TaskInstance;
import org.kie.server.api.util.QueryFilterSpecBuilder;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.QueryServicesClient;

/**
 * AdvancedQueriesRest
 */
public class AdvancedQueriesRest {

	private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
	private static final String user = System.getProperty("username", "donato");
	private static final String password = System.getProperty("password", "donato");
	private static final String QUERY_ID = "tasksProcVars";
	// private static final String CONTAINER = "hello-world_2.0-SNAPSHOT";
	// private static String PROCESS_ID = "hello-world.HelloWorld";

	public static void main(String[] args) {
		AdvancedQueriesRest queriesRest = new AdvancedQueriesRest();

		queriesRest.getAllTasksWithVariable();
	}

	private void getAllTasksWithVariable() {
		try {
			KieServicesClient client = getClient();

			QueryServicesClient queryClient = client.getServicesClient(QueryServicesClient.class);

			QueryFilterSpec spec = new QueryFilterSpecBuilder().equalsTo("VARIABLEINSTANCEID", "name")
			                                                   .equalsTo("VALUE", "Sherlock")
			                                                   .get();

			// UserTasksWithCustomVariables
			List<List> result = queryClient.query(QUERY_ID, QueryServicesClient.QUERY_MAP_RAW, spec, 0, 20, List.class);

			result.forEach(obj -> {
				System.out.format("%s\n", obj);
			});

			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private KieServicesClient getClient() {
		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(URL, user, password);

		// Marshalling
		config.setMarshallingFormat(MarshallingFormat.JSON);
		Set<Class<?>> extraClasses = new HashSet<Class<?>>();
		// extraClasses.add(Date.class);
		// extraClasses.add(QueryFilterSpec.class);
		// extraClasses.add(QueryParam.class);
		// extraClasses.add(List.class);
		// extraClasses.add(Comparable.class);
		// extraClasses.add(Arrays.class);
		config.addExtraClasses(extraClasses);
		Map<String, String> headers = null;
		config.setHeaders(headers);
		KieServicesClient client = KieServicesFactory.newKieServicesClient(config);

		return client;
	}

}