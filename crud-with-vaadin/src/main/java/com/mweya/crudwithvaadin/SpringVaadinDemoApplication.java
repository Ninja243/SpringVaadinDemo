package com.mweya.crudwithvaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SpringVaadinDemoApplication {

	public static final Logger log = LoggerFactory.getLogger(SpringVaadinDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringVaadinDemoApplication.class);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			repository.save(new Customer("User", "Surname"));
			repository.save(new Customer("User2", "Surname"));
			repository.save(new Customer("User3", "Surname3"));
			repository.save(new Customer("User4", "Surname4"));
			repository.save(new Customer("User5", "Surname"));

			log.info("Customers found with findAll():");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			Customer customer = repository.findById(1L).get();
			log.info("Customer found with findOne(1L):");
			log.info(customer.toString());
			log.info("");

			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Surname'):");
			for (Customer surname : repository.findByLastNameStartsWithIgnoreCase("Surname")) {
				log.info(surname.toString());
			}
			log.info("");
		};
	}
}
