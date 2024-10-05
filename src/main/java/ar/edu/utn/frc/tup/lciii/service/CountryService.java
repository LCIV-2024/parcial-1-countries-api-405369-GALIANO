package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
        //@Autowired
        //private final CountryRepository countryRepository;

        @Autowired
        private final RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        public List<Country> getCountryByName(String name){
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                Country country1 = response.stream().map(this::mapToCountry)
                        .filter(asd -> asd.getName().equals(name))
                        .findFirst()
                        .orElse(null);
                List<Country> list = new ArrayList<>();
                list.add(country1);
                return list;
        }

        public List<Country> getCountryByCode(String code){
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                Country country1 = response.stream().map(this::mapToCountry)
                        .filter(asd -> asd.getCode().equals(code))
                        .findFirst()
                        .orElse(null);
                List<Country> list = new ArrayList<>();
                list.add(country1);
                return list;
        }

          public List<Country> getCountriesByContinent(String continent) {
                  String url = "https://restcountries.com/v3.1/all";
                  List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                  return response.stream()
                          .map(this::mapToCountry)
                          .filter(country -> continent.equals(country.getRegion()))
                          .collect(Collectors.toList());
          }


        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .code((String) countryData.get("cca3"))
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .build();

        }

        public CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }
}