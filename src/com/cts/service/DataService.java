package com.cts.service;

import com.cts.dao.DataDAO;
import com.cts.model.Data;
import com.cts.exception.ServiceException;

import java.util.List;

public class DataService {
    private DataDAO dataDAO;

    public DataService(DataDAO dataDAO) {
        this.dataDAO = dataDAO;
    }

    public void addData(Data data) throws ServiceException {
        try {
            dataDAO.addData(data);
        } catch (Exception e) {
            throw new ServiceException("Unable to add data", e);
        }
    }

    public Data getData(int dataId) throws ServiceException {
        try {
            return dataDAO.getData(dataId);
        } catch (Exception e) {
            throw new ServiceException("Unable to retrieve data", e);
        }
    }

    public List<Data> getAllData() throws ServiceException {
        try {
            return dataDAO.getAllData();
        } catch (Exception e) {
            throw new ServiceException("Unable to retrieve data", e);
        }
    }

    public void updateData(Data data) throws ServiceException {
        try {
            dataDAO.updateData(data);
        } catch (Exception e) {
            throw new ServiceException("Unable to update data", e);
        }
    }

    public void deleteData(int dataId) throws ServiceException {
        try {
            dataDAO.deleteData(dataId);
        } catch (Exception e) {
            throw new ServiceException("Unable to delete data", e);
        }
    }
}
