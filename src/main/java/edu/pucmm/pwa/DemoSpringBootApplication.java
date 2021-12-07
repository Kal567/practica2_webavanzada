package edu.pucmm.pwa;

import edu.pucmm.pwa.servicios.seguridad.SeguridadServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

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
	}
}
