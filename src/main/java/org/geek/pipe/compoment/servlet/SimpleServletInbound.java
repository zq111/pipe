/**
 * 
 */
package org.geek.pipe.compoment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.geek.pipe.api.DeliverException;
import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.InBound;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;
import org.geek.pipe.helper.EventRequestHelper;

/**
 * @author haichuan
 * @Create 2012-3-27
 */
public class SimpleServletInbound extends HttpServlet implements InBound{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234163024445338185L;

	private Processor processor;
	private String contextType = "text/html";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EventRequest pipRequest = EventRequestHelper.createRequest();
		pipRequest.setSource(req);
		try {
			deliverMessage(pipRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType(contextType);
		resp.getWriter().write(pipRequest.getResponse().getResponseCode().getDetail());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	public void deliverMessage(EventRequest request) throws DeliverException {
		try {
			getProcessor().process(request);
		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DeliverException(e);
		}
	}
	
	@Override
	public Processor getProcessor() {
		return this.processor;
	}
	
	@Override
	public void setProcessor(Processor out) {
		this.processor = out;
	}
	
	public void setType(String type) {
		this.contextType = type;
	}
	
	public String getType() {
		return contextType;
	}
}
