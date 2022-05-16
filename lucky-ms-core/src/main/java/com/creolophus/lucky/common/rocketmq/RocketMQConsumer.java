package com.creolophus.lucky.common.rocketmq;

import com.liuyi.common.config.RocketMQSetting;
import com.liuyi.common.exception.DoNotReConsumeException;
import com.liuyi.common.logger.Entry;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author magicnana
 * @date 2019/7/23 下午5:54
 */
public abstract class RocketMQConsumer implements Shutdown {

  private static final Logger logger = LoggerFactory.getLogger(RocketMQProducer.class);
  private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
  @Resource
  private RocketMQSetting rocketMQSetting;

  protected abstract String getConsumerGroup();

  protected abstract String getConsumerTopic();

  @Entry
  protected abstract void process(String msgId, String topic, String msgBody, int times);


  @PostConstruct
  public void init() {
    Thread thread = new Thread(new ConsumerTask(), "rocketmq_consumer_" + getConsumerTopic());
    thread.start();
    logger.info("start RocketMQ Consumer " + thread.getName());

  }

  @Override
  public void shutdown() {
    consumer.shutdown();
  }

  private class ConsumerTask implements Runnable {

    @Override
    public void run() {
      consumer.setNamesrvAddr(rocketMQSetting.getNamesrvAddr());
      consumer.setConsumerGroup(getConsumerGroup());
      try {
        //订阅
        consumer.subscribe(getConsumerTopic(), "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {

          @Override
          public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
              ConsumeConcurrentlyContext context) {
            for (MessageExt msg : msgs) {
              String msgBody = new String(msg.getBody());
              try {
                process(msg.getMsgId(), msg.getTopic(), msgBody, msg.getReconsumeTimes());
              } catch (DoNotReConsumeException e) {
                logger.error("RocketMQ Consumer Error,Do Not Reconsume", e);
              } catch (Throwable e) {
                logger.error("RocketMQ Consumer Error,Reconsume Later", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
              }
            }
            logger.info("RocketMQ Consumer Success");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
          }
        });
        consumer.start();
      } catch (Throwable e) {
        throw new RuntimeException(e);
      }
    }
  }
}
