package com.cts.dao;

import com.cts.model.Data;
import com.cts.exception.DAOException;

import java.util.List;

public interface DataDAO {
    void addData(Data data) throws DAOException;
    Data getData(int dataId) throws DAOException;
    List<Data> getAllData() throws DAOException;
    void updateData(Data data) throws DAOException;
    void deleteData(int dataId) throws DAOException;
}
