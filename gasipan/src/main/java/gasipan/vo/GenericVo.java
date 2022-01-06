package gasipan.vo;

import java.util.List;

import lombok.Data;

public class GenericVo<T> {
	private String message;
	private List<T> list;
	
	public GenericVo() {}

	public GenericVo(List<T> list) {
		this.list = list;
	}

	public GenericVo(String message, List<T> list) {
		super();
		this.message = message;
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
