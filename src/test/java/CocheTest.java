import com.parking.Coche;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

public class CocheTest {

    @DisplayName("Comprobamosm que el coston del parqking es el correcto")
    @Test
    public void costoParking(){
        Coche coche = new Coche();
        double costo= Double.parseDouble(coche.canidadPagar(0,2));
        Assertions.assertEquals(0.30, costo);
    }
    @DisplayName("Comprobamos que el coche existe")
    @Test
    public void cocheExiste(){
        Coche coche = new Coche();
        HashMap <String, Coche>map = new HashMap<>();
        Boolean existe= Boolean.valueOf(String.valueOf(coche.existeCoche("123", map)));
        Assertions.assertEquals(false, existe);
    }

}
