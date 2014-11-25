package com.plugtree.dslExample.guvnor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.client.Entity;

public final class GuvnorExample {

	public static void main(final String[] args) {
		addingNewPackage();
		addingNewAsset();

	}

	@SuppressWarnings("rawtypes")
	private static void addingNewPackage() {
		WebClient client = WebClient.create("http://localhost:8080");

		final Entity entity = Entity
				.xml("<?xml version=\"1.0\" encoding=\"UTF-8\"?><package><description>Hernsys Package Example Description</description><title>testPackage</title></package>");

		Response response = client.path("guvnor/rest/packages")
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Basic Z3Vlc3Q6Z3Nlc2QK=")
				.type(MediaType.APPLICATION_OCTET_STREAM_TYPE).post(entity);

		System.out.println("Status:" + response.getStatus());
		System.out.println("Headers:" + response.getHeaders());

	}

	private static void addingNewAsset() {
		WebClient client = WebClient.create("http://localhost:8080");

		byte[] fileBytes = null;
		try {
			fileBytes = Files
					.readAllBytes(Paths
							.get("src/main/resources/com/resources/guvnor/assets/guvnorRuleExample.drl"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fileSource = new String(fileBytes);

		System.out.println(fileSource);

		Response response = client
				.path("guvnor/rest/packages/testPackage/assets")
				.accept(MediaType.APPLICATION_ATOM_XML_TYPE)
				.header("Authorization", "Basic Z3Vlc3Q6Z3Nlc2QK=")
				.type(MediaType.APPLICATION_OCTET_STREAM_TYPE)
				.header("slug", "guvnorRuleExample.drl").post(fileSource);

		System.out.println("Status:" + response.getStatus());
		System.out.println("Headers:" + response.getHeaders());

	}

}
