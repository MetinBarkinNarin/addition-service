package com.mycompany.calculator.additionservice;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpGet;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpResponse;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import com.mycompany.calculator.additionservice.domain.CalculationResult;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.lang.reflect.Field;

import java.net.URL;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@Testcontainers
public class AdditionServiceIntegrationTest {

    private static final DockerImageName ADDITION_SERVICE = DockerImageName.
            parse("registry.hub.docker.com/addition-service:latest")
            .asCompatibleSubstituteFor("addition-service");
    private static final DockerImageName SUBTRACTION_SERVICE = DockerImageName
            .parse("registry.hub.docker.com/subtraction-service:latest")
            .asCompatibleSubstituteFor("subtraction-service");

    static {
        setRyukDisabledInEnv();
    }

    private GenericContainer<?> additionContainer;
    private GenericContainer<?> subtractionContainer;
    private Network network;

    @BeforeEach
    public void setup() {
        network = Network.newNetwork();
        additionContainer = new GenericContainer(ADDITION_SERVICE)
                .withExposedPorts(8070)
                .withNetwork(network)
                .withEnv("SERVER_PORT", "8070")
                .withEnv("SERVER_ADDRESS", "localhost");

        subtractionContainer = new GenericContainer<>(SUBTRACTION_SERVICE)
                .withExposedPorts(8071)
                .withExtraHost("subtraction-service","10.150.17.73")
                .withNetwork(network)
                .withEnv("SERVER_PORT", "8071")
                .withEnv("SERVER_ADDRESS", "localhost");

        additionContainer.start();
        subtractionContainer.start();
    }
    @AfterEach
    public void tearDown() {
        additionContainer.close();
        subtractionContainer.close();
        network.close();

    }
    @Test
    public void subtractionTest() throws Exception {


        Assert.assertEquals(200, 200);
        // Given


//         RequestSpecification requestSpecification;
//        RestAssured.baseURI="http://localhost:8070/api/addition/calculate?one=9&two=14";
//        requestSpecification = RestAssured.given();
//        requestSpecification.
//                when().get(baseURI).then().statusCode(HttpStatus.SC_OK);

//        // Then
//        CalculationResult resource = RetrieveUtil.retrieveResourceFromResponse(
//                response, CalculationResult.class);



        //given
//        try (ConfigurableApplicationContext ignored = startApplication()) {
//            // when
//            produceKeyValuesSynchronously(
//                    tacticalModelEventKafkaTemplate,
//                    TACTICAL_MODEL_EVENT_CONSUMER,
//                    TACTICAL_MODEL_EVENT_LIST_1.stream()
//            );
//
//            Map<Integer, DomainEventEnvelope> tacticalModelEventResult = drainTableOutput(consumerTacticalModelEvent);
//
//            // then
//            assertThat(tacticalModelEventResult.keySet())
//                    .isEqualTo(EXPECTED_TACTICAL_MODEL_EVENT_MAP_WITH_FALSE.keySet());
//            assertThat(tacticalModelEventResult).isEqualToComparingFieldByFieldRecursively(EXPECTED_TACTICAL_MODEL_EVENT_MAP_WITH_FALSE);
//        }
    }

    private static void setRyukDisabledInEnv() {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theCaseInsensitiveEnvironment = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironment.setAccessible(true);
            Map<String, String> caseEnv = (Map<String, String>) theCaseInsensitiveEnvironment.get(null);
            caseEnv.put("TESTCONTAINERS_RYUK_DISABLED", "true");
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}