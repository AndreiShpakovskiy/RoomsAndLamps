package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.IpDataItem;
import com.shpakovskiy.roomsandlamps.repository.IpDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpDataService implements IpDataServiceInterface {

    private final IpDataRepository ipDataRepository;

    public IpDataService(IpDataRepository ipDataRepository) {
        this.ipDataRepository = ipDataRepository;
    }

    @Override
    public void addItem(IpDataItem ipDataItem) {
        ipDataRepository.addItem(ipDataItem);
    }

    @Override
    public void addItems(List<IpDataItem> ipDataItems) {
        ipDataRepository.addItems(ipDataItems);
    }

    @Override
    public int getCountryIdByNumericIp(long numericIp) {
        return ipDataRepository.getCountryIdByNumericIp(numericIp);
    }
}
