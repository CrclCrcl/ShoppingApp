package notificationservice;

import lombok.extern.slf4j.Slf4j;
import notificationservice.Event.OrderEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "shopping-app")
	public void handleNotification(OrderEvent orderEvent){
	log.info("Recieved Notification for Order - {} ",orderEvent.getOrderNumber());
	}
}
