package com.sinosteel.framework.core.web;

import com.alibaba.fastjson.JSON;

public class Response 
{
	public ResponseType status;
	public JSON data;
	public String message;
	/* todo 新增属性 */
	public String type;
	public String currentAuthority;

	public Response()
	{
		this.status = null;
		this.data = null;
		this.message = null;
	}
	
	public Response(ResponseType status, JSON data, String message)
	{
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public Response(ResponseType status, JSON data, String message,String type,String currentAuthority)
	{
		this.status = status;
		this.data = data;
		this.message = message;
		this.type = type;
		this.currentAuthority = currentAuthority;
	}
}