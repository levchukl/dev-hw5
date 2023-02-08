package org.example.database;

import org.example.model.WorkerLevel;
import org.example.prefs.Prefs;
import org.h2.command.dml.Insert;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;


public class DatabasePopulateService {
    private final Database database;
    private final PreparedStatement insertWorker;
    private final PreparedStatement insertClient;
    private final PreparedStatement insertProject;
    private final PreparedStatement insertProjectWorker;

    public DatabasePopulateService(Database database) throws SQLException {
        this.database = database;
        Connection connection = database.getConnection();
        this.insertWorker = connection
                .prepareStatement("INSERT INTO worker (name, birthday, level, salary) VALUES(?, ?, ?, ?)");
        this.insertClient = connection
                .prepareStatement("INSERT INTO client (name) VALUES(?)");
        this.insertProject = connection
                .prepareStatement("INSERT INTO project (client_id, start_date, finish_date) VALUES(?, ?, ?)");
        this.insertProjectWorker = connection
                .prepareStatement("INSERT INTO project_worker (project_id, worker_id) VALUES(?, ?)");
    }


    public void createWorker(String name, LocalDate birthday, WorkerLevel level, int salary) {
        try (insertWorker) {
            insertWorker.setString(1, name);
            insertWorker.setString(2, birthday.toString());
            insertWorker.setString(3, level.name());
            insertWorker.setInt(4, salary);
            insertWorker.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createClient(String name) {
        try (insertClient) {
            insertClient.setString(1, name);
            insertClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProject(int client_id, LocalDate start_date, LocalDate finish_date) {
        try (insertProject) {
            insertProject.setInt(1, client_id);
            insertProject.setString(2, start_date.toString());
            insertProject.setString(3, finish_date.toString());
            insertProject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProjectWorker(int project_id, int worker_id) {
        try (insertProjectWorker) {
            insertProjectWorker.setInt(1, project_id);
            insertProjectWorker.setInt(2, worker_id);
            insertProjectWorker.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        DatabasePopulateService databasePopulateService = new DatabasePopulateService(Database.getInstance());
        databasePopulateService.createWorker("Maks Roy", LocalDate.parse("1990-02-25"), WorkerLevel.Senior, 7500);
        databasePopulateService.createWorker("Nik Korn", LocalDate.parse("2002-05-19"), WorkerLevel.Junior, 2100);
        databasePopulateService.createWorker("Luk Dan", LocalDate.parse("1999-12-08"), WorkerLevel.Middle, 4800);
        databasePopulateService.createWorker("Jon Lenon", LocalDate.parse("2007-09-09"), WorkerLevel.Trainee, 1500);
        databasePopulateService.createWorker("Eva Van", LocalDate.parse("2004-04-17"), WorkerLevel.Junior, 1800);
        databasePopulateService.createWorker("Ivan Dorn", LocalDate.parse("2004-08-06"), WorkerLevel.Junior, 1500);
        databasePopulateService.createWorker("Viki Lyer", LocalDate.parse("2006-10-08"), WorkerLevel.Trainee, 1200);
        databasePopulateService.createWorker("Kris Null", LocalDate.parse("1987-05-23"), WorkerLevel.Senior, 8000);
        databasePopulateService.createWorker("Ira Zyh", LocalDate.parse("2007-11-05"), WorkerLevel.Junior, 3500);
        databasePopulateService.createWorker("Stas Soha", LocalDate.parse("2010-07-19"), WorkerLevel.Middle, 5500);

        databasePopulateService.createClient("Bob Ranj");
        databasePopulateService.createClient("Gim Malkin");
        databasePopulateService.createClient("Federik Kroy");
        databasePopulateService.createClient("Daniel Bill");
        databasePopulateService.createClient("Bred Joker");
        databasePopulateService.createClient("Rik Dalas");

        databasePopulateService.createProject(1, LocalDate.parse("2023-01-13"), LocalDate.parse("2023-12-13"));
        databasePopulateService.createProject(2, LocalDate.parse("2022-07-15"), LocalDate.parse("2022-11-30"));
        databasePopulateService.createProject(1, LocalDate.parse("2022-05-23"), LocalDate.parse("2023-12-01"));
        databasePopulateService.createProject(3, LocalDate.parse("2022-12-01"), LocalDate.parse("2024-05-23"));
        databasePopulateService.createProject(4, LocalDate.parse("2023-01-05"), LocalDate.parse("2024-09-15"));
        databasePopulateService.createProject(5, LocalDate.parse("2021-05-06"), LocalDate.parse("2023-01-25"));
        databasePopulateService.createProject(1, LocalDate.parse("2023-05-15"), LocalDate.parse("2023-12-01"));
        databasePopulateService.createProject(4, LocalDate.parse("2022-09-06"), LocalDate.parse("2023-06-15"));
        databasePopulateService.createProject(6, LocalDate.parse("2023-02-12"), LocalDate.parse("2023-09-12"));
        databasePopulateService.createProject(1, LocalDate.parse("2022-11-24"), LocalDate.parse("2023-03-28"));

        databasePopulateService.createProjectWorker(1, 3);
        databasePopulateService.createProjectWorker(1, 5);
        databasePopulateService.createProjectWorker(1, 4);
        databasePopulateService.createProjectWorker(2, 10);
        databasePopulateService.createProjectWorker(2, 1);
        databasePopulateService.createProjectWorker(3, 2);
        databasePopulateService.createProjectWorker(4, 6);
        databasePopulateService.createProjectWorker(5, 7);
        databasePopulateService.createProjectWorker(5, 9);
        databasePopulateService.createProjectWorker(4, 8);
        databasePopulateService.createProjectWorker(3, 3);

    }
}

