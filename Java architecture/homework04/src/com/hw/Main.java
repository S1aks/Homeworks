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

interface Worked {
    boolean diagnose(CarForRepair car);
    void repair(CarForRepair car);
}

abstract class Worker {
    private final String name;
    private final int experience;
    protected boolean canRepair;

    public Worker(String name, int experience) {
        this.name = name;
        this.experience = experience;
        this.canRepair = false;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }
}

abstract class WorkerModal {    // Классы фабричного метода
    public Worked hireWorker (String name, int experience) {
        return createWorker(name, experience);
    }

    protected abstract Worked createWorker(String name, int experience);
}

class EngineWorkerModal extends WorkerModal {

    @Override
    protected Worked createWorker(String name, int experience) {
        return new EngineWorker(name, experience);
    }
}

class TransmissionWorkerModal extends WorkerModal {

    @Override
    protected Worked createWorker(String name, int experience) {
        return new TransmissionWorker(name, experience);
    }
}

class BodyWorkerModal extends WorkerModal {

    @Override
    protected Worked createWorker(String name, int experience) {
        return new BodyWorker(name, experience);
    }
}
//  Реализация структурного паттерна Мост (абстрактный класс менеджера в качестве управления рабочими)
interface Managed {
    void manageWork();
}

class MainManager implements Managed {
    ArrayList<Worked> workers;
    CarForRepair car;

    public MainManager(ArrayList<Worked> workers, CarForRepair car) {
        this.workers = workers;
        this.car = car;
    }

    @Override
    public void manageWork() {
        for (Worked worker : workers) {
            if (worker.diagnose(car)) {
                worker.repair(car);
                car.nowRepaired();
                break;
            }
        }
    }
}
// Вызов класса менеджера ниже в процедуре doRepair класса MachineRepair

class EngineWorker extends Worker implements Worked{

    public EngineWorker(String name, int experience) {
        super(name,experience);
    }

    @Override
    public boolean diagnose(CarForRepair car) {
        if (getExperience() > 50) {
            System.out.println(getName() + " do diagnose..");
            if (car.getBreak() == Breaking.ENGINE) {
                this.canRepair = true;
                System.out.println(getName() + " can repair.");
            } else {
                System.out.println(getName() + " can not repair.");
            }
        } else {
            System.out.println(getName() + " has low experience!");
        }
        return canRepair;
    }

    @Override
    public void repair(CarForRepair car) {
        System.out.println(getName() + " repair engine..");
    }
}

class TransmissionWorker extends Worker implements Worked{

    public TransmissionWorker(String name, int experience) {
        super(name,experience);
    }

    @Override
    public boolean diagnose(CarForRepair car) {
        if (getExperience() > 50) {
            System.out.println(getName() + " do diagnose..");
            if (car.getBreak() == Breaking.TRANSMISSION) {
                this.canRepair = true;
                System.out.println(getName() + " can repair.");
            } else {
                System.out.println(getName() + " can not repair.");
            }
        } else {
            System.out.println(getName() + " has low experience!");
        }
        return canRepair;
    }

    @Override
    public void repair(CarForRepair car) {
        System.out.println(getName() + " repair transmission..");
    }
}

class BodyWorker extends Worker implements Worked{

    public BodyWorker(String name, int experience) {
        super(name,experience);
    }

    @Override
    public boolean diagnose(CarForRepair car) {
        if (getExperience() > 50) {
            System.out.println(getName() + " do diagnose..");
            if (car.getBreak() == Breaking.BODY) {
                this.canRepair = true;
                System.out.println(getName() + " can repair.");
            } else {
                System.out.println(getName() + " can not repair.");
            }
        } else {
            System.out.println(getName() + " has low experience!");
        }
        return canRepair;
    }

    @Override
    public void repair(CarForRepair car) {
        System.out.println(getName() + " repair body..");
    }
}

class MachineRepair {
    private final String name;
    private final ArrayList<Worked> workers;

    public MachineRepair(String name) {
        this.name = name;
        workers = new ArrayList<>();
    }

    public void addWorker(Worked worker) {
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