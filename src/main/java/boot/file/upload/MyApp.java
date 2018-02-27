package boot.file.upload;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "boot.file.upload")
public class MyApp {
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

	/**
	 * If you deployed to Tomcat, configure the maxSwallowSize to avoid this Tomcat connection reset issue. 
	 * For embedded Tomcat, declares a TomcatEmbeddedServletContainerFactory like the following
	 */
	@Bean
	public TomcatEmbeddedServletContainerFactory embeddedTomcat() {
		TomcatEmbeddedServletContainerFactory tomcatContainerFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatContainerFactory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>) {
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});

		return tomcatContainerFactory;
	}
}
