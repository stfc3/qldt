package org.stfc.business;

import org.stfc.message.BaseResponse;

import com.google.gson.Gson;

public interface Business {
	public BaseResponse process(String request, Gson gson);

	public BaseResponse process(Gson gson);
}
