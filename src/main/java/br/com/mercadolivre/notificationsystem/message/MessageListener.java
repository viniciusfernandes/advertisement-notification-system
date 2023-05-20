package br.com.mercadolivre.notificationsystem.message;

import br.com.mercadolivre.notificationsystem.model.Advertisement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @KafkaListener(topics = "${kafka.topic.notification}",
      groupId = "${kafka.topic.groupId}", containerFactory = "advertisementKafkaListener")
  public void advertisement(Advertisement advertisement) {
    log.info("Received Message in group record: " + advertisement);
    messagingTemplate.convertAndSend("/topic/advertisement-notifications", advertisement);

  }

}
