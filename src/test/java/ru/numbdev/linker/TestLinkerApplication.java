package ru.numbdev.linker;

import org.springframework.boot.SpringApplication;

public class TestLinkerApplication {

	public static void main(String[] args) {
		SpringApplication.from(LinkerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
