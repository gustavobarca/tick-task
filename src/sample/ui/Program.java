package sample.ui;

import sample.application.TimeTrackingService;
import sample.domain.Task;
import sample.infrastructure.repositories.JsonTaskRepository;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Program {
    private Scanner scanner;
    private TimeTrackingService timeTrackingService;
    private List<Task> todoTasks;
    private List<Task> finishedTasks;
    private List<Task> doingTasks;

    public Program() throws IOException {
        this.scanner = new Scanner(System.in);
        timeTrackingService = new TimeTrackingService(new JsonTaskRepository());
        getTasks();
    }

    private void getTasks() throws IOException {
        this.todoTasks = this.timeTrackingService.getAllTasksToDo();
        this.finishedTasks = this.timeTrackingService.getAllTasksDone();
        this.doingTasks = this.timeTrackingService.getAllDoingTasks();
    }

    private void showTasks() throws IOException {
        getTasks();

        System.out.println("________________________");
        System.out.println("| Tarefas em andamento |\n");
        if (doingTasks.isEmpty()) {
            System.out.println("Nenhuma tarefa ainda!\n");
        } else {
            for (Task doingTask: doingTasks) {
                System.out.println("#" + doingTask.getId() + " " + doingTask.getTitle());
                System.out.println("Percorrido: " + doingTask.getElapsedTime() + " | Estimado: " + doingTask.getEstimatedTime() + "\n");
            }
        }

        System.out.println("______________________");
        System.out.println("| Tarefas a realizar |\n");
        if (todoTasks.isEmpty()) {
            System.out.println("Nenhuma tarefa ainda!\n");
        } else {
            for (Task todoTask: todoTasks) {
                System.out.println("#" + todoTask.getId() + " " + todoTask.getTitle());
                System.out.println("Percorrido: " + todoTask.getElapsedTime() + " | Estimado: " + todoTask.getEstimatedTime() + "\n");
            }
        }

        System.out.println("_______________________");
        System.out.println("| Tarefas finalizadas |\n");
        if (finishedTasks.isEmpty()) {
            System.out.println("Nenhuma tarefa ainda!\n");
        } else {
            for (Task finishedTask: finishedTasks) {
                System.out.println("#" + finishedTask.getId() + " " + finishedTask.getTitle());
                System.out.println("Percorrido: " + finishedTask.getElapsedTime() + " | Estimado: " + finishedTask.getEstimatedTime() + "\n");
            }
        }
    }

    private void showMenuOptions() throws IOException {
        System.out.println("\n1. Adicionar tarefa");
        System.out.println("2. Excluir tarefa ");
        System.out.println("3. Apontar tarefa");
        System.out.println("4. Finalizar tarefa");
        System.out.println("5. Sair");

        showTasks();
    }

    public void showMenu() throws Exception {
        int option = 0;

        do {
            System.out.print("[TICK-TASK]\n");
            showMenuOptions();
            System.out.print("Opcao do menu => ");
            option = Integer.parseInt(this.scanner.nextLine());

            switch (option) {
                case 1:
                    add();
                    break;
                case 2:
                    deleteTask();
                    break;
                case 3:
                    addTaskEntry();
                    break;
                case 4:
                    finishTask();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        } while (option != 9);

        this.scanner.close();
    }

    public void getAll() throws IOException {
        this.timeTrackingService.getAllTasksToDo();
    }

    public void finishTask() throws Exception {
        System.out.print("Id tarefa: ");
        this.timeTrackingService.finishTask(Integer.parseInt(this.scanner.nextLine()));
    }

    public void addTaskEntry() throws IOException {
        System.out.print("Id tarefa: ");
        this.timeTrackingService.addEntryToTask(Integer.parseInt(this.scanner.nextLine()));
    }

    public void add() throws IOException {
        System.out.print("Titulo: ");
        String title = this.scanner.nextLine();

        System.out.print("Estimativa de tempo\n");

        System.out.print("Horas: ");
        int hours = Integer.parseInt(this.scanner.nextLine());

        System.out.print("Minutos: ");
        int minutes = Integer.parseInt(this.scanner.nextLine());

        System.out.print("Segundos: ");
        int seconds = Integer.parseInt(this.scanner.nextLine());

        this.timeTrackingService.addTask(title, hours, minutes, seconds);
    }

    public void deleteTask() throws IOException {
        System.out.print("Id tarefa: ");
        this.timeTrackingService.deleteTask(Integer.parseInt(this.scanner.nextLine()));
    }
}
