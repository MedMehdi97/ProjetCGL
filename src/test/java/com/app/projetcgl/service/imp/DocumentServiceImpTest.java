package com.app.projetcgl.service.imp;

import com.app.projetcgl.exception.ResourceNotFoundException;
import com.app.projetcgl.model.*;
import com.app.projetcgl.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class DocumentServiceImpTest {

    @Mock
    DocumentRepository documentRepository;

    @InjectMocks
    DocumentServiceImp documentServiceImpl;

    Document baseDocument;
    Document documentToUpdate;
    Document documentUpdated;

    Document documentToDelete;

    ArrayList<Document> listOfDocument;

    List<StatDate> listStatDate;
    List<StatType> listStatType;
    List<StatDateType> listStatDateType;

    List<LocalDate> listDate;
    List<Type> listType;

    @BeforeEach
    public void setUpDocuments(){
        listOfDocument = new ArrayList<>();

        baseDocument = new Document("Test1", "Lien1", new Type("text"));
        baseDocument.setIdDocument(0);

        documentToDelete = new Document("Test2", "Lien2", new Type("audio"));
        documentToDelete.setIdDocument(1);

        documentToUpdate = new Document("Test3", "Lien3", new Type("video"));
        documentToUpdate.setIdDocument(2);

        documentUpdated = new Document("Test1", "Lien1", new Type("text"));
        documentUpdated.setIdDocument(2);

        listOfDocument.add(baseDocument);
        listOfDocument.add(documentToDelete);
        listOfDocument.add(documentToUpdate);
        listOfDocument.add(documentUpdated);


        openMocks(this);

        setUpStatsDate();
        setUpStatsType();
        setUpStatsDateType();

        when(documentRepository.save(any())).thenReturn(baseDocument);
        when(documentRepository.findAll()).thenReturn(listOfDocument);
        when(documentRepository.findById(0L)).thenReturn(Optional.of(baseDocument));
        when(documentRepository.findById(1L)).thenReturn(Optional.of(documentToDelete));
        when(documentRepository.findById(2L)).thenReturn(Optional.of(documentToUpdate));

    }

    void setUpStatsDate(){
        listStatDate = new ArrayList<>();
        listDate = new ArrayList<>();
        LocalDate date = LocalDate.now();


        listStatDate.add(new StatDate(date, 4));

        listDate.add(date);

        when(documentRepository.countDocumentsByDateArchivage(date)).thenReturn(4);
        when(documentRepository.findDistinctDateArchivage()).thenReturn(listDate);
    }



    void setUpStatsType(){
        listStatType = new ArrayList<>();

        listType = new ArrayList<>();

        Type text = new Type("text");
        Type audio = new Type("audio");
        Type video = new Type("video");

        listStatType.add(new StatType("text", 2));
        listStatType.add(new StatType("audio", 1));
        listStatType.add(new StatType("video", 1));

        listType.add(text);
        listType.add(audio);
        listType.add(video);

        when(documentRepository.countDocumentsByType(text)).thenReturn(2);
        when(documentRepository.countDocumentsByType(audio)).thenReturn(1);
        when(documentRepository.countDocumentsByType(video)).thenReturn(1);
        when(documentRepository.findDistinctType()).thenReturn(listType);
    }

    void setUpStatsDateType(){
        listStatDateType = new ArrayList<>();

        List<Object[]> listObj = new ArrayList<>();

        LocalDate date = LocalDate.now();
        Type text = new Type("text");
        Type audio = new Type("audio");
        Type video = new Type("video");

        Object[] dateAndType1 = {date, text};
        Object[] dateAndType2 = {date, audio};
        Object[] dateAndType3 = {date, video};

        listObj.add(dateAndType1);
        listObj.add(dateAndType2);
        listObj.add(dateAndType3);

        listStatDateType.add(new StatDateType(date, text.getLibType(), 2));
        listStatDateType.add(new StatDateType(date, audio.getLibType(), 1));
        listStatDateType.add(new StatDateType(date, video.getLibType(), 1));

        when(documentRepository.findDistinctDateAndType()).thenReturn(listObj);
        when(documentRepository.countDocumentsByDateArchivageAndType(date, text)).thenReturn(2);
        when(documentRepository.countDocumentsByDateArchivageAndType(date, audio)).thenReturn(1);
        when(documentRepository.countDocumentsByDateArchivageAndType(date, video)).thenReturn(1);

    }



    @Test
    void should_return_new_document(){
        Document newDocument = documentServiceImpl.saveDocument(baseDocument);
        assertThat(newDocument).isEqualTo(baseDocument);
    }

    /**
    @Test
    void should_delete_document(){
        long idToDelete = documentToDelete.getIdDocument();
        documentServiceImpl.deleteDocument(idToDelete);
        verify(documentRepository, times(1)).deleteById(idToDelete);
    }
     **/

    @Test
    void should_return_a_document_by_is_id() {
        Document newDocument = documentServiceImpl.getDocumentById(baseDocument.getIdDocument());

        assertThat(newDocument).isEqualTo(baseDocument);

        long fakeId = 5;
        ResourceNotFoundException exceptionName = assertThrows(ResourceNotFoundException.class, () -> {
            documentServiceImpl.getDocumentById(fakeId);
        });
        assertEquals("Document not found with IdDocument : '" + fakeId + "'", exceptionName.getMessage());
    }


    @Test
    void should_return_all_document(){
        List<Document> newListDocuments = documentServiceImpl.getAllDocument();
        assertThat(newListDocuments).isEqualTo(listOfDocument);
    }


    @Test
    void should_return_updated_document(){
        Document newDocument = documentServiceImpl.updateDocument(baseDocument, documentToUpdate.getIdDocument());
        assertThat(newDocument).isEqualTo(documentUpdated);
    }

    @Test
    void should_return_stats_by_date(){
        List<StatDate> newList = documentServiceImpl.statistiqueArchivageBydate();
        assertThat(newList).isEqualTo(listStatDate);
    }

    @Test
    void should_return_stats_by_type(){
        List<StatType> newList = documentServiceImpl.statistiqueArchivageByType();
        assertThat(newList).isEqualTo(listStatType);
    }

    @Test
    void should_return_stats_by_date_and_type(){
        List<StatDateType> newList = documentServiceImpl.statistiqueArchivageByDateAndType();
        assertThat(newList).isEqualTo(listStatDateType);
    }


}