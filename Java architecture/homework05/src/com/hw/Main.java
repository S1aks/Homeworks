package com.hw;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MachineRepair machineRepair = new MachineRepair("CrazyWheels - RnD");
        WorkerModal modal;  // Реализация фабричного метода
        modal = new EngineWorkerModal();
        machineRepair.addWorker(modal.hireWorker("Ivan", 90));
        machineRepair.addWorker(modal.hireWorker("Petr", 45));
        modal = new TransmissionWorkerModal();
        machineRepair.addWorker(modal.hireWorker("Nikolay", 99));
        modal = new BodyWorkerModal();
        machineRepair.addWorker(modal.hireWorker("Ashot", 80));
        machineRepair.addWorker(modal.hireWorker("Kirill", 30));

        CarForRepair carForRepair = new CarForRepair("VAZ");
        machineRepair.doRepair(carForRepair);
    }
}

abstract class Worker {
    private final String name;
    private final int experience;
    private final Breaking specialization;
    protected boolean canRepair;

    public Worker(String name, int experience, Breaking specialization) {
        this.name = name;
        this.experience = experience;
        this.specialization = specialization;
        this.canRepair = false;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public Breaking getSpecialization() {
        return specialization;
    }
}

abstract class WorkerModal {    // Классы фабричного метода
    public Worker hireWorker (String name, int experience) {
        return createWorker(name, experience);
    }

    protected abstract Worker createWorker(String name, int experience);
}

class EngineWorkerModal extends WorkerModal {

    @Override
    protected Worker createWorker(String name, int experience) {
        return new EngineWorker(name, experience);
    }
}

class TransmissionWorkerModal extends WorkerModal {

    @Override
    protected Worker createWorker(String name, int experience) {
        return new TransmissionWorker(name, experience);
    }
}

class BodyWorkerModal extends WorkerModal {

    @Override
    protected Worker createWorker(String name, int experience) {
        return new BodyWorker(name, experience);
    }
}
//  Реализация структурного паттерна Мост (абстрактный класс менеджера в качестве управления рабочими)
interface Managed {
    void manageWork();
}

class MainManager implements Managed {
    ArrayList<Worker> workers;
    CarForRepair car;

    public MainManager(ArrayList<Worker> workers, CarForRepair car) {
        this.workers = workers;
        this.car = car;
    }

    @Override
    public void manageWork() {
        for (Worker worker : workers) {
            DiagnoseCommand dc = new DiagnoseCommand(worker, car);  // Вызов классов паттерна Command
            if (dc.exec()) {
                RepairCommand rc = new RepairCommand(worker, car);
                rc.exec();
                break;
            }
        }
    }
}
// Вызов класса менеджера ниже в процедуре doRepair класса MachineRepair

// Реализация паттерна Command
abstract class WorkerCommand{
    public Worker worker;
    public CarForRepair car;

    public WorkerCommand(Worker worker, CarForRepair car) {
        this.worker = worker;
        this.car = car;
    }

    public abstract boolean exec();
}
// Реализация первой комманды
class DiagnoseCommand extends WorkerCommand {
    public DiagnoseCommand(Worker worker, CarForRepair car) {
        super(worker, car);
    }

    public boolean exec() {
        if (worker.getExperience() > 50) {
            System.out.println(worker.getName() + " do diagnose..");
            if (car.getBreak() == worker.getSpecialization()) {
                this.worker.canRepair = true;
                System.out.println(worker.getName() + " can repair.");
            } else {
                System.out.println(worker.getName() + " can not repair.");
            }
        } else {
            System.out.println(worker.getName() + " has low experience!");
        }
        return worker.canRepair;
    }
}
// Реализация второй комманды
class RepairCommand extends WorkerCommand {
    public RepairCommand(Worker worker, CarForRepair car) {
        super(worker, car);
    }

    public boolean exec() {
        switch (worker.getSpecialization()) {
            case ENGINE: System.out.println(worker.getName() + " repair engine.."); break;
            case TRANSMISSION: System.out.println(worker.getName() + " repair transmission.."); break;
            case BODY: System.out.println(worker.getName() + " repair body.."); break;
        }
        car.nowRepaired();
        return true;
    }
}


class EngineWorker extends Worker{

    public EngineWorker(String name, int experience) {
        super(name, experience, Breaking.ENGINE);
    }
}

class TransmissionWorker extends Worker{

    public TransmissionWorker(String name, int experience) {
        super(name, experience, Breaking.TRANSMISSION);
    }
}

class BodyWorker extends Worker {

    public BodyWorker(String name, int experience) {
        super(name, experience, Breaking.BODY);
    }
}

class MachineRepair {
    private final String name;
    private final ArrayList<Worker> workers;

    public MachineRepair(String name) {
        this.name = name;
        workers = new ArrayList<>();
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public void doRepair(CarForRepair car) {
        Managed manager = new MainManager(workers, car);
        manager.manageWork();
    }
}

enum Breaking {NONE, ENGINE, TRANSMISSION, BODY}

class CarForRepair {
    private final String model;
    private Breaking breaking;

    public CarForRepair(String model) {
        this.model = model;
        int sw = (int) (Math.random() * 3) + 1;
        switch (sw) {
            case (1):
                this.breaking = Breaking.ENGINE;
                break;
            case (2):
                this.breaking = Breaking.TRANSMISSION;
                break;
            case (3):
                this.breaking = Breaking.BODY;
        }
        System.out.println(model + " " + breaking + " is breaked.");
    }

    public Breaking getBreak() {
        return this.breaking;
    }

    public void nowRepaired() {
        this.breaking = Breaking.NONE;
        System.out.println(model + " is repaired!");
    }
}