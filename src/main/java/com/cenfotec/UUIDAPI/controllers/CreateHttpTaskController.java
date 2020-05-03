package com.cenfotec.UUIDAPI.controllers;


import com.google.cloud.tasks.v2.CloudTasksClient;
import com.google.cloud.tasks.v2.HttpMethod;
import com.google.cloud.tasks.v2.HttpRequest;
import com.google.cloud.tasks.v2.QueueName;
import com.google.cloud.tasks.v2.Task;
import com.google.cloud.tasks.v2.Task.Builder;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Parser;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateHttpTaskController {
	private String projectId = "elcolectivo";
	private String locationId = "us-east1";
	private String domain = "https://colectivojam-zpwlrwwbpq-ue.a.run.app/";
	
	@GetMapping("/task/create/{path}")
	public ResponseEntity<String> createGETTask(@PathVariable String path) throws Exception {
		String queueId = "my-get-queue";
		// Instantiates a client.
		try (CloudTasksClient client = CloudTasksClient.create()) {
			String url = domain+path;

			// Construct the fully qualified queue name.
			String queuePath = QueueName.of(projectId, locationId, queueId).toString();

			// Construct the task body.
			Task.Builder taskBuilder = Task.newBuilder().setHttpRequest(
					HttpRequest.newBuilder()
						.setUrl(url)
						.setHttpMethod(HttpMethod.GET)
						.build());

			// Send create task request.
			Task task = client.createTask(queuePath, taskBuilder.build());

			return ResponseEntity.ok(task.getName());
		}
	}
	
	@PostMapping("/task/create/{path}")
	public ResponseEntity<String> createPOSTTask(@RequestBody Bands newBand) throws Exception {
		String queueId = "my-post-queue";

		// Instantiates a client.
		try (CloudTasksClient client = CloudTasksClient.create()) {
			String url = domain+"bands";
			String payload = "payload";

			// Construct the fully qualified queue name.
			String queuePath = QueueName.of(projectId, locationId, queueId).toString();

			//Construct the task body.
			Task.Builder taskBuilder = Task.newBuilder().setHttpRequest(
					HttpRequest.newBuilder().setBody(ByteString.copyFrom(newBand.toString(), Charset.defaultCharset())).setUrl(url)
							.setHttpMethod(HttpMethod.POST).build());

			// Send create task request.
			Task task = client.createTask(queuePath, taskBuilder.build());
			System.out.println("Task created: " + task);
			return ResponseEntity.ok(task.getName());
		}
	}
}
