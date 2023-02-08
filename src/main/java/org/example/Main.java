package org.example;
import org.example.database.DatabaseQueryService;
import org.example.model.MaxProjectCountClient;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
        List<MaxProjectCountClient> maxProjectCountClients = databaseQueryService.findMaxProjectsClient();
        System.out.println("maxProjectCountClients = " + maxProjectCountClients);

    }
 }