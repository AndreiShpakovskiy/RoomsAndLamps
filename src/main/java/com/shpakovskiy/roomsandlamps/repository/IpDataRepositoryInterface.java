package com.shpakovskiy.roomsandlamps.repository;

import com.shpakovskiy.roomsandlamps.entity.IpDataItem;

import java.util.List;

public interface IpDataRepositoryInterface {
    void addItem(IpDataItem ipDataItem);
    void addItems(List<IpDataItem> ipDataItems);
    int getCountryIdByNumericIp(long numericIp);
}
