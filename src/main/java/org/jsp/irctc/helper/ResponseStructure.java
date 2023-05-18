package org.jsp.irctc.helper;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	String message;
	int statusCode;
	T data;
}
