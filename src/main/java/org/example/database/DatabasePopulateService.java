package org.example.database;

import org.example.model.WorkerLevel;
import org.example.prefs.Prefs;
import org.h2.command.dml.Insert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class DatabasePopulateService {
    private static PreparedStatement insertWorker;
    private static PreparedStatement insertClient;
    private static PreparedStatement insertProject;
    private static PreparedStatement insertProjectWorker;

    public static boolean createWorker(String name, LocalDate birthday, WorkerLevel level, int salary) {
        try{
            insertWorker.setString(1, name);
            insertWorker.setString(2, birthday.toString());
            insertWorker.setString(3, level.name());
            insertWorker.setInt(4, salary);
            return insertWorker.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createClient(String name){
        try{
            insertClient.setString(1, name);
            return insertClient.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createProject( int client_id, LocalDate start_date, LocalDate finish_date){
        try { insertProject.setInt(1, client_id);
            insertProject.setString(2, start_date.toString());
            insertProject.setString(3, finish_date.toString());
            return  insertProject.executeUpdate()>0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createProjectWorker(int project_id, int worker_id){
        try{ insertProjectWorker.setInt(1, project_id);
            insertProjectWorker.setInt(2, worker_id);
            return insertProjectWorker.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String sqlWorker = "INSERT INTO worker (name, birthday, level, salary) VALUES(?, ?, ?, ?)";
        String sqlClient = "INSERT INTO client (name) VALUES(?)";
        String sqlProject = "INSERT INTO project (client_id, start_date, finish_date) VALUES(?, ?, ?)";
        String sqlProjectWorker = "INSERT INTO project_worker (project_id, worker_id) VALUES(?, ?)";


        Database database = Database.getInstance();

        try( Connection connection = database.getConnection()){
            insertWorker = connection.prepareStatement(sqlWorker);
            insertClient = connection.prepareStatement(sqlClient);
            insertProject = connection.prepareStatement(sqlProject);
            insertProjectWorker = connection.prepareStatement(sqlProjectWorker);

            createWorker("Maks Roy", LocalDate.parse("1990-02-25"), WorkerLevel.Senior, 7500);
            createWorker("Nik Korn", LocalDate.parse("2002-05-19"), WorkerLevel.Junior, 2100);
            createWorker("Luk Dan", LocalDate.parse("1999-12-08"), WorkerLevel.Middle, 4800);
            createWorker("Jon Lenon", LocalDate.parse("2007-09-09"), WorkerLevel.Trainee, 1500);
            createWorker("Eva Van", LocalDate.parse("2004-04-17"), WorkerLevel.Junior, 1800);
            createWorker("Ivan Dorn", LocalDate.parse("2004-08-06"), WorkerLevel.Junior, 1500);
            createWorker("Viki Lyer", LocalDate.parse("2006-10-08"), WorkerLevel.Trainee, 1200);
            createWorker("Kris Null", LocalDate.parse("1987-05-23"), WorkerLevel.Senior, 8000);
            createWorker("Ira Zyh", LocalDate.parse("2007-11-05"), WorkerLevel.Junior, 3500);
            createWorker("Stas Soha",LocalDate.parse( "2010-07-19"), WorkerLevel.Middle, 5500);

            createClient("Bob Ranj");
            createClient("Gim Malkin");
            createClient("Federik Kroy");
            createClient("Daniel Bill");
            createClient("Bred Joker");
            createClient("Rik Dalas");

            createProject(1, LocalDate.parse("2023-01-13"), LocalDate.parse("2023-12-13"));
            createProject(2, LocalDate.parse("2022-07-15"), LocalDate.parse("2022-11-30"));
            createProject(1, LocalDate.parse("2022-05-23"), LocalDate.parse("2023-12-01"));
            createProject(3, LocalDate.parse("2022-12-01"), LocalDate.parse("2024-05-23"));
            createProject(4, LocalDate.parse("2023-01-05"), LocalDate.parse("2024-09-15"));
            createProject(5, LocalDate.parse("2021-05-06"), LocalDate.parse("2023-01-25"));
            createProject(1, LocalDate.parse("2023-05-15"), LocalDate.parse("2023-12-01"));
            createProject(4, LocalDate.parse("2022-09-06"), LocalDate.parse("2023-06-15"));
            createProject(6, LocalDate.parse("2023-02-12"), LocalDate.parse("2023-09-12"));
            createProject(1, LocalDate.parse("2022-11-24"), LocalDate.parse("2023-03-28"));

            createProjectWorker(1, 3);
            createProjectWorker(1,5);
            createProjectWorker(1,4);
            createProjectWorker(2,10);
            createProjectWorker(2, 1);
            createProjectWorker(3, 2);
            createProjectWorker(4, 6);
            createProjectWorker(5,7);
            createProjectWorker(5, 9);
            createProjectWorker(4,8);
            createProjectWorker(3,3);

        } catch (SQLException e){
            e.printStackTrace();
        }
        database.close();
    }
}
