package br.com.conectaplantao.api.usuario.infrastructure.web.controller;

import br.com.conectaplantao.api.usuario.application.dto.CriarMedicoDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Deve retornar status 201 ao criar médico com dados válidos")
    void criarMedico_ComDadosValidos_DeveRetornar201() {
        var medicoDTO = new CriarMedicoDTO(
                "Dra. Teste",
                "teste." + System.currentTimeMillis() + "@email.com",
                "senha1234",
                "998877",
                "Clínica Geral"
        );

        given()
                .contentType(ContentType.JSON)
                .body(medicoDTO)
                .when()
                .post("/api/usuarios/medicos")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("email", equalTo(medicoDTO.email()));
    }

    @Test
    @DisplayName("Deve retornar status 400 ao tentar criar médico com dados inválidos")
    void criarMedico_ComDadosInvalidos_DeveRetornar400() {
        var medicoComEmailInvalido = new CriarMedicoDTO(
                "Dra. Teste",
                "email-invalido",
                "123",
                "",
                "Clínica Geral"
        );

        given()
                .contentType(ContentType.JSON)
                .body(medicoComEmailInvalido)
                .when()
                .post("/api/usuarios/medicos")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
