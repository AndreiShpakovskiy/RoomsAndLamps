package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.IpDataItem;

import java.util.List;

public interface IpDataServiceInterface {
    void addItem(IpDataItem ipDataItem);
    void addItems(List<IpDataItem> ipDataItems);
    int getCountryIdByNumericIp(long numericIp);
}
