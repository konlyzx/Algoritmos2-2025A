import java.util.*;

public class POO2 {
    class Tren {
        int id;
        double cargaMaxima;
        double cargaActual;
        String tipoCarga;

        Tren(int id, double cargaMaxima, double cargaActual, String tipoCarga) {
            this.id = id;
            this.cargaMaxima = cargaMaxima;
            this.cargaActual = cargaActual;
            this.tipoCarga = tipoCarga;
        }
    }

    class Bodega {
        int id;
        String direccion;
        double capacidadMaxima;
        int capacidadMaximaTrenes;
        double usoActual;
        int usoActualTrenes;
        String tipoCarga;
        List<Tren> trenes;

        Bodega(int id, String direccion, double capacidadMaxima, int capacidadMaximaTrenes, String tipoCarga) {
            this.id = id;
            this.direccion = direccion;
            this.capacidadMaxima = capacidadMaxima;
            this.capacidadMaximaTrenes = capacidadMaximaTrenes;
            this.tipoCarga = tipoCarga;
            this.trenes = new ArrayList<>();
            this.usoActual = 0;
            this.usoActualTrenes = 0;
        }

        boolean recibirTren(Tren tren) {
            if (!tren.tipoCarga.equals(this.tipoCarga)) {
                System.out.println("Error: El tren no coincide con el tipo de carga de la bodega.");
                return false;
            }
            if (usoActualTrenes >= capacidadMaximaTrenes) {
                System.out.println("Error: No se puede recibir el tren, se ha alcanzado el límite de trenes.");
                return false;
            }
            if (usoActual + tren.cargaActual > capacidadMaxima) {
                System.out.println("Error: No se puede recibir el tren, se excede la capacidad máxima.");
                return false;
            }
            trenes.add(tren);
            usoActual += tren.cargaActual;
            usoActualTrenes++;
            System.out.println("Tren " + tren.id + " recibido en la bodega " + this.id);
            return true;
        }

        boolean darSalidaTren(int trenId) {
            for (Tren tren : trenes) {
                if (tren.id == trenId) {
                    trenes.remove(tren);
                    usoActual -= tren.cargaActual;
                    usoActualTrenes--;
                    System.out.println("Tren " + trenId + " ha salido de la bodega " + this.id);
                    return true;
                }
            }
            System.out.println("Error: Tren no encontrado en la bodega.");
            return false;
        }
    }

    public static void main(String[] args) {
        POO2 sistema = new POO2();
        List<Bodega> bodegas = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Bodega bodega = sistema.new Bodega(i, "Direccion " + i, 1000 + (i * 100), 5, "Alimentos");
            bodegas.add(bodega);
            System.out.println("Bodega creada: " + bodega.id + " en " + bodega.direccion);
        }

        Tren tren1 = sistema.new Tren(1, 500, 300, "Alimentos");
        Tren tren2 = sistema.new Tren(2, 600, 400, "Alimentos");
        Tren tren3 = sistema.new Tren(3, 700, 500, "Insumos Químicos");

        bodegas.get(0).recibirTren(tren1);
        bodegas.get(0).recibirTren(tren2);
        bodegas.get(0).recibirTren(tren3);

        bodegas.get(0).darSalidaTren(1);
    }
}