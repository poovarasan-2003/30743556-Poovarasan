package com.cts.main;

import com.cts.model.Data;
import com.cts.service.DataService;
import com.cts.exception.ServiceException;
import com.cts.dao.DataDAOImpl;
import com.cts.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class DataManagement {
    private final DataService dataService;

    public DataManagement(DatabaseConnectionUtil dbUtil) {
        Connection connection = dbUtil.getConnection();
        this.dataService = new DataService(new DataDAOImpl(connection));
    }

    public void manageData(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Data");
            System.out.println("2. View Data");
            System.out.println("3. View All Data");
            System.out.println("4. Update Data");
            System.out.println("5. Delete Data");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            int participantId;
            java.sql.Date dataDate = null;
            String dataValue = null;
            String dataType = null;
            int dataId;

            switch (choice) {
                case 1:
                    System.out.print("Enter participant ID: ");
                    participantId = scanner.nextInt();
                    System.out.print("Enter data date (yyyy-mm-dd): ");
                    dataDate = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter data value: ");
                    dataValue = scanner.next();
                    System.out.print("Enter data type: ");
                    dataType = scanner.next();

                    Data data = new Data(0, participantId, dataDate, dataValue, dataType);

                    try {
                        dataService.addData(data);
                        System.out.println("Data added successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error adding data: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter data ID: ");
                    dataId = scanner.nextInt();

                    try {
                        Data retrievedData = dataService.getData(dataId);
                        if (retrievedData != null) {
                            System.out.println(retrievedData);
                        } else {
                            System.out.println("Data not found.");
                        }
                    } catch (ServiceException e) {
                        System.err.println("Error retrieving data: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<Data> dataList = dataService.getAllData();
                        for (Data d : dataList) {
                            System.out.println(d);
                        }
                    } catch (ServiceException e) {
                        System.err.println("Error retrieving data: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter data ID: ");
                    dataId = scanner.nextInt();
                    System.out.print("Enter new participant ID: ");
                    participantId = scanner.nextInt();
                    System.out.print("Enter new data date (yyyy-mm-dd): ");
                    dataDate = java.sql.Date.valueOf(scanner.next());
                    System.out.print("Enter new data value: ");
                    dataValue = scanner.next();
                    System.out.print("Enter new data type: ");
                    dataType = scanner.next();

                    Data newData = new Data(dataId, participantId, dataDate, dataValue, dataType);

                    try {
                        dataService.updateData(newData);
                        System.out.println("Data updated successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error updating data: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter data ID to delete: ");
                    dataId = scanner.nextInt();

                    try {
                        dataService.deleteData(dataId);
                        System.out.println("Data deleted successfully.");
                    } catch (ServiceException e) {
                        System.err.println("Error deleting data: " + e.getMessage());
                    }
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
