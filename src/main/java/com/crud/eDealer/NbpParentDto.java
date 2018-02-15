package com.crud.eDealer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NbpParentDto {

    private String table;

    private List<NbpDto> rates;
}
