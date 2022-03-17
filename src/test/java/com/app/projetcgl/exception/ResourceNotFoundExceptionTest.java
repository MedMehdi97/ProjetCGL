package com.app.projetcgl.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceNotFoundExceptionTest {

    @Test
    void should_create_sondage_with_all_parameters(){
        ResourceNotFoundException exception = new ResourceNotFoundException("Sondage", "test", 1);
        assertThat(exception).isNotNull();
        assertThat(exception.getResourceName()).isEqualTo("Sondage");
        assertThat(exception.getFieldName()).isEqualTo("test");
        assertThat(exception.getFieldValue()).isEqualTo(1);
    }

}