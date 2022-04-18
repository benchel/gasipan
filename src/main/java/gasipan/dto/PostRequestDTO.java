package gasipan.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * json
 * {
 * 	"name" : "",
 *	"age" : ,
 *	"car_list" : [
 *			{ "name" : "", "car_number" : "" },
 *			{ "name" : "", "car_number" : "" }
 *		]
 * }
 * 
 * JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
 * change json naming rule 
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequestDTO {
	private String name;
	private int age;

	private List<CarDTO> carList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<CarDTO> getCarList() {
		return carList;
	}

	public void setCarList(List<CarDTO> carList) {
		this.carList = carList;
	}
	
}
