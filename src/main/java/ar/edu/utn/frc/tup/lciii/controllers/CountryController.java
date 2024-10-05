package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private final CountryService countryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getCountries(@RequestParam(required = false, value = "name") String nombre,
                                                         @RequestParam(required = false, value = "code") String codigo){
        List<Country> countryList = new ArrayList<>();
        if (nombre == null && codigo == null){
            countryList = countryService.getAllCountries();
        } else if (nombre != null) {
            countryList = countryService.getCountryByName(nombre);
        } else if (codigo !=null){
            countryList = countryService.getCountryByCode(codigo);
        }

       List<CountryDTO> countryDTOS = new ArrayList<>();
       for (Country country: countryList){
            countryDTOS.add(countryService.mapToDTO(country));
       }
       return ResponseEntity.ok(countryDTOS);
    }

    @GetMapping("/countries/{continent}/continent")
    public ResponseEntity<List<CountryDTO>> getCountriesByContinent(@PathVariable String continent){
        List<Country> countryList = countryService.getCountriesByContinent(continent);
        List<CountryDTO> countryDTOS = new ArrayList<>();
        for (Country country: countryList){
            countryDTOS.add(countryService.mapToDTO(country));
        }
        return ResponseEntity.ok(countryDTOS);
    }

    @GetMapping("/countries/{languaje}/languaje")
    public ResponseEntity<List<CountryDTO>> getCountriesByLanguajes(@PathVariable String languaje){
        List<Country> countryList = countryService.getCountriesByLanguaje(languaje);
        List<CountryDTO> countryDTOS = new ArrayList<>();
        for (Country country: countryList){
            countryDTOS.add(countryService.mapToDTO(country));
        }
        return ResponseEntity.ok(countryDTOS);
    }
}