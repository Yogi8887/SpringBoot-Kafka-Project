package com.yogi;

import com.yogi.entity.WikimediaData;
import com.yogi.repository.WikimediaDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataBaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDataBaseConsumer.class);

    private WikimediaDataRepo wikimediaDataRepo;

    public KafkaDataBaseConsumer(WikimediaDataRepo wikimediaDataRepo) {
        this.wikimediaDataRepo = wikimediaDataRepo;
    }
    @KafkaListener(
            topics = "wikimedia_recentChange",
            groupId = "myGroup"
    )
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event message received -> %s",eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaDataRepo.save(wikimediaData);
    }
}
