package com.app.projetcgl.configuration;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Type;
import com.app.projetcgl.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Seeder {
    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Bean
    public CommandLineRunner seedingType(TypeService typeService){
        return args -> {
            List<Type> listType=typeService.getAllType();

            ArrayList<String> listLibType=new ArrayList<>();
            listLibType.add("texte"); listLibType.add("audio");
            listLibType.add("vidéo"); listLibType.add("binaire");
            log.info("Début du seeding de la table Type");
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
}
