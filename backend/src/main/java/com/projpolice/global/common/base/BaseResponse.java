package com.projpolice.global.common.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a base response object.
 *
 * <p>It is used to wrap the response data, along with a response code and message.
 * The response code defines the status of the response, while the message provides additional
 * details or context. The response body holds the actual data being sent back.
 *
 * @param <T> the type of the response body
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
	private int code;
	private String message;
	private T body;

	public BaseResponse(T body) {
		this.body = body;
	}
}
