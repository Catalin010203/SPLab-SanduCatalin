
package ro.uvt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ro.uvt.difexamples.ClientComponent;
import ro.uvt.difexamples.SingletonComponent;
import ro.uvt.difexamples.TransientComponent;

@SpringBootApplication
public class MySpringApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(MySpringApplication.class, args);

        System.out.println("\n--- TRANSIENT COMPONENT ---");

        TransientComponent tc1 = context.getBean(TransientComponent.class);
        TransientComponent tc2 = context.getBean(TransientComponent.class);

        tc1.operation();
        tc2.operation();

        System.out.println("\n--- SINGLETON COMPONENT ---");

        SingletonComponent sc1 = context.getBean(SingletonComponent.class);
        SingletonComponent sc2 = context.getBean(SingletonComponent.class);

        sc1.operation();
        sc2.operation();

        System.out.println("\n--- CLIENT COMPONENT ---");

        ClientComponent client = context.getBean(ClientComponent.class);
        ClientComponent client2 = context.getBean(ClientComponent.class);

        System.out.println("Client 1: " + client);
        System.out.println("Client 2: " + client2);

        client.operation();
        client2.operation();
        client.operation();
    }
}
