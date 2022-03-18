package com.app.projetcgl.configuration;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.model.Type;
import com.app.projetcgl.service.DocumentService;
import com.app.projetcgl.service.TypeService;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Configuration
public class Seeder {
    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Bean
    public CommandLineRunner seedingType(TypeService typeService){
        return args -> {

            ArrayList<String> listLibType=new ArrayList<>();
            listLibType.add("texte"); listLibType.add("audio");
            listLibType.add("vidéo"); listLibType.add("binaire");
            log.info("Debut du seeding de la table Type");
            for (String libType: listLibType) {
                try {
                    Type type=typeService.getTypeByLib(libType);
                }catch (ResourceNotFoundException e){
                    typeService.saveType(new Type(libType));
                }
            }
            log.info("Fin du seeding de la table Type");
        };
    }

    @Bean
    public CommandLineRunner seedingDocuments(DocumentService documentService, TypeService typeService){
        return args -> {
            log.info("Debut du seeding de la table Document");
            String[] dates = new String[]{"2022-03-15","2021-04-04","2021-09-01","2021-09-22"};
            String[] types = new String[]{"txt", "mp3", "mp4","bin"};
            for(int i=0; i < 50; i++) {
                String nom = RandomStringUtils.randomAlphabetic(new Random().nextInt(15)+4);
                int type = new Random().nextInt(types.length);
                String typeExt = types[type];
                LocalDate date = LocalDate.parse(dates[new Random().nextInt(dates.length)]);
                documentService.saveDocument(new Document(
                    nom,
                    "C:\\fakepath\\"+nom+"."+typeExt,
                    date, 
                    typeService.getTypeById(type + 1)
                ));
            }
            log.info("Fin du seeding de la table Document");
        };
    }
}
