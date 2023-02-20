package com.example.Users.Redis;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@Component
public class ResponceEntityDeserializer extends StdDeserializer<ResponseEntity<?>> {

	public ResponceEntityDeserializer() {
		this(null);
	}

	protected ResponceEntityDeserializer(Class<?> vc) {
		super(vc);
	// TODO Auto-generated constructor stub
	}
	@Override
	public ResponseEntity<?> deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {

		System.err.println("In Redis deserialize");
		JsonNode jsonNode = p.getCodec().readTree(p);
		JsonNode node = jsonNode.get("body");
		Object body = null;

		if (node != null && !node.isNull()) {
			body = ctxt.readValue(node.traverse(), Object.class);
		}

		return ResponseEntity.status(node.get("statusCode").asInt()).body(body);
	}

}
