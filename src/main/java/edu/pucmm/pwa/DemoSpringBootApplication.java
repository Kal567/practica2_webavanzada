package edu.pucmm.pwa;

import edu.pucmm.pwa.servicios.seguridad.SeguridadServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication() //scanBasePackages = {"edu.pucmm.otro", "edu.pucmm.pwa"}
public class DemoSpringBootApplication {



	public static void main(String[] args) {

		//Crear el template
		//SpringApplication.run(DemoSpringBootApplication.class, args);

		ApplicationContext applicationContext = SpringApplication.run(DemoSpringBootApplication.class, args);

		String[] lista = applicationContext.getBeanDefinitionNames();
		System.out.println("====== Beans Registrados =====");
		for(String bean : lista){
			System.out.println(""+bean);
		}
		System.out.println("====== FIN Beans Registrados =====");

		SeguridadServices seguridadServices = (SeguridadServices) applicationContext.getBean("seguridadServices");
		seguridadServices.crearUsuarioAdmin();

		String language = "en";
		String country = "US";

		if (args.length == 2) {
			language = args[0];
			country = args[1];
		}

		var locale = new Locale(language, country);
		var messages = ResourceBundle.getBundle("messages", locale);
	}
}
