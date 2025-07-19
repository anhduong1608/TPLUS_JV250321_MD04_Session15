package dao.imp;

import dao.TaskDAO;
import entity.Task;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImp implements TaskDAO {
    @Override
    public boolean addTasks(Task task) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call add_task(?, ?)}");
            callableStatement.setString(1, task.getTaskName());
            callableStatement.setBoolean(2, task.isStatus());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public List<Task> findAllTask() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Task> taskList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_tasks}");
            ResultSet resultSet = callableStatement.executeQuery();
            taskList = new ArrayList<>();
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("task_id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setStatus(resultSet.getBoolean("status"));
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return taskList;
    }

    @Override
    public Task findTaskById(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Task task = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call search_task_by_id(?)}");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                task = new Task();
                task.setTaskId(resultSet.getInt("task_id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setStatus(resultSet.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return task;
    }

    @Override
    public boolean updateTaskStatus(Task task) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_task_status(?, ?)}");
            callableStatement.setInt(1, task.getTaskId());
            callableStatement.setBoolean(2, task.isStatus());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteTask(Task task) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_task(?)}");
            callableStatement.setInt(1, task.getTaskId());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public List<Task> findTaskByName(String name) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Task> taskList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call search_task_by_name(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            taskList = new ArrayList<>();
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("task_id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setStatus(resultSet.getBoolean("status"));
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return taskList;
    }
}
