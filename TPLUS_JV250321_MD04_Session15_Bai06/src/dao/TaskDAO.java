package dao;

import entity.Task;

import java.util.List;

public interface TaskDAO {
    boolean addTasks(Task task);
    List<Task> findAllTask();
    Task findTaskById(int id);
    boolean updateTaskStatus(Task task);
    boolean deleteTask(Task task);
    List<Task> findTaskByName(String name);
}
