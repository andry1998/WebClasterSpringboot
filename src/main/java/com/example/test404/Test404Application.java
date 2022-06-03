package com.example.test404;

import com.example.test404.config.SecurityConfig;
import com.example.test404.repository.PersonRepository;
import com.example.test404.repository.StudentRepository;
import com.example.test404.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test404Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Test404Application.class, args);
	}

	@Autowired
	private SecurityConfig securityConfig;
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {

		//personRepository.save(new Person("Oleg", "Mirzeabasov", "oam@mail.ru", securityConfig.passwordEncoder().encode("admin"), Role.ADMIN, Status.ACTIVE));
		//personRepository.save(new Person("Andrey", "Brynza", "brynza@mail.ru", securityConfig.passwordEncoder().encode("admin"), Role.ADMIN, Status.ACTIVE));
		//studentRepository.save(new Student("Liza", "Got", "lissssaGot@mail.ru", "a091098b", "IVT-B16"));
		//studentService.writerToFile(filename);
//		try (BufferedWriter writter = new BufferedWriter(new FileWriter(filename))) {
//			for (Student student : studentRepository.findAll()) {
//				writter.write(student + "\n");
//			}
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}
