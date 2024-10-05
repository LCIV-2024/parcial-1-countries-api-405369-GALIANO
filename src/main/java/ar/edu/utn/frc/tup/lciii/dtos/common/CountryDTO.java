package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {
    public CountryDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }
    private String code;
    private String name;
}
