package Models;

public class SendEmailRequest {
	private String from;
	private String password;
	private String to;
	private String sub;
	private String msg;
	
	public SendEmailRequest(String from, String password, String to, String sub, String msg) {
		super();
		this.from = from;
		this.password = password;
		this.to = to;
		this.sub = sub;
		this.msg = msg;
	}

	public SendEmailRequest() {
		super();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
