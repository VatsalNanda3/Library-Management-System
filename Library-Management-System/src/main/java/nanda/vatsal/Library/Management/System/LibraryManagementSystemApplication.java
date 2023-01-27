package nanda.vatsal.Library.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages= {"nanda.vatsal.test","nanda.vatsal.Library.Management.System","nanda.vatsal.books","nanda.vatsal.user","nanda.vatsal.configuration","nanda.vatsal.lib"})
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}
