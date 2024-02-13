package after_study.connect.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Student {
	private String name;
	private int age;
}
