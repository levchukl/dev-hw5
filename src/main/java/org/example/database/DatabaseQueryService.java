package org.example.database;

import org.example.model.*;
import org.example.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
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
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(selectSql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    resultSet.getString("name");
                    resultSet.getInt("project_count");
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

        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(selectSql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    workers.add (new MaxSalaryWorker(
                            resultSet.getString("name"),
                    resultSet.getInt("salary")
                    ));
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

        try(PreparedStatement preparedStatement = database.getConnection().prepareStatement(selectSql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    workers.add(new YoungestEldestWorkers(
                            resultSet.getString("type"),
                    resultSet.getString("name"),
                    LocalDate.parse(resultSet.getString("birthday"))
                    ));
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
        try(PreparedStatement preparedStatement = database.getConnection().prepareStatement(selectSql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    prices.add(new ProjectPrices(
                            resultSet.getInt("id"),
                            resultSet.getInt("price")
                    ));
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

        try (PreparedStatement preparedStatement= database.getConnection().prepareStatement(selectSql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    projects.add(new LongestProject(
                            resultSet.getInt("name"),
                            resultSet.getInt("month_count")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.close();
        return projects;
    }
}
