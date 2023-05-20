package br.com.mercadolivre.notificationsystem.message;

import br.com.mercadolivre.notificationsystem.exception.BusinessException;
import br.com.mercadolivre.notificationsystem.model.Advertisement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class MessageProducer {
  @Autowired
  private KafkaTemplate<String, Advertisement> notificationKafkaTemplate;

  @Value(value = "${kafka.topic.notification}")
  private String topicName;

  public void sendMessage(Advertisement advertisement) throws BusinessException {
    if (advertisement == null || !advertisement.isValid()) {
      throw new BusinessException("All the notification fields must be filled");
    }
    var key = advertisement.getCode() + ":" + advertisement.getUserId();
    var future = notificationKafkaTemplate.send(topicName, key, advertisement);
    future.addCallback(new ListenableFutureCallback<>() {
      @Override
      public void onSuccess(SendResult<String, Advertisement> result) {
        log.info("Publicando a notificacao=[" + advertisement.getCode() +
            "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to send message=["
            + advertisement.getCode() + "] due to : " + ex.getMessage());
      }
    });
  }
}
