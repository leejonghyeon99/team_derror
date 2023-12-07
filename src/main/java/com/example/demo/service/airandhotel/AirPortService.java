package com.example.demo.service.airandhotel;

import com.example.demo.domain.airandhotel.Items;

import java.util.List;

public interface AirPortService {

    List<Items> airportInfo(String code);

}
