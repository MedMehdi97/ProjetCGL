package com.app.projetcgl.configuration;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.Document;
import com.app.projetcgl.model.Type;
import com.app.projetcgl.service.DocumentService;
import com.app.projetcgl.service.TypeService;
import com.app.projetcgl.service.imp.DocumentServiceImp;
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

    @Bean
    public CommandLineRunner seedingDocuments(DocumentService documentService, TypeService typeService){
        return args -> {
            log.info("Début du seeding de la table Document");
            documentService.saveDocument(new Document("Document 1","E:/doc1.txt",typeService.getTypeById(1)));
            documentService.saveDocument(new Document("Audio 1","E:/audio.mp3",typeService.getTypeById(2)));
            documentService.saveDocument(new Document("Vidéo 1","E:/vid1.mp4",typeService.getTypeById(3)));
            documentService.saveDocument(new Document("Binaire 1","E:/bin1.txt",typeService.getTypeById(4)));

            documentService.saveDocument(new Document("Document 2","E:/doc2.txt",typeService.getTypeById(1)));
            documentService.saveDocument(new Document("Audio 2","E:/audio.mp3",typeService.getTypeById(2)));
            documentService.saveDocument(new Document("Vidéo 2","E:/vid2.mp4",typeService.getTypeById(3)));
            documentService.saveDocument(new Document("Binaire 2","E:/bin2.txt",typeService.getTypeById(4)));

            documentService.saveDocument(new Document("Document 3","E:/doc3.txt",typeService.getTypeById(1)));
            documentService.saveDocument(new Document("Audio 3","E:/audio.mp3",typeService.getTypeById(2)));
            documentService.saveDocument(new Document("Vidéo 3","E:/vid3.mp4",typeService.getTypeById(3)));
            documentService.saveDocument(new Document("Binaire 3","E:/bin3.txt",typeService.getTypeById(4)));
            log.info("Début du seeding de la table Document");
        };
    }
}
