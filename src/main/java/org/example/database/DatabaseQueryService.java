package org.example.database;

import org.example.model.*;
import org.example.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() throws IOException {
        List<MaxProjectCountClient> clients = new ArrayList<>();
        String selectMaxProjectClientFileName = new Prefs().getPref(Prefs.SELECT_MAX_PROJECTS_CLIENT_FILE_PATH);
        String selectSql = Files.readString(Paths.get(selectMaxProjectClientFileName));

        Database database = Database.getInstance();
        try (Statement statement = database.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    MaxProjectCountClient client = new MaxProjectCountClient();
                    String name = resultSet.getString("name");
                    int project_count = resultSet.getInt("project_count");
                    client.setName(name);
                    client.setProject_count(project_count);
                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.close();
        return clients;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorkers() throws IOException {
        List<MaxSalaryWorker> workers = new ArrayList<>();
        String maxSalaryWorkerFileName = new Prefs().getPref(Prefs.SELECT_MAX_SALARY_WORKER_FILE_PATH);
        String selectSql = Files.readString(Paths.get(maxSalaryWorkerFileName));

        Database database = Database.getInstance();

        try (Statement statement = database.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    MaxSalaryWorker worker = new MaxSalaryWorker();
                    String name = resultSet.getString("name");
                    int salary = resultSet.getInt("salary");
                    worker.setName(name);
                    worker.setSalary(salary);
                    workers.add(worker);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.close();
        return workers;
    }


    public List<YoungestEldestWorkers> findYoungestEldestWorkers() throws IOException {
            List<YoungestEldestWorkers> workers = new ArrayList<>();
            String youngestEldestWorkersFileName = new Prefs().getPref(Prefs.SELECT_YOUNGEST_ELDEST_WORKERS_FILE_PATH);
            String selectSql = Files.readString(Paths.get(youngestEldestWorkersFileName));

        Database database = Database.getInstance();

        try(Statement statement = database.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    YoungestEldestWorkers worker = new YoungestEldestWorkers();
                    String type = resultSet.getString("type");
                    String name = resultSet.getString("name");
                    LocalDate birthday = LocalDate.parse(resultSet.getString("birthday"));
                    worker.setName(name);
                    worker.setType(type);
                    worker.setBirthday(birthday);
                    workers.add(worker);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        database.close();
        return workers;
    }

    public List<ProjectPrices> printProjectPrices() throws IOException {
        List<ProjectPrices> prices = new ArrayList<>();
        String projectPriceFileName = new Prefs().getPref(Prefs.SELECT_PROJECT_PRICES_FILE_PATH);
        String selectSql = Files.readString(Paths.get(projectPriceFileName));

        Database database = Database.getInstance();
        try(Statement statement = database.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    ProjectPrices projectPrices = new ProjectPrices();
                    int id = resultSet.getInt("id");
                    int price = resultSet.getInt("price");
                    projectPrices.setId(id);
                    projectPrices.setPrice(price);
                    prices.add(projectPrices);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        database.close();
        return prices;
    }

    public List<LongestProject> findLongestProject() throws IOException {
        List<LongestProject> projects = new ArrayList<>();
        String longestProjectFileName = new Prefs().getPref(Prefs.SELECT_LONGEST_PROJECT_FILE_PATH);
        String selectSql = Files.readString(Paths.get(longestProjectFileName));

        Database database = Database.getInstance();

        try (Statement statement = database.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    LongestProject project = new LongestProject();
                    int name = resultSet.getInt("name");
                    int month_count = resultSet.getInt("month_count");
                    project.setName(name);
                    project.setMonth_count(month_count);
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.close();
        return projects;
    }
}
