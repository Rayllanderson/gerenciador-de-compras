package com.rayllanderson.gerenciadordecompras.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserImageIT extends BaseApiTest{

    @Autowired
    private MockMvc mvc;

    private final String BASE_URL = "/api/v1/users/image";

    @Test
    public void upload_FileUploaded_WhenSuccessful() throws Exception {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("kaguya-sama.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "kaguya-sama.jpg", "image/jpg", inputStream);

        //confirmando que o content type é válido antes de enviar pra api
        Assertions.assertThat(multipartFile.getContentType()).isEqualTo("image/jpg");

        this.mvc.perform(multipart(BASE_URL).file(multipartFile).headers(getHeaders())).andExpect(result ->
            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(MockHttpServletResponse.SC_OK)
        );
    }

    @Test
    public void upload_ChangePhoto_WhenSuccessful() throws Exception {
        //Upload da primeira foto de perfil
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("kaguya-sama.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "kaguya-sama.jpg", "image/jpg", inputStream);
        this.mvc.perform(multipart(BASE_URL).file(multipartFile).headers(getHeaders())).andExpect(result ->
                Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(MockHttpServletResponse.SC_OK)
        );
        //fim upload da primeira foto de perfil

        //realizando troca de foto, pela 2 vez.
        this.mvc.perform(multipart(BASE_URL).file(multipartFile).headers(getHeaders())).andExpect(result ->
                Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(MockHttpServletResponse.SC_OK)
        );
    }

    @Test
    public void upload_ReturnsBadRequest400_WhenFileIsNotAnImage() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Kaguya Sama".getBytes());

        //confirmando que o content type NÃO é válido antes de enviar pra api
        Assertions.assertThat(multipartFile.getContentType()).isEqualTo("text/plain");

        this.mvc.perform(multipart(BASE_URL).file(multipartFile).headers(getHeaders())).andExpect(result ->
                Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(MockHttpServletResponse.SC_BAD_REQUEST)
        );
    }

    @Test
    public void delete_DeleteImage_WhenSuccessful() throws Exception {
        //COMEÇO UPLOAD foto
        upload_FileUploaded_WhenSuccessful();
        //FIM UPLOAD foto

        ResponseEntity<Void> response = super.delete(BASE_URL, Void.class);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void delete_Returns404_WhenUserDoesNotHasPhoto(){
        ResponseEntity<Void> response = super.delete(BASE_URL, Void.class);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void findBase64_ReturnsBase64_WhenUserHasPhoto() throws Exception {
        //COMEÇO UPLOAD foto
        upload_FileUploaded_WhenSuccessful();
        //FIM UPLOAD foto

        ResponseEntity<String> response = super.get(BASE_URL, String.class);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().length()).isGreaterThan(100);
        Assertions.assertThat(response.getBody()).contains("base64");
    }

    @Test
    public void findBase64_ReturnsNull_whenUserHasNoPhoto(){
        ResponseEntity<String> response = super.get(BASE_URL, String.class);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNull();
    }

    @Test
    public void findMiniature_ReturnsMiniatureBase64_WhenUserHasPhoto() throws Exception {
        //COMEÇO UPLOAD foto
        upload_FileUploaded_WhenSuccessful();
        //FIM UPLOAD foto

        ResponseEntity<String> response = super.get(BASE_URL + "/miniature", String.class);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().length()).isGreaterThan(100);
        Assertions.assertThat(response.getBody()).contains("base64");
    }

    @Test
    public void findMiniature_ReturnsNull_WhenUserHasPhoto() {
        ResponseEntity<String> response = super.get(BASE_URL + "/miniature", String.class);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNull();
    }
}
