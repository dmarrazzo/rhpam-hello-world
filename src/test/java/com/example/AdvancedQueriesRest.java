package com.example;

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
	private static final String QUERY_ID = "c0cdbfd5-46d7-4cca-a931-036ce38bdbb6";
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

			QueryFilterSpec spec = new QueryFilterSpecBuilder().equalsTo("STATUS", "Created")
			                                                   .addColumnMapping("ACCOUNT", "string")
			                                                   .addColumnMapping("SOURCE", "string")
			                                                   .addColumnMapping("AMOUNT", "double")
			                                                   .get();

			// UserTasksWithCustomVariables
			List<TaskInstance> tiList = queryClient.query(QUERY_ID, QueryServicesClient.QUERY_MAP_TASK_WITH_CUSTOM_VARS,
			        spec, 0, 10, TaskInstance.class);

			tiList.forEach(ti -> {
				System.out.format("ti %s input data: %s\n", ti, ti.getInputData());
			});

			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private KieServicesClient getClient() {
		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(URL, user, password);

		// Marshalling
		config.setMarshallingFormat(MarshallingFormat.XSTREAM);
		Set<Class<?>> extraClasses = new HashSet<Class<?>>();
		extraClasses.add(Date.class);
		extraClasses.add(QueryFilterSpec.class);
		extraClasses.add(QueryParam.class);
		extraClasses.add(List.class);
		extraClasses.add(Map.class);
		config.addExtraClasses(extraClasses);
		Map<String, String> headers = null;
		config.setHeaders(headers);
		KieServicesClient client = KieServicesFactory.newKieServicesClient(config);

		return client;
	}

}