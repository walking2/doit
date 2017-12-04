package SpringBoot.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import SpringBoot.hello.pojo.Student;

@RestController
@SpringBootApplication
public class HelloSpringBoot {

	@Value("${test.a}")
	private String test1a;
	
	@Value("${test2.a}")
	private String test2a;
	
	@Autowired
	private Student stu;
	@RequestMapping(value="/",produces="text/plain;charset=UTF-8")
	public ModelAndView hello(){
		System.out.println(test1a);
		System.out.println(test2a);
		System.out.println(stu.getAge());
		System.out.println(stu.getName());
		ModelAndView model=new ModelAndView();
		model.setViewName("index");
		return model;
	}
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBoot.class, args);
	}
}
