package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private ObjectMapper testMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCountriesTest() throws Exception {
        List<Country> lista = new ArrayList<>();
        Country country = new Country();
        country.setName("Argentina");
        Country country1 = new Country();
        country1.setName("Brazil");
        lista.add(country);
        lista.add(country1);

    }


}