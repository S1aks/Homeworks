package homework13;

import static homework13.Main.TUNNEL;

public class Tunnel extends Stage {
    public Tunnel(int length) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            if (TUNNEL.availablePermits() == 0) {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            }
            TUNNEL.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000L);
            System.out.println(c.getName() + " закончил этап: " + description);
            TUNNEL.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

